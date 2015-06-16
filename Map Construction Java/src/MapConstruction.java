import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Andreas on 04/05/15.
 *
 * Class representing the original algorithm
 *
 */
public class MapConstruction {

    private Util util;
    private HashMap<Integer, ArrayList<Point>> data;
    private Grid grid;
    private double sigma1;
    private double sigma2;
    private double M;
    private double k;
    private double xPixelWidth;
    private double yPixelWidth;
    private LatLonPoint.Double minLatLon;
    private LatLonPoint.Double maxLatLon;


    public MapConstruction(HashMap<Integer, ArrayList<Point>> data, LatLonPoint.Double minLatLon, LatLonPoint.Double maxLatLon){
        util = new Util();
        this.data = selectSegments(data);
        this.minLatLon = minLatLon;
        this.maxLatLon = maxLatLon;
        grid = new Grid(3, 3, 8, new UTMPoint(minLatLon), new UTMPoint(maxLatLon));
        for(Integer key : data.keySet()){
            ArrayList<Point> list = data.get(key);
            for(int i=1; i<list.size();i++){
                grid.addSegment(list.get(i-1), list.get(i));
            }
        }
        grid.removeInsignificantBins();
    }

    public HashMap<Integer, ArrayList<Point>> getResult(){
        return grid.getResult();
    }

    public HashMap<Integer, ArrayList<Point>> selectSegments(HashMap<Integer, ArrayList<Point>> data){
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        int ruteId = 1;
        for(Integer key : data.keySet()){
            ArrayList<Point> list = data.get(key);
            ArrayList<Point> temp = new ArrayList<Point>();
            temp.add(list.get(0));
            for(int i=1; i<list.size();i++){
                if(checkSegment(list.get(i - 1), list.get(i))){
                    temp.add(list.get(i));
                }
                else{
                    if(temp.size() > 1)
                        result.put(ruteId++, temp);
                    temp = new ArrayList<Point>();
                    temp.add(list.get(i));
                }
            }
            if(temp.size() > 1)
                result.put(ruteId++, temp);
        }
        return result;
    }

    public boolean checkSegment(Point p1, Point p2){
        boolean result = false;
        double speedThreshold = 12;
        Date d1 = getDateFromString(p1.getTime());
        Date d2 = getDateFromString(p2.getTime());
        long timeSpan = (d2.getTime() - d1.getTime()) / 1000;
        double distance = util.getDistancePointToPoint(p1, p2);
        double speed = distance/timeSpan;
        if(speed < speedThreshold) {
            result = true;
        }
        return result;
    }

    /*private void clarify(){
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
    }*/



    private double attractionForce(double distance){
        return Math.exp(-Math.pow(distance,2)/(2*Math.pow(20,2)));
    }

    private double springForce(double distance){
        return 0.005*distance;
    }

    public HashMap<Integer, ArrayList<Point>> getData(){
        return data;
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
