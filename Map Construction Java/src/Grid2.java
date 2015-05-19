import com.bbn.openmap.omGraphics.grid.OMGridObjects;
import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.util.*;

/**
 * Created by Andreas on 13/05/15.
 */
public class Grid2 {

    private HashMap<GridPosition, Double> gridValues;
    private double xPixelWidth;
    private double yPixelWidth;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private int angles;
    private double threshold;


    public Grid2(double xPixelWidth, double yPixelWidth, int angles, UTMPoint min, UTMPoint max, double threshold){
        gridValues = new HashMap<GridPosition, Double>();
        this.angles = angles;
        this.xMin = min.easting;
        this.xMax = max.easting;
        this.yMin = min.northing;
        this.yMax = max.northing;
        this.xPixelWidth = xPixelWidth;
        this.yPixelWidth = yPixelWidth;
        this.threshold = threshold;
    }

    public double getXPixelWidth(){
        return xPixelWidth;
    }

    public double getYPixelWidth(){
        return yPixelWidth;
    }

    public void replacePoints(HashMap<Point, Double> points){
        gridValues = new HashMap<GridPosition, Double>();
        for(Point p : points.keySet()){
            GridPosition g = getGridPosition(p);
            gridValues.put(g, points.get(p));
        }
    }

    public HashMap<Point, Double> getPoints(){
        HashMap<Point, Double> result = new HashMap<Point, Double>();
        int pointId = 0;
        for(GridPosition g : gridValues.keySet()){
            UTMPoint current = new UTMPoint((g.getY()*yPixelWidth)+yMin, (g.getX()*xPixelWidth)+xMin, 32, 'N');
            LatLonPoint l = current.toLatLonPoint();
            Point p = new Point(l.getLatitude(), l.getLongitude(), current.easting, current.northing, "", pointId++, 0);
            result.put(p, gridValues.get(g));
        }
        return result;
    }

    public HashMap<Integer, ArrayList<GridPosition>> computeBorderLines(){
        int borderId = 0;
        HashMap<Integer, ArrayList<GridPosition>> result = new HashMap<Integer, ArrayList<GridPosition>>();
        for(GridPosition g : gridValues.keySet()){
            ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
            temp.add(g);
            ArrayList<GridPosition> neighborhood = get8Neighborhood(g);
            GridPosition first = g;
            GridPosition second = null;
            int zero = getZeroElementIndex(neighborhood);
            int start = getOneElementIndex(neighborhood, zero);
            if(start != 9) {
                second = neighborhood.get(start);
                temp.add(second);
                GridPosition prev = null;
                GridPosition current = second;
                while (!current.equals(second) && !prev.equals(first)) {
                    neighborhood = get8Neighborhood(current);
                    start = getOneElementIndex(neighborhood, start);
                    if (start != 9) {
                        prev = current;
                        current = neighborhood.get(start);
                        temp.add(current);
                    } else {
                        break;
                    }
                }
            }
            if(temp.size() > 1){
                result.put(borderId++, temp);
            }
        }
        return result;
    }

    public HashMap<Integer, ArrayList<Point>> getFormattedBorderLines(){
        int pointId = 0;
        HashMap<Integer, ArrayList<GridPosition>> borderLines = computeBorderLines();
        HashMap<Integer, ArrayList<Point>> result = new  HashMap<Integer, ArrayList<Point>>();
        for(Integer key : borderLines.keySet()){
            ArrayList<Point> list = new ArrayList<Point>();
            for(GridPosition g : borderLines.get(key)) {
                UTMPoint current = new UTMPoint((g.getY() * yPixelWidth) + yMin, (g.getX() * xPixelWidth) + xMin, 32, 'N');
                LatLonPoint l = current.toLatLonPoint();
                Point p = new Point(l.getLatitude(), l.getLongitude(), current.easting, current.northing, "", pointId++, key);
                list.add(p);
            }
            result.put(key, list);
        }
        return result;
    }

    private int getOneElementIndex(ArrayList<GridPosition> neighborhood, int start){
        int next = getNextIndexCounterclockwise(start);
        while(next != start){
            if(gridValues.containsKey(neighborhood.get(next))){
                return next;
            }
            next = getNextIndexCounterclockwise(next);
        }
        return 9;
    }

    private int getNextIndexCounterclockwise(int start){
        int next = start-1;
        if(next < 0){
            next = 7;
        }
        return next;
    }

    private int getZeroElementIndex(ArrayList<GridPosition> neighborhood){
        int zero = 0;
        for (int i = 0; i < neighborhood.size() - 1; i++) {
            if (!gridValues.containsKey(neighborhood.get(i))) {
                zero = i;
            }
        }
        return zero;
    }

    public void binarize(){
        HashMap<GridPosition, Double> result = new HashMap<GridPosition, Double>();
        for(GridPosition g : gridValues.keySet()){
            if(gridValues.get(g) > threshold)
                result.put(g, gridValues.get(g));
        }
        gridValues = result;
    }

    public void shrink(){
        HashMap<GridPosition, Double> result = new HashMap<GridPosition, Double>();
        boolean foundSomethingToRemove = false;
        for(GridPosition g : gridValues.keySet()){
            ArrayList<GridPosition> neighbors = get8Neighborhood(g);
            int temp = 0;
            for(GridPosition n : neighbors){
                temp += contains(n);
            }
            if(temp > 0 && temp != 8) {
                int N = 0;
                for (int i = 0; i < neighbors.size() - 1; i++) {
                    int one = contains(neighbors.get(i));
                    int two = contains(neighbors.get((i + 1) % 8));
                    int three = contains(neighbors.get((i + 2) % 8));
                    int value = (1 - one) - ((1 - one) * (1 - two) * (1 - three));
                    N += value;
                }
                if (N != 1) {
                    result.put(g, gridValues.get(g));
                }
                else{
                    foundSomethingToRemove = true;
                }
            }
        }
        gridValues = result;
        if(foundSomethingToRemove){
            shrink();
        }
    }

    private int contains(GridPosition g){
        if(gridValues.containsKey(g)){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void addTrajectory(ArrayList<Point> trajectory){
        GridPosition prevG = null;
        for(Point p : trajectory){
            GridPosition g = getGridPosition(p);
            if(!gridValues.containsKey(g)){
                gridValues.put(g, 1.0);
            }
            else{
                gridValues.put(g, gridValues.get(g)+1);
            }
            ArrayList<GridPosition> neighbors = getNeighbors(g);
            for(GridPosition n : neighbors){
                if(!gridValues.containsKey(n)){
                    gridValues.put(g, 0.5);
                }
                else{
                    gridValues.put(n, gridValues.get(n)+0.5);
                }
            }
            if(prevG != null){
                GridPosition first;
                GridPosition second;
                if(prevG.getX() < g.getX()){
                    first = prevG;
                    second = g;
                }
                else{
                    first = g;
                    second = prevG;
                }
                int step = 0;
                int xSteps = (int) Math.abs(second.getX()-first.getX());
                double x = first.getX();
                double yChange = (second.getY() - first.getY()) / xSteps;
                while(x != second.getX()){
                    double yVal = first.getY() + (step*yChange);
                    int y = (int) ((yVal-yMin)/yPixelWidth);
                    GridPosition newG = new GridPosition(x, y);
                    if(!gridValues.containsKey(newG)){
                        gridValues.put(newG, 0.5);
                    }
                    else{
                        gridValues.put(g, gridValues.get(newG)+0.5);
                    }
                    step++;
                    x++;
                }
            }
            prevG = g;
        }
    }

    public ArrayList<GridPosition> getNeighbors(GridPosition g){
        ArrayList<GridPosition> result = new ArrayList<GridPosition>();
        for(int i=(int)g.getX()-1; i<= g.getX()+1; i++){
            for(int j=(int)g.getY()-1; j<=g.getY()+1; j++){
                if(!(i == g.getX() && j == g.getY())){
                    result.add(new GridPosition(i, j));
                }
            }
        }
        return result;
    }

    public ArrayList<GridPosition> get8Neighborhood(GridPosition g){
        ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
        ArrayList<GridPosition> result = new ArrayList<GridPosition>();
        for(int i=(int)g.getX()-1; i<= g.getX()+1; i++){
            for(int j=(int)g.getY()-1; j<=g.getY()+1; j++){
                if(!(i == g.getX() && j == g.getY())){
                    temp.add(new GridPosition(i, j));
                }
            }
        }
        result.add(temp.get(0));
        result.add(temp.get(1));
        result.add(temp.get(2));
        result.add(temp.get(4));
        result.add(temp.get(7));
        result.add(temp.get(6));
        result.add(temp.get(5));
        result.add(temp.get(3));
        return result;
    }



    public GridPosition getGridPosition(Point p){
        int x = (int)((p.getX()-xMin)/xPixelWidth);
        int y = (int)((p.getY()-yMin)/yPixelWidth);
        return new GridPosition(x, y);
    }

}
