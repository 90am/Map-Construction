import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * Created by Andreas on 01/05/15.
 */
public class Program {

    public static void main(String[] args) {
        // TEST OF SIMPLE CONVERSIONS
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

        // LOAD RAW GPS TRACES INTO UPDATEDPOINTS TABLE
        /*HerningCyklerDataLoader load = new HerningCyklerDataLoader();
        load.addAllTrips();
        HerningCyklerDataSaver save = new HerningCyklerDataSaver();
        System.out.println("Number of trips: "+load.getAllTrips().keySet().size());
        save.insertUpdatedPoints(load.getAllTrips());*/

        //GRID WITH ANGLE TEST
        /*HerningCyklerDataLoader load = new HerningCyklerDataLoader();
        load.addAllTrips();
        HerningCyklerDataSaver save = new HerningCyklerDataSaver();
        System.out.println("Number of trips: "+load.getAllTrips().keySet().size());
        MapConstruction test = new MapConstruction(load.getAllTrips());
        save.insertComponents(test.getResult()); */

        Util util = new Util();
        util.printTimestamp();
        HerningCyklerDataLoader load = new HerningCyklerDataLoader();
        HashMap<Integer, ArrayList<Point>> trips = load.loadAllTrips();
        HerningCyklerDataSaver save = new HerningCyklerDataSaver();
        System.out.println("Number of trips: " + trips.keySet().size());
        util.printTimestamp();
        System.out.println("Add trajectory data");
        MapConstruction map = new MapConstruction(trips, load.getMin(), load.getMax());
        util.printTimestamp();
        System.out.println("Compute curves from grid");
        HashMap<Integer, ArrayList<Point>> result = map.getResult();
        util.printTimestamp();
        System.out.println("Evaluation");
        Evaluation eval = new Evaluation(load.loadMatchedGroundTruth(), result);
        util.printTimestamp();
        System.out.println("Save segments");
        save.insertSegments(result);
        util.printTimestamp();

       /*HerningCyklerDataLoader load = new HerningCyklerDataLoader();
        HerningCyklerDataSaver save = new HerningCyklerDataSaver();
        HashMap<Integer, ArrayList<Point>> result = load.loadAllTrips();
        MapConstruction2 map = new MapConstruction2(result, load.getMin(), load.getMax());
        save.insertSegments(map.selectSegments(result));*/
    }
}
