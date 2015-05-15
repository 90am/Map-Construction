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

    public void blur(){

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
