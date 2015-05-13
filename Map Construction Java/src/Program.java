import java.util.ArrayList;
import java.util.HashMap;

/*
 * Created by Andreas on 01/05/15.
 */
public class Program {



    public static void main(String[] args) {
        /*HashMap<Integer, ArrayList<Point>> testDataSet = new HashMap<Integer, ArrayList<Point>>();
        ArrayList<Point> testList = new ArrayList<Point>();
        testList.add(new Point(0, 0, 10, 10, "", 1, 1));
        testList.add(new Point(0, 0, 50, 50, "", 2, 1));
        testList.add(new Point(0, 0, 70, 70, "", 3, 1));
        testDataSet.put(1, testList);
        MapConstruction test = new MapConstruction(testDataSet);
        HerningCyklerDataSaver save = new HerningCyklerDataSaver();

        MapConstruction test = new MapConstruction(load.getAllTrips());
        save.insertComponents(test.getComponents());

        save.insertUpdatedPoints(load.getAllTrips());
        */

        HerningCyklerDataLoader load = new HerningCyklerDataLoader();
        HerningCyklerDataSaver save = new HerningCyklerDataSaver();
        System.out.println("Number of trips: "+load.getAllTrips().keySet().size());
        save.insertUpdatedPoints(load.getAllTrips());

    }
}
