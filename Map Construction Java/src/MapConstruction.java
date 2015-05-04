import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 04/05/15.
 */
public class MapConstruction {

    private HashMap<Integer, ArrayList<Point>> data;
    private ArrayList<RoadSegment> result;
    private double sigma1;
    private double sigma2;
    private double M;
    private double k;

    public MapConstruction(HashMap<Integer, ArrayList<Point>> data){
        this.data = data;
        this.result = new ArrayList<RoadSegment>();
        this.sigma1 = 5;
        this.sigma2 = 5;
        this.M = 1;
        this.k = 0.005;
    }

    private void clarify(){
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
                        System.out.println("Attraction forces: "+attractionForce(bestDistance));
                    }
                }
            }
        }
    }

    private double attractionForce(double distance){
        return Math.exp(-Math.pow(distance,2)/(2*Math.pow(20,2)));
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
        return Math.sqrt(xd*xd+yd*yd);
    }

}