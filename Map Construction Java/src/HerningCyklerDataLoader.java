import com.bbn.openmap.proj.coords.LatLonPoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 01/05/15.
 */
public class HerningCyklerDataLoader {

    private String jdbc_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String herningCykler_db_url = "jdbc:sqlserver://herningcykler.ecosense.cs.au.dk;databaseName=HerningCykler";
    private String herningCyklerDEBUG_db_url = "jdbc:sqlserver://herningcykler.ecosense.cs.au.dk;databaseName=HerningCyklerDEBUG";
    private String herningCykler_user = "HerningUser";
    private String herningCykler_password = "herningpass";
    private HashMap<Integer, ArrayList<Point>> allTrips;
    private LatLonPoint.Double min;
    private LatLonPoint.Double max;

    // Small test area
    // min 56.135931,8.969493
    // max 56.136672,8.975212

    // Middle test area
    // min 56.135532, 8.965375
    // max 56.138324, 8.976705

    // Big test area
    // min 56.1288653,8.9452581
    // max 56.146625,8.9885811

    public HerningCyklerDataLoader(){
        allTrips = new HashMap<Integer, ArrayList<Point>>();
        min = new LatLonPoint.Double(56.135532, 8.965375);
        max = new LatLonPoint.Double(56.138324, 8.976705);
    }

    public LatLonPoint.Double getMin(){
        return min;
    }

    public LatLonPoint.Double getMax(){
        return max;
    }

    public HashMap<Point, Double> getPoints(){
        HashMap<Point, Double> result = new HashMap<Point, Double>();
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register jdbc driver and open connection
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(herningCyklerDEBUG_db_url, herningCykler_user, herningCykler_password);
            // Execute query
            stmt = conn.createStatement();
            //String sql ="SELECT X, Y, Lat, Lon, TimePoint, PunktId, RuteId FROM Punkt WHERE RuteId = 739321 ORDER BY PunktId";
            String sql ="SELECT PointId, X, Y, Lat, Lon, Prob FROM pointProb";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while(rs.next()){
                int pointId = rs.getInt("PointId");
                double lat = rs.getDouble("Lat");
                double lon = rs.getDouble("Lon");
                double x = rs.getDouble("X");
                double y = rs.getDouble("Y");
                double prob = rs.getDouble("Prob");
                Point p = new Point(lat, lon, x, y, "", pointId, 0, 0, 0);
                result.put(p, prob);
            }
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return result;
    }

    public HashMap<Integer, ArrayList<Point>> loadGroundTruth(){
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        Connection conn = null;
        Statement stmt = null;
        int numberOfSegments = 0;
        int pointId = 1;
        try{
            // Register jdbc driver and open connection
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(herningCykler_db_url, herningCykler_user, herningCykler_password);
            // Execute query
            stmt = conn.createStatement();
            String sql = "SELECT VejstykkeId, X, Y, Lat, Lon FROM vwKnudeJoinedVejstykke WHERE " +
                    "Vejklasse not like 'Trafik%' AND " +
                    "Vejklasse not like 'Non-cykl%' " +
                    "ORDER BY VejstykkeId, NrLangsVejstykke";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            ArrayList<Point> tempList = new ArrayList<Point>();
            int currentSegmentId = 0;
            while(rs.next()){
                int roadSegmentId = rs.getInt("VejstykkeId");
                double lat = rs.getDouble("Lat");
                double lon = rs.getDouble("Lon");
                double x = rs.getDouble("X");
                double y = rs.getDouble("Y");
                //Road segment already created and new node found
                Point p = new Point(lat, lon, x, y, "", pointId++, roadSegmentId, 0, 0);
                if(p.getLat() > min.getLatitude() && p.getLat() < max.getLatitude() && p.getLon() > min.getLongitude() && p.getLon() < max.getLongitude()) {
                    if(roadSegmentId == currentSegmentId){
                        tempList.add(p);
                    }
                    //New road segment
                    else{
                        if(currentSegmentId != 0) {
                            result.put(currentSegmentId, tempList);
                        }
                        currentSegmentId = roadSegmentId;
                        tempList = new ArrayList<Point>();
                        tempList.add(p);
                        numberOfSegments++;
                    }
                }
            }
            if(tempList.size() > 0){
                result.put(currentSegmentId, tempList);
            }
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return result;
    }

    public void addAllTrips(){
        System.out.println("Loading all trips");
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register jdbc driver and open connection
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(herningCykler_db_url, herningCykler_user, herningCykler_password);
            // Execute query
            stmt = conn.createStatement();
            //String sql ="SELECT X, Y, Lat, Lon, TimePoint, PunktId, RuteId, Accuracy FROM Punkt WHERE RuteId = 739321 ORDER BY PunktId";
            String sql ="SELECT X, Y, Lat, Lon, TimePoint, PunktId, RuteId, Bearing, Accuracy FROM Punkt ORDER BY PunktId";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            ArrayList<Point> tempList = new ArrayList<Point>();
            int currentRuteId = 0;
            while(rs.next()){
                double lat = rs.getDouble("Lat");
                double lon = rs.getDouble("Lon");
                double x = rs.getDouble("X");
                double y = rs.getDouble("Y");
                String time = rs.getString("TimePoint");
                int pointId = rs.getInt("PunktId");
                int ruteId = rs.getInt("RuteID");
                double bearing = rs.getDouble("Bearing");
                double accuracy = rs.getDouble("Accuracy");
                Point p = new Point(lat, lon, x, y, time, pointId, ruteId, bearing, accuracy);
                if(p.getLat() > min.getLatitude() && p.getLat() < max.getLatitude() && p.getLon() > min.getLongitude() && p.getLon() < max.getLongitude()) {
                    if (currentRuteId == ruteId) {
                        tempList.add(p);
                    } else {
                        if(currentRuteId != 0){
                            allTrips.put(currentRuteId, tempList);
                            tempList = new ArrayList<Point>();
                        }
                        currentRuteId = ruteId;
                        tempList.add(p);
                    }
                }
            }
            allTrips.put(currentRuteId, tempList);
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public HashMap<Integer, ArrayList<Point>> getAllTrips(){
        return allTrips;
    }
}

