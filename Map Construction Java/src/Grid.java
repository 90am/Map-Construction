import com.bbn.openmap.proj.coords.UTMPoint;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.*;

/**
 * Created by Andreas on 07/05/15.
 *
 * Class representing the original algorithm
 *
 */
public class Grid {
    private Util util;
    private HashMap<GridPosition, double[]> gridValues;
    private double xPixelWidth;
    private double yPixelWidth;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private int angles;

    public Grid(double xPixelWidth, double yPixelWidth, int angles, UTMPoint min, UTMPoint max){
        util = new Util();
        gridValues = new HashMap<GridPosition, double[]>();
        this.angles = angles;
        this.xMin = min.easting;
        this.xMax = max.easting;
        this.yMin = min.northing;
        this.yMax = max.northing;
        this.xPixelWidth = xPixelWidth;
        this.yPixelWidth = yPixelWidth;
    }

    public Grid(double xPixelWidth, double yPixelWidth, int angles, int xMin, int yMin, int xMax, int yMax){
        gridValues = new HashMap<GridPosition, double[]>();
        this.xPixelWidth = xPixelWidth;
        this.yPixelWidth = yPixelWidth;
        this.angles = angles;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public double getXPixelWidth(){
        return xPixelWidth;
    }

    public double getYPixelWidth(){
        return yPixelWidth;
    }

    public HashMap<Integer, ArrayList<Point>> getResult(){
        System.out.println("AHBI "+gridValues.keySet().size());
        HashMap<Integer, ArrayList<GridPosition>> lines = computeLines();
        HashMap<Integer, ArrayList<Point>> formattedLines = util.formatGridPositions(lines, xPixelWidth, yPixelWidth, xMin, yMin);
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        for(Integer key : formattedLines.keySet()){
            double distance = util.getDistanceOfSegment(formattedLines.get(key));
            if(distance > 45){
                result.put(key, formattedLines.get(key));
            }
        }
        return result;
    }

    public HashMap<Integer, ArrayList<GridPosition>> computeCurves() {
        HashMap<Integer, HashMap<GridPosition, Double>> components = getComponents();
        return util.weightedCurveFitting(components, 5, 4);
    }


    public HashMap<Integer, ArrayList<GridPosition>> computeLines(){
        HashMap<Integer, HashMap<GridPosition, Double>> components = getComponents();
        return util.linearRegression(components);
    }

    public void removeInsignificantBins() {
        for (GridPosition g : gridValues.keySet()) {
            int angle = maxAng(g);
            double prob = 0;
            int counter = 0;
            for(GridPosition n : getNeighborsWithProbability(g)){
                if(maxAng(n) == angle){
                    prob += gridValues.get(n)[angle];
                    counter++;
                }
            }
            if(counter > 0) {
                if (gridValues.get(g)[angle] < (prob/counter)) {
                    gridValues.put(g, new double[angles]);
                }
            }
        }
    }

    public HashMap<Integer, HashMap<GridPosition, Double>> getComponents(){
        int componentId = 1;
        HashMap<Integer, HashMap<GridPosition, Double>> components = new HashMap<Integer, HashMap<GridPosition, Double>>();
        HashSet<GridPosition> addedToComponent = new HashSet<GridPosition>();
        for(GridPosition g : gridValues.keySet()){
            if(!addedToComponent.contains(g)){
                HashSet<GridPosition> visited = new HashSet<GridPosition>();
                HashMap<GridPosition, Double> component = new HashMap<GridPosition, Double>();
                LinkedList<GridPosition> toVisit = new LinkedList<GridPosition>();
                int angle = maxAng(g);
                component.put(g, gridValues.get(g)[angle]);
                addedToComponent.add(g);
                visited.add(g);
                toVisit.add(g);
                while(toVisit.size() > 0){
                    GridPosition current = toVisit.poll();
                    for(GridPosition neighbour : getNeighborsWithProbability(current)){
                        if(!visited.contains(neighbour)){
                            visited.add(neighbour);
                            if(angle == maxAng(neighbour) && gridValues.get(neighbour)[angle] > 0 && !addedToComponent.contains(neighbour)){
                                toVisit.add(neighbour);
                                component.put(neighbour, gridValues.get(neighbour)[angle]);
                                addedToComponent.add(neighbour);
                            }
                        }
                    }
                }
                if(component.size() > 4)
                    components.put(componentId++, component);
            }
        }
        return components;
    }

    // Code to sort a component
    /*
    if(component.size() > 2){
        boolean one = component.get(0).getX() > component.get(1).getX() && component.get(0).getY() > component.get(1).getY();
        boolean two = component.get(0).getX() < component.get(1).getX() && component.get(0).getY() < component.get(1).getY();
        if(one && two){
            Collections.sort(component, new comparator1());
        }
        else{
            Collections.sort(component, new comparator2());
        }
    }
    components.put(getComponentId(), component);*/

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

    public void addSegment(Point a, Point b){
        Point p1 = a;
        Point p2 = b;
        if(a.getY() > b.getY()){
            p1 = b;
            p2 = a;
        }
        double ang = util.getAngle(p1, p2);
        int angIdx = (int)Math.floor((angles * ((ang + Math.PI / (angles * 2)) / (Math.PI)))) % angles;
        int x = (int)((p1.getX()-xMin)/xPixelWidth);
        int y = (int)((p1.getY()-yMin)/yPixelWidth);
        int xStop = (int)((p2.getX()-xMin)/xPixelWidth);
        int yStop = (int)((p2.getY()-yMin)/yPixelWidth);
        int xSteps = Math.abs(xStop-x);
        int ySteps = Math.abs(yStop-y);
        if(ySteps >= xSteps){
            int step = 0;
            double xChange = ySteps == 0 ? 0 : (p2.getX() - p1.getX()) / ySteps;
            while(y <= yStop){
                double xVal = p1.getX() + (step*xChange);
                x = (int) ((xVal-xMin)/xPixelWidth);
                GridPosition g = new GridPosition(x, y);
                addProbability(g, angIdx, 1);
                y++;
                step++;
            }
        }
        else{
            int step = 0;
            double yChange = (p2.getY() - p1.getY()) / xSteps;
            while(x != xStop){
                double yVal = p1.getY() + (step*yChange);
                y = (int) ((yVal-yMin)/yPixelWidth);
                GridPosition g = new GridPosition(x, y);
                addProbability(g, angIdx, 1);
                x += Math.signum(xStop-x);
                step++;
            }
        }

    }

    private void addProbability(GridPosition g, int angleIndex, double prob){
        double[] angleArray;
        if(!gridValues.containsKey(g)){
            angleArray = new double[angles];
            gridValues.put(g, angleArray);
        }
        else{
            angleArray = gridValues.get(g);
        }
        angleArray[angleIndex] += prob;

    }

    public void printAngl(int[] angl){
        for(int i=0; i<angl.length; i++){
            System.out.print(angl[i]+" ");
        }
    }

    class comparator1 implements Comparator<GridPosition> {
        public int compare(GridPosition g1, GridPosition g2) {
            return (int) ((g2.getX() - g2.getX())+(g2.getY()-g1.getY()) ) ;
        }
    }

    class comparator2 implements Comparator<GridPosition> {
        public int compare(GridPosition g1, GridPosition g2) {
            return (int) ((g2.getX() - g2.getX())-(g2.getY()-g1.getY()) ) ;
        }
    }

}
