import com.bbn.openmap.proj.coords.UTMPoint;

import java.util.*;

/**
 * Created by Andreas on 07/05/15.
 */
public class Grid {
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
        gridValues = new HashMap<GridPosition, int[]>();
        this.xPixelWidth = xPixelWidth;
        this.yPixelWidth = yPixelWidth;
        this.angles = angles;
        this.xMin = min.easting;
        this.xMax = max.easting;
        this.yMin = min.northing;
        this.yMax = max.northing;
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

    public HashMap<Integer, ArrayList<GridPosition>> getComponents(){
        HashMap<Integer, ArrayList<GridPosition>> components = new HashMap<Integer, ArrayList<GridPosition>>();
        HashSet<GridPosition> addedToComponent = new HashSet<GridPosition>();
        System.out.println(gridValues.keySet().size());
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
                while(toVisit.size() > 0){
                    GridPosition current = toVisit.poll();
                    System.out.println(current.getX());
                    System.out.println(current.getY());
                    for(GridPosition neighbour : getNeighbors(current)){
                        if(!visited.contains(neighbour)){
                            visited.add(neighbour);
                            if(angle == maxAng(neighbour)){
                                toVisit.add(neighbour);
                                component.add(neighbour);
                                addedToComponent.add(neighbour);
                            }
                        }
                    }
                }
                components.put(getComponentId(), component);
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
                if(!(i==(int)g.getX() && j==(int)g.getY()) && gridValues.containsKey(new GridPosition(i, j))){
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
        System.out.println(p1.getX());
        System.out.println(p1.getY());
        System.out.println(p2.getX());
        System.out.println(p2.getY());
        double ang = getAngle(p1, p2);
        int angIdx = (int)Math.floor((angles * ((ang + Math.PI / (angles * 2)) / (Math.PI)))) % angles;
        double x = Math.floor((p1.getX()-xMin)/xPixelWidth);
        double y = Math.floor((p1.getY()-yMin)/yPixelWidth);
        double xStop = Math.floor((p2.getX()-xMin)/xPixelWidth);
        double yStop = Math.floor((p2.getY()-yMin)/yPixelWidth);
        int xSteps = (int) Math.abs(xStop-x);
        int ySteps = (int) Math.abs(yStop-y);
        if(ySteps >= xSteps){
            int step = 0;
            double xChange = ySteps == 0 ? 0 : (p2.getX() - p1.getY()) / ySteps;
            while(y <= yStop){
                double xVal = p1.getX() + (step*xChange);
                x = Math.floor((xVal-xMin)/xPixelWidth);
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
            double yChange = (p2.getX() - p1.getY()) / xSteps;
            while(step <= xSteps){
                double yVal = p1.getY() + (step*yChange);
                y = Math.floor((yVal-yMin)/yPixelWidth);
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
                x += Math.signum(xStop-x);
                step++;
            }
        }

    }

    public double getAngle(Point p1, Point p2){
        double dx = p2.getX()-p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.atan2(dy, dx);
    }

}
