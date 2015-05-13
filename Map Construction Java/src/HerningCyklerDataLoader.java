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
    private String herningCykler_user = "HerningUser";
    private String herningCykler_password = "herningpass";
    private HashMap<Integer, ArrayList<Point>> allTrips;

    public HerningCyklerDataLoader(){
        allTrips = new HashMap<Integer, ArrayList<Point>>();
        addAllTrips();
    }

    private void addAllTrips(){
        System.out.println("Loading all trips");
        LatLonPoint.Double min = new LatLonPoint.Double(56.1288653,8.9452581);
        LatLonPoint.Double max = new LatLonPoint.Double(56.146625,8.9885811);
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register jdbc driver and open connection
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(herningCykler_db_url, herningCykler_user, herningCykler_password);
            // Execute query
            stmt = conn.createStatement();
            //String sql ="SELECT X, Y, Lat, Lon, TimePoint, PunktId, RuteId FROM Punkt WHERE RuteId = 739321 ORDER BY PunktId";
            String sql ="SELECT X, Y, Lat, Lon, TimePoint, PunktId, RuteId FROM Punkt ORDER BY PunktId";
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
                Point p = new Point(lat, lon, x, y, time, pointId, ruteId);
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

