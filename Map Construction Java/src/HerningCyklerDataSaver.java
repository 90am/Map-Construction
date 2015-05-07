import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 05/05/15.
 */
public class HerningCyklerDataSaver {
    private String jdbc_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String herningCykler_db_url = "jdbc:sqlserver://herningcykler.ecosense.cs.au.dk;databaseName=HerningCyklerDEBUG";
    private String herningCykler_user = "HerningUser";
    private String herningCykler_password = "herningpass";

    public HerningCyklerDataSaver(){

    }

    public void insertUpdatedPoints(HashMap<Integer, ArrayList<Point>> points) {
        Connection conn = null;
        Statement stmt = null;
        for (Integer key : points.keySet()) {
            for (Point p : points.get(key)) {
                UTMPoint UTMTemp = new UTMPoint(p.getNewY(), p.getNewX(), 32, 'N');
                LatLonPoint LatLonTemp = UTMTemp.toLatLonPoint();
                try {
                    // Register jdbc driver and open connection
                    Class.forName(jdbc_driver);
                    conn = DriverManager.getConnection(herningCykler_db_url, herningCykler_user, herningCykler_password);
                    // Execute update
                    stmt = conn.createStatement();
                    String sql = " INSERT INTO updatedPoint (PointId, RuteId, X, Y, Lat, Lon, TimePoint) VALUES ("+p.getPointId()+", "+p.getRuteId()+
                            ", "+p.getNewX()+", "+p.getNewY()+", "+LatLonTemp.getLatitude()+", "+LatLonTemp.getLongitude()+", '"+p.getTime()+"')";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stmt != null)
                            stmt.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                    try {
                        if (conn != null)
                            conn.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
            }
        }
    }

    public void insertComponents(HashMap<Integer, ArrayList<Point>> points) {
        Connection conn = null;
        Statement stmt = null;
        for (Integer key : points.keySet()) {
            for (Point p : points.get(key)) {
                UTMPoint UTMTemp = new UTMPoint(p.getNewY(), p.getNewX(), 32, 'N');
                LatLonPoint LatLonTemp = UTMTemp.toLatLonPoint();
                try {
                    // Register jdbc driver and open connection
                    Class.forName(jdbc_driver);
                    conn = DriverManager.getConnection(herningCykler_db_url, herningCykler_user, herningCykler_password);
                    // Execute update
                    stmt = conn.createStatement();
                    String sql = " INSERT INTO components (PointId, RuteId, X, Y, Lat, Lon) VALUES ("+p.getPointId()+", "+p.getRuteId()+
                            ", "+p.getNewX()+", "+p.getNewY()+", "+LatLonTemp.getLatitude()+", "+LatLonTemp.getLongitude()+")";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stmt != null)
                            stmt.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                    try {
                        if (conn != null)
                            conn.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
            }
        }
    }
}
