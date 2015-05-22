import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 22/05/15.
 */
public class Grid3 {

    private Util util;
    private HashMap<GridPosition, double[]> gridValues;
    private double xPixelWidth;
    private double yPixelWidth;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private int angles;
    private double threshold;

    public Grid3(double xPixelWidth, double yPixelWidth, int angles, UTMPoint min, UTMPoint max, double threshold){
        util = new Util();
        gridValues = new HashMap<GridPosition, double[]>();
        this.angles = angles;
        this.xMin = min.easting;
        this.xMax = max.easting;
        this.yMin = min.northing;
        this.yMax = max.northing;
        this.xPixelWidth = xPixelWidth;
        this.yPixelWidth = yPixelWidth;
        this.threshold = threshold;
        this.angles = 8;
    }

    public double getXPixelWidth(){
        return xPixelWidth;
    }

    public double getYPixelWidth(){
        return yPixelWidth;
    }

    public HashMap<Point, Double> getPoints(){
        HashMap<Point, Double> result = new HashMap<Point, Double>();
        int pointId = 0;
        for(GridPosition g : gridValues.keySet()){
            UTMPoint current = new UTMPoint((g.getY()*yPixelWidth)+yMin, (g.getX()*xPixelWidth)+xMin, 32, 'N');
            LatLonPoint l = current.toLatLonPoint();
            Point p = new Point(l.getLatitude(), l.getLongitude(), current.easting, current.northing, "", pointId++, 0, 0);
            result.put(p, getMaxAngle(g));
        }
        return result;
    }

    public double getMaxAngle(GridPosition g){
        double[] angleArrray = gridValues.get(g);
        double value = 0;
        for(int i=0; i<angles-1; i++){
            if(angleArrray[i] > value){
                value = angleArrray[i];
            }
        }
        return value;
    }


    public void binarize(){
        HashMap<GridPosition, double[] > result = new HashMap<GridPosition, double[]>();
        for(GridPosition g : gridValues.keySet()){
            if(getMaxAngle(g) > threshold)
                result.put(g, gridValues.get(g));
        }
        gridValues = result;
    }


    public void addTrajectory(ArrayList<Point> trajectory) {
        for (int i = 1; i < trajectory.size() - 1; i++) {
            Point p1 = trajectory.get(i-1);
            Point p2 = trajectory.get(i);
            double distance = util.getDistancePointToPoint(p1, p2);
            double ang = 0;
            if(p2.getY() < p1.getY())
                ang = util.getAngle(p2, p1);
            else
                ang = util.getAngle(p1, p2);
            int angleIndex = (int) Math.floor((angles * ((ang + Math.PI / (angles * 2)) / (Math.PI)))) % angles;
            GridPosition g1 = getGridPosition(p1);
            double[] angleArray = new double[angles];
            // Add probability for start point;
            if (gridValues.containsKey(g1)) {
                angleArray = gridValues.get(g1);
            }
            angleArray[angleIndex] += 1.0;
            gridValues.put(g1, angleArray);
            // Add probability for start point neighbors
            ArrayList<GridPosition> neighbors = getNeighbors(g1);
            for (GridPosition n : neighbors) {
                if (!gridValues.containsKey(n)) {
                    angleArray = new double[angles];
                } else {
                    angleArray = gridValues.get(n);
                }
                angleArray[angleIndex] += 0.5;
                gridValues.put(n, angleArray);
            }
            // Add probability for gridpositions between points
            GridPosition g2 = getGridPosition(p2);
            if (g1.getX() > g2.getX()) {
                g1 = g2;
                g2 = getGridPosition(p1);
            }
            int step = 0;
            double x = g1.getX();
            int xSteps = (int) Math.abs(g2.getX() - g1.getX());
            double yChange = (g2.getY() - g1.getY()) / xSteps;
            while (x != g2.getX()) {
                if(step != 0){
                    double yVal = g1.getY() + (step*yChange);
                    int y = (int) ((yVal-yMin)/yPixelWidth);
                    GridPosition newG = new GridPosition(x, y);
                    if (!gridValues.containsKey(newG)) {
                        angleArray = new double[angles];
                    } else {
                        angleArray = gridValues.get(newG);
                    }
                    angleArray[angleIndex] += 1/distance;
                    gridValues.put(newG, angleArray);
                }
                step++;
                x++;
            }
            // Add probability for last point in trajectory
            if(i == trajectory.size()-1){
                g2 = getGridPosition(p2);
                if (!gridValues.containsKey(g2)) {
                    angleArray = new double[angles];
                } else {
                    angleArray = gridValues.get(g2);
                }
                angleArray[angleIndex] += 1.0;
                gridValues.put(g2, angleArray);
                neighbors = getNeighbors(g2);
                for (GridPosition n : neighbors) {
                    if (!gridValues.containsKey(n)) {
                        angleArray = new double[angles];
                    } else {
                        angleArray = gridValues.get(n);
                    }
                    angleArray[angleIndex] += 0.5;
                    gridValues.put(n, angleArray);
                }
            }
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
