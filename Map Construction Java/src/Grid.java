import com.bbn.openmap.proj.coords.UTMPoint;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.*;

/**
 * Created by Andreas on 07/05/15.
 */
public class Grid {
    private Util util;
    private HashMap<GridPosition, int[]> gridValues;
    private double xPixelWidth;
    private double yPixelWidth;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private int angles;
    private int componentId;

    public Grid(double xPixelWidth, double yPixelWidth, int angles, UTMPoint min, UTMPoint max){
        util = new Util();
        gridValues = new HashMap<GridPosition, int[]>();
        this.angles = angles;
        this.xMin = min.easting;
        this.xMax = max.easting;
        this.yMin = min.northing;
        this.yMax = max.northing;
        /*double xDist = xMax-xMin;
        double yDist = yMax-yMin;
        double ration = xDist/yDist;
        xPixelWidth = (xMax-xMin)/xWidth;
        yPixelWidth = (yMax-yMin)/(xWidth/ration);*/
        this.xPixelWidth = xPixelWidth;
        this.yPixelWidth = yPixelWidth;
        componentId = 0;
    }

    public Grid(double xPixelWidth, double yPixelWidth, int angles, int xMin, int yMin, int xMax, int yMax){
        gridValues = new HashMap<GridPosition, int[]>();
        this.xPixelWidth = xPixelWidth;
        this.yPixelWidth = yPixelWidth;
        this.angles = angles;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        componentId = 0;
    }

    public double getXPixelWidth(){
        return xPixelWidth;
    }

    public double getYPixelWidth(){
        return yPixelWidth;
    }

    public HashMap<Integer, ArrayList<GridPosition>> computeCurves() {
        HashMap<Integer, ArrayList<GridPosition>> components = getComponents();
        return util.curveFitting(components, 20, 3);
    }


    public HashMap<Integer, ArrayList<GridPosition>> computeLines(){
        HashMap<Integer, ArrayList<GridPosition>> components = getComponents();
        return util.linearRegression(components);
    }

    public HashMap<Integer, ArrayList<GridPosition>> getComponents(){
        HashMap<Integer, ArrayList<GridPosition>> components = new HashMap<Integer, ArrayList<GridPosition>>();
        HashSet<GridPosition> addedToComponent = new HashSet<GridPosition>();
        for(GridPosition g : gridValues.keySet()){
            if(!addedToComponent.contains(g)){
                HashSet<GridPosition> visited = new HashSet<GridPosition>();
                ArrayList<GridPosition> component = new ArrayList<GridPosition>();
                visited.add(g);
                LinkedList<GridPosition> toVisit = new LinkedList<GridPosition>();
                toVisit.add(g);
                component.add(g);
                addedToComponent.add(g);
                int angle = maxAng(g);
                if(gridValues.get(g)[angle] > 5){
                    while(toVisit.size() > 0){
                        GridPosition current = toVisit.poll();
                        for(GridPosition neighbour : getNeighbors(current)){
                            if(!visited.contains(neighbour)){
                                visited.add(neighbour);
                                if(angle == maxAng(neighbour) && gridValues.get(neighbour)[angle] > 2){
                                    toVisit.add(neighbour);
                                    component.add(neighbour);
                                    addedToComponent.add(neighbour);
                                }
                            }
                        }
                    }
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
                    components.put(getComponentId(), component);
                }
            }
        }
        return components;
    }

    public int getComponentId(){
        componentId++;
        return componentId;
    }

    public ArrayList<GridPosition> getNeighbors(GridPosition g){
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
        int[] angleArrray = gridValues.get(g);
        int max = 0;
        int value = 0;
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
        //System.out.println("Point "+a.getNewX()+","+a.getY());
        //System.out.println("Point "+b.getNewX()+","+b.getY());
        //System.out.println("X steps " + xSteps);
        //System.out.println("Y steps " + ySteps);
        if(ySteps >= xSteps){
            int step = 0;
            double xChange = ySteps == 0 ? 0 : (p2.getX() - p1.getX()) / ySteps;
            while(y <= yStop){
                double xVal = p1.getX() + (step*xChange);
                x = (int) ((xVal-xMin)/xPixelWidth);
                GridPosition g = new GridPosition(x, y);
                int[] angl;
                if(!gridValues.containsKey(g)){
                    angl = new int[angles];
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
            int step = 0;
            double yChange = (p2.getY() - p1.getY()) / xSteps;
            while(x != xStop){
                double yVal = p1.getY() + (step*yChange);
                y = (int) ((yVal-yMin)/yPixelWidth);
                GridPosition g = new GridPosition(x, y);
                int[] angl;
                //System.out.println("x,y: " + x+","+y);
                if(!gridValues.containsKey(g)){
                    angl = new int[angles];
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
