/**
 * Created by Andreas on 01/05/15.
 */
public class Program {

    public static void main(String[] args) {
        HerningCyklerDataLoader load = new HerningCyklerDataLoader();
        HerningCyklerDataSaver save = new HerningCyklerDataSaver();
        System.out.println("Number of trips: "+load.getAllTrips().keySet().size());
        MapConstruction test = new MapConstruction(load.getAllTrips());
        save.insertComponents(test.getComponents());
    }
}
