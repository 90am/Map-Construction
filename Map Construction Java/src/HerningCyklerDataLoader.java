import com.bbn.openmap.proj.coords.LatLonPoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 01/05/15.
 */
public class HerningCyklerDataLoader {

    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://herningcykler.ecosense.cs.au.dk;databaseName=HerningCykler";
    private String debugurl= "jdbc:sqlserver://herningcykler.ecosense.cs.au.dk;databaseName=HerningCyklerDEBUG";
    private String user = "HerningUser";
    private String pass = "herningpass";
    private LatLonPoint.Double min;
    private LatLonPoint.Double max;

    //PostgreSQL
    /*static final String driver = "org.postgresql.Driver";
    static final String url = "jdbc:postgresql://localhost:5432/HerningCykler";

    static final String user = "Andreas";
    static final String pass = "090490";*/

    // Small test area
    // min 56.135931,8.969493
    // max 56.136672,8.975212

    // Middle test area
    // min 56.135532, 8.965375
    // max 56.138324, 8.976705

    //min = new LatLonPoint.Double(56.135532, 8.965375);
    //max = new LatLonPoint.Double(56.138324, 8.976705);

    //min = new LatLonPoint.Double(56.140736, 8.965670);
    //max = new LatLonPoint.Double(56.143342, 8.973137);

    //min = new LatLonPoint.Double(56.134916, 8.966616);
    //max = new LatLonPoint.Double(56.145712, 8.981679);

    //min = new LatLonPoint.Double(56.133984, 8.966423);
    //max = new LatLonPoint.Double(56.139340, 8.985434);

    // Big test area
    // min 56.1288653,8.9452581
    // max 56.146625,8.9885811

    // 56.133422, 8.964642
    // 56.146286, 8.988460
    // 56.145640, 8.981551


    public HerningCyklerDataLoader(){
        min = new LatLonPoint.Double(56.135532, 8.965375);
        max = new LatLonPoint.Double(56.138324, 8.976705);
    }

    public LatLonPoint.Double getMin(){
        return min;
    }

    public LatLonPoint.Double getMax(){
        return max;
    }

    public HashMap<Point, Double> loadPoints(){
        HashMap<Point, Double> result = new HashMap<Point, Double>();
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register jdbc driver and open connection
            Class.forName(driver);
            conn = DriverManager.getConnection(debugurl, user, pass);
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
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
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


    public HashMap<Integer, ArrayList<Point>> loadMatchedGroundTruth() {
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        Connection conn = null;
        Statement stmt = null;
        int numberOfSegments = 0;
        int pointId = 1;
        try{
            // Register jdbc driver and open connection
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            // Execute query
            stmt = conn.createStatement();
            String sql = "SELECT VejstykkeId, X, Y, Lat, Lon FROM vwKnudeJoinedVejstykke WHERE " +
                    "Vejklasse not like 'Trafik%' AND " +
                    "Vejklasse not like 'Non-cykl%' AND " +
                    "VejstykkeId IN (SELECT VejstykkeId FROM PlotVejstykke) " +
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

    public HashMap<Integer, ArrayList<Point>> loadAllTrips(){
        HashMap<Integer, ArrayList<Point>> result = new HashMap<Integer, ArrayList<Point>>();
        System.out.println("Loading all trips");
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register jdbc driver and open connection
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
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
                            result.put(currentRuteId, tempList);
                            tempList = new ArrayList<Point>();
                        }
                        currentRuteId = ruteId;
                        tempList.add(p);
                    }
                }
            }
            result.put(currentRuteId, tempList);
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
}

