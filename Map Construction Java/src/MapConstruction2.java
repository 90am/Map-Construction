import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Andreas on 13/05/15.
 */
public class MapConstruction2 {

    private HashMap<Integer, ArrayList<Point>> data;
    private Grid3 grid;
    private LatLonPoint.Double minLatLon;
    private LatLonPoint.Double maxLatLon;

    public MapConstruction2(HashMap<Integer, ArrayList<Point>> data, LatLonPoint.Double minLatLon, LatLonPoint.Double maxLatLon) {
        this.data = data;
        this.minLatLon = minLatLon;
        this.maxLatLon = maxLatLon;
        HerningCyklerDataLoader load = new HerningCyklerDataLoader();
        grid = new Grid3(5, 5, 8, new UTMPoint(minLatLon), new UTMPoint(maxLatLon), 2);
        for(Integer key : data.keySet()){
            grid.addTrajectory(data.get(key));
        }
    }

    public HashMap<Point, Double> getResult(){
        return grid.getPoints();
    }

    private void sanityCheck(){
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
