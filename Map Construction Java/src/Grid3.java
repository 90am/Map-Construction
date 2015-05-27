import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.threed.Line;

import java.util.*;

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
        this.angles = angles;
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

    // TODO
    // Expand lines in correct direction method
    // Check whether lines can be connected
    // Connect lines via proper heuristic

    // Compute best fit curve by trying different degrees and splitting up the points in different clusters
    // Remove bad lines
    // Add probability for different angles, like it's done with the neighborhood
    // Probabilities for different path between points far from each other

    /*public ArrayList<GridPosition> extendCurve(ArrayList<GridPosition> s1, ArrayList<GridPosition> s2){
        double distance1 = util.getDistancePointToSegment(s1.get(0), s2);
        double distance2 = util.getDistancePointToSegment(s1.get(s1.size()-1), s2);
        if(distance1 < distance2){
            Line line = new Line(util.getVector(s1.get(0)), util.getVector(s1.get(1)), 0);
            for (int i = 1; i < s2.size() - 1; i++) {
                Line temp = new Line(util.getVector(s2.get(i - 1)), util.getVector(s2.get(i)), 0);
                Vector3D intersection = line.intersection(temp);
                if (intersection != null) {
                    return s1;
                }

            }
        }
        else{

        }
        return null;
    }*/

    public HashMap<Integer, ArrayList<GridPosition>> computeCurves() {
        HashMap<Integer, HashMap<GridPosition, Double>> components = getComponents(threshold);
        return util.weightedCurveFitting(components, 10, 3);
    }

    public HashMap<Integer, ArrayList<Point>> getFormattedCurves(){
        return util.formatGridPositions(computeCurves(), xPixelWidth, yPixelWidth, xMin, yMin);
    }

    private void addNeighborProbability(Point p, int angleIndex){
        GridPosition g = getGridPosition(p);
        ArrayList<GridPosition> neighbors = getNeighbors(g);
        double[] angleArray;
        if(!gridValues.containsKey(g)){
            angleArray = new double[angles];
            gridValues.put(g, angleArray);
        }
        else{
            angleArray = gridValues.get(g);
        }
        angleArray[angleIndex]++;
        for(GridPosition n : neighbors){
            if(!gridValues.containsKey(n)){
                angleArray = new double[angles];
                gridValues.put(n,angleArray);
            }
            else{
                angleArray = gridValues.get(n);
            }
            angleArray[angleIndex] += 0.5;
        }
    }

    public void addSegment(Point a, Point b){
        Point p1 = a;
        Point p2 = b;
        if(a.getY() > b.getY()){
            p1 = b;
            p2 = a;
        }
        double distance = util.getDistancePointToPoint(p1, p2);
        double ang = util.getAngle(p1, p2);
        int angIdx = (int)Math.floor((angles * ((ang + Math.PI / (angles * 2)) / (Math.PI)))) % angles;
        int x = (int)((p1.getX()-xMin)/xPixelWidth);
        int y = (int)((p1.getY()-yMin)/yPixelWidth);
        int xStop = (int)((p2.getX()-xMin)/xPixelWidth);
        int yStop = (int)((p2.getY()-yMin)/yPixelWidth);
        int xSteps = Math.abs(xStop-x);
        int ySteps = Math.abs(yStop-y);
        addNeighborProbability(p1, angIdx);
        addNeighborProbability(p2, angIdx);
        if(ySteps >= xSteps){
            int step = 1;
            double xChange = ySteps == 0 ? 0 : (p2.getX() - p1.getX()) / ySteps;
            while(y < yStop){
                double xVal = p1.getX() + (step*xChange);
                x = (int) ((xVal-xMin)/xPixelWidth);
                GridPosition g = new GridPosition(x, y);
                double[] angl;
                if(!gridValues.containsKey(g)){
                    angl = new double[angles];
                    gridValues.put(g,angl);
                }
                else{
                    angl = gridValues.get(g);
                }
                angl[angIdx]++;
                y++;
                step++;
            }
        }
        else{
            int step = 1;
            double yChange = (p2.getY() - p1.getY()) / xSteps;
            while(x != xStop){
                double yVal = p1.getY() + (step*yChange);
                y = (int) ((yVal-yMin)/yPixelWidth);
                GridPosition g = new GridPosition(x, y);
                double[] angl;
                if(!gridValues.containsKey(g)){
                    angl = new double[angles];
                    gridValues.put(g,angl);
                }
                else{
                    angl = gridValues.get(g);
                }
                angl[angIdx]++;
                x += Math.signum(xStop-x);
                step++;
            }
        }

    }


    public HashMap<Integer, HashMap<GridPosition, Double>> getComponents(double threshold){
        HashMap<GridPosition, double[]> dataSet = gridValues;
        int componentId = 1;
        HashMap<Integer, HashMap<GridPosition, Double>> components = new HashMap<Integer, HashMap<GridPosition, Double>>();
        for(GridPosition g : dataSet.keySet()){
            double[] angleArray = dataSet.get(g);
            for(int i=0; i<angles; i++) {
                if(angleArray[i] > threshold){
                    HashSet<GridPosition> visited = new HashSet<GridPosition>();
                    HashMap<GridPosition, Double> component = new HashMap<GridPosition, Double>();
                    LinkedList<GridPosition> toVisit = new LinkedList<GridPosition>();
                    visited.add(g);
                    component.put(g, dataSet.get(g)[i]);
                    toVisit.add(g);
                    dataSet.get(g)[i] = 0;
                    while(toVisit.size() > 0){
                        GridPosition current = toVisit.poll();
                        for (GridPosition n : getNeighborsWithProbability(current)) {
                            if (!visited.contains(n)) {
                                visited.add(n);
                                if (dataSet.get(n)[i] > threshold) {
                                    toVisit.add(n);
                                    component.put(n, dataSet.get(n)[i]);
                                    dataSet.get(n)[i] = 0;
                                }
                            }
                        }
                    }
                    if(component.size() > 3){
                        components.put(componentId++, component);
                    }
                }
            }
        }
        return components;
    }

    public void binarize(){
        HashMap<GridPosition, double[] > result = new HashMap<GridPosition, double[]>();
        for(GridPosition g : gridValues.keySet()){
            if(getMaxAngle(g) > threshold)
                result.put(g, gridValues.get(g));
        }
        gridValues = result;
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


    public ArrayList<GridPosition> getNeighborsWithProbability(GridPosition g){
        ArrayList<GridPosition> result = new ArrayList<GridPosition>();
        for(int i=(int)g.getX()-1; i<= g.getX()+1; i++){
            for(int j=(int)g.getY()-1; j<=g.getY()+1; j++){
                if(!(i == g.getX() && j == g.getY()) && gridValues.containsKey(new GridPosition(i, j))){
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

    public int maxAng(GridPosition g){
        double[] angleArrray = gridValues.get(g);
        int max = 0;
        double value = 0;
        for(int i=0; i<angles-1; i++){
            if(angleArrray[i] > value){
                max = i;
                value = angleArrray[i];
            }
        }
        return max;
    }
}
