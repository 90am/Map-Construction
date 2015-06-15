import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Andreas on 13/05/15.
 */
public class MapConstruction2 {

    private Util util;
    private HashMap<Integer, ArrayList<Point>> data;
    private Grid3 grid;
    private LatLonPoint.Double minLatLon;
    private LatLonPoint.Double maxLatLon;

    public MapConstruction2(HashMap<Integer, ArrayList<Point>> data, LatLonPoint.Double minLatLon, LatLonPoint.Double maxLatLon) {
        this.util = new Util();
        this.data = data;
        this.minLatLon = minLatLon;
        this.maxLatLon = maxLatLon;
        grid = new Grid3(5, 5, 8, new UTMPoint(minLatLon), new UTMPoint(maxLatLon), 7);
        /*for(Integer key : data.keySet()){
            ArrayList<Point> list = data.get(key);
            for(int i=1; i<list.size();i++){
                if(checkSegment(list.get(i - 1), list.get(i))){
                    grid.addSegment(list.get(i - 1), list.get(i));
                }
            }
        }*/
    }

    public HashMap<Integer, ArrayList<Point>> getResult(){
        return grid.getResult();
    }

    public HashMap<Point, Double> getPoints(){
        return grid.getPoints();
    }

    public HashMap<Integer, ArrayList<Point>> selectSegments(HashMap<Integer, ArrayList<Point>> data){
        data = accuracyFilter(data);
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
                    result.put(ruteId++, temp);
                    temp = new ArrayList<Point>();
                    temp.add(list.get(i));
                }
            }
            result.put(ruteId++, temp);
        }
        return result;
    }

    public boolean checkSegment(Point p1, Point p2){
        boolean result = false;
        double speedThreshold = 12;
        double angleThreshold = 30;
        Date d1 = getDateFromString(p1.getTime());
        Date d2 = getDateFromString(p2.getTime());
        long timeSpan = (d2.getTime() - d1.getTime()) / 1000;
        double distance = util.getDistancePointToPoint(p1, p2);
        double speed = distance/timeSpan;
        double angle = util.getAngleInDegrees(p1, p2);
        double angle2 = angle+180;
        if(angle2 > 360){
            angle2 = angle2 - 360;
        }
        if(speed < speedThreshold) {
            if (p1.getBearing() != 0 && p2.getBearing() != 0) {
                double angDif1 = Math.abs(p1.getBearing()-angle);
                double angDif2 = Math.abs(p1.getBearing()-angle2);
                if(angDif1 < angleThreshold || angDif2 < angleThreshold){
                    angDif1 = Math.abs(p2.getBearing()-angle);
                    angDif2 = Math.abs(p2.getBearing()-angle2);
                    if(angDif1 < angleThreshold || angDif2 < angleThreshold){
                        result = true;
                    }
                }
            }
            else{
                result = true;
            }
        }
        return result;
    }

    private HashMap<Integer, ArrayList<Point>>  accuracyFilter(HashMap<Integer, ArrayList<Point>>  data){
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        for(Integer key : data.keySet()){
            ArrayList<Point> temp = new ArrayList<Point>();
            for(Point p : data.get(key)){
                if(p.getAccuracy() < 10){
                    temp.add(p);
                }
            }
            result.put(key, temp);
        }
        return result;
    }

    private void speedFilter(){
        int ruteId = 0;
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        for(Integer key : data.keySet()){
            ArrayList<Point> temp = new ArrayList<Point>();
            for(Point p : data.get(key)){
                if(temp.size() == 0){
                    p.setRuteId(ruteId++);
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
                        p.setRuteId(ruteId++);
                        temp.add(p);
                    }
                }
            }
            if(temp.size() > 0)
                result.put(temp.get(0).getRuteId(), temp);

        }
        data = result;
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
