/**
 * Created by Andreas on 01/05/15.
 */
public class Program {


    public static void main(String[] args) {
        HerningCyklerDataLoader data = new HerningCyklerDataLoader();
        System.out.print("Number of trips: "+data.getAllTrips().keySet().size());
        MapConstruction test = new MapConstruction(data.getAllTrips());
    }
}
