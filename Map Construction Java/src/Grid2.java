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


    public HashMap<Point, Double> getPossiblePoints(){
        HashMap<Point, Double> result = new HashMap<Point, Double>();
        int pointId = 0;
        for(GridPosition g : gridValues.keySet()){
            UTMPoint current = new UTMPoint((g.getY()*yPixelWidth)+yMin, (g.getX()*xPixelWidth)+xMin, 32, 'N');
            LatLonPoint l = current.toLatLonPoint();
            Point p = new Point(l.getLatitude(), l.getLongitude(), current.easting, current.northing, "", pointId++, 0);
            if(gridValues.get(g) > threshold)
                result.put(p, gridValues.get(g));
        }
        return result;
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
            /*ArrayList<GridPosition> neighbors = getNeighbors(g);
            for(GridPosition n : neighbors){
                if(!gridValues.containsKey(n)){
                    gridValues.put(g, 0.5);
                }
                else{
                    gridValues.put(n, gridValues.get(n)+0.5);
                }
            }*/

            /*if(prevG != null){

            }
            prevG = g;*/
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

    public GridPosition getGridPosition(Point p){
        int x = (int)((p.getX()-xMin)/xPixelWidth);
        int y = (int)((p.getY()-yMin)/yPixelWidth);
        return new GridPosition(x, y);
    }

}
