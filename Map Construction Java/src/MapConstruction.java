import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Andreas on 04/05/15.
 */
public class MapConstruction {

    private HashMap<Integer, ArrayList<Point>> data;
    HashMap<Integer, ArrayList<Point>> componentsFormatted;
    //private ArrayList<RoadSegment> result;
    private double sigma1;
    private double sigma2;
    private double M;
    private double k;
    private long roadSegmentId;
    private int ruteId;
    // min 56.1288653,8.9452581
    // max 56.146625,8.9885811
    public MapConstruction(HashMap<Integer, ArrayList<Point>> data){
        this.data = data;
        //this.result = new ArrayList<RoadSegment>();
        this.sigma1 = 5;
        this.sigma2 = 5;
        this.M = 1;
        this.k = 0.005;
        this.roadSegmentId = 0;
        ruteId  = 0;
        LatLonPoint.Double min = new LatLonPoint.Double(56.1288653,8.9452581);
        LatLonPoint.Double max = new LatLonPoint.Double(56.146625,8.9885811);
        Grid testGrid = new Grid(100, 100, 8, new UTMPoint(min), new UTMPoint(max));
        //Grid testGrid = new Grid(5, 5, 8, 5, 5, 100, 100);
        for(Integer key : data.keySet()){
            ArrayList<Point> list = data.get(key);
            for(int i=1; i<list.size();i++){
                testGrid.addSegment(list.get(i-1), list.get(i));
            }
        }
        HashMap<Integer, ArrayList<GridPosition>> components = testGrid.getComponents();
        componentsFormatted = formatComponents(components);
        System.out.println("Number of components found: " + components.keySet().size());

        /*sanityCheck();
        clarify();*/
    }

    // TODO fix such that x and y are mapped correctly by multiplying pixelwidth
    private HashMap<Integer, ArrayList<Point>> formatComponents(HashMap<Integer, ArrayList<GridPosition>> components){
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        int pointId = 0;
        for(Integer key : components.keySet()){
            ArrayList<Point> list = new ArrayList<Point>();
            for(GridPosition g : components.get(key)){
                UTMPoint current = new UTMPoint(g.getY()*100, g.getX()*100, 32, 'N');
                LatLonPoint l = current.toLatLonPoint();
                Point p =  new Point(l.getLatitude(), l.getLongitude(), g.getX()*100, g.getY()*100, "", pointId++, key);
                list.add(p);
            }
            result.put(key, list);
        }
        return result;
    }

    private void sanityCheck(){
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        for(Integer key : data.keySet()){
            ArrayList<Point> temp = new ArrayList<Point>();
            for(Point p : data.get(key)){
                if(temp.size() == 0){
                    p.setRuteId(getRuteId());
                    temp.add(p);
                }
                else{
                    Point prevP = temp.get(temp.size()-1);
                    Date d1 = getDateFromString(prevP.getTime());
                    Date d2 = getDateFromString(p.getTime());
                    long timeSpan = (d2.getTime() - d1.getTime()) / 1000;
                    if(timeSpan < 30){
                        p.setRuteId(prevP.getRuteId());
                        temp.add(p);
                    }
                    else{
                        result.put(prevP.getRuteId(), temp);
                        temp = new ArrayList<Point>();
                        p.setRuteId(getRuteId());
                        temp.add(p);
                    }
                }
            }
            if(temp.size() > 0)
                result.put(temp.get(0).getRuteId(), temp);

        }
        data = result;
    }

    private void clarify(){
        for(int j=0; j<2; j++){
            for(Integer key : data.keySet()){
                for(Point p : data.get(key)){
                    for(Integer key2 : data.keySet()){
                        if(key != key2){
                            ArrayList<Point> trace = data.get(key2);
                            double bestDistance = Double.MAX_VALUE;
                            Point bestPoint = null;
                            for(int i=1; i<trace.size()-1; i++){
                                Point tempPoint = getDistanceToTraceSegment(p, trace.get(i-1), trace.get(i));
                                double tempDistance = getDistancePointToPoint(p.getX(), p.getY(), tempPoint.getX(), tempPoint.getY());
                                if(tempDistance < bestDistance){
                                    bestDistance = tempDistance;
                                    bestPoint = tempPoint;
                                }
                            }
                            if(bestPoint != null){
                                double att = attractionForce(bestDistance);
                                p.updatePoint(att, bestPoint, bestDistance);
                                double spring = springForce(att*getDistancePointToPoint(p.getX(), p.getY(), bestPoint.getNewX(), bestPoint.getNewY()));
                                p.updatePoint(spring, p, att*bestDistance);
                                //System.out.println("Updating point with att force " + att + " and sprince force " + spring);
                            }
                        }
                    }
                }
            }
        }
    }

    private double attractionForce(double distance){
        return Math.exp(-Math.pow(distance,2)/(2*Math.pow(20,2)));
    }

    private double springForce(double distance){
        return 0.005*distance;
    }

    // Calculates the shortest distance from p to the trace segment from p1 to p2. Returns the best point, which is either p1, p2 or the point orthogonal on the trace segment
    private Point getDistanceToTraceSegment(Point p, Point p1, Point p2){
        double bestDistance = Double.MAX_VALUE;
        Point bestPoint = null;
        // Calculate distance, if less than result update
        double vx = p2.getX() - p1.getX();
        double vy = p2.getY() - p1.getY();
        double wx = p.getX() - p1.getX();
        double wy = p.getY() - p1.getY();
        double c1 = vx * wx + vy * wy;
        double c2 = vx * vx + vy * vy;
        // p is before line
        if(c1 <= 0){
            bestDistance = getDistancePointToPoint(p.getX(), p.getY(), p1.getX(), p1.getY());
            bestPoint = p1;
        }
        // p is after line
        else if(c2 <= c1){
            bestDistance = getDistancePointToPoint(p.getX(), p.getY(), p2.getX(), p2.getY());
            bestPoint = p2;
        }
        // p is in between endpoints of line
        else{
            double b = c1/c2;
            double x = p1.getX()+ vx * b;
            double y = p1.getY()+ vy * b;
            bestDistance = getDistancePointToPoint(p.getX(), p.getY(), x, y);
            bestPoint = new Point(0, 0, x, y, "", 0, 0);
        }
        return bestPoint;
    }

    // Calculates the distance from one point to another
    private double getDistancePointToPoint(double x1, double y1, double x2, double y2){
        double xd = x2-x1;
        double yd = y2-y1;
        return Math.sqrt(xd * xd + yd * yd);
    }

    public int getRuteId(){
        ruteId++;
        return ruteId;
    }

    public long getRoadSegmentId(){
        roadSegmentId++;
        return roadSegmentId;
    }

    public HashMap<Integer, ArrayList<Point>> getData(){
        return data;
    }

    public HashMap<Integer, ArrayList<Point>> getComponents(){
        return componentsFormatted;
    }

    private Date getDateFromString(String s){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            date = format.parse(s);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }
}
