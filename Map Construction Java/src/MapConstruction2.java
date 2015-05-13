import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.util.*;

/**
 * Created by Andreas on 13/05/15.
 */
public class MapConstruction2 {

    private HashMap<Integer, ArrayList<Point>> data;
    private Grid2 grid;
    private LatLonPoint.Double minLatLon;
    private LatLonPoint.Double maxLatLon;

    public MapConstruction2(HashMap<Integer, ArrayList<Point>> data, LatLonPoint.Double minLatLon, LatLonPoint.Double maxLatLon) {
        this.data = data;
        this.minLatLon = minLatLon;
        this.maxLatLon = maxLatLon;

        grid = new Grid2(20, 20, 8, new UTMPoint(minLatLon), new UTMPoint(maxLatLon), 5);
        for(Integer key : data.keySet()){
            grid.addTrajectory(data.get(key));
        }
    }

    public HashMap<Point, Double> getResult(){
        return grid.getPossiblePoints();
    }

}
