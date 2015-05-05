import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 01/05/15.
 */
public class HerningCyklerDataLoader {

    private String jdbc_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String herningCykler_db_url = "jdbc:sqlserver://herningcykler.ecosense.cs.au.dk;databaseName=HerningCyklerDEBUG";
    private String herningCykler_user = "HerningUser";
    private String herningCykler_password = "herningpass";
    private HashMap<Integer, ArrayList<Point>> allTrips;

    public HerningCyklerDataLoader(){
        allTrips = new HashMap<Integer, ArrayList<Point>>();
        addAllTrips();
    }

    private void addAllTrips(){
        System.out.println("Loading all trips");
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register jdbc driver and open connection
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(herningCykler_db_url, herningCykler_user, herningCykler_password);
            // Execute query
            stmt = conn.createStatement();
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
                if(currentRuteId == ruteId){
                    tempList.add(p);
                }
                else{
                    allTrips.put(currentRuteId, tempList);
                    currentRuteId = ruteId;
                    tempList = new ArrayList<Point>();
                    tempList.add(p);
                }
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
    }

    public HashMap<Integer, ArrayList<Point>> getAllTrips(){
        return allTrips;
    }
}

