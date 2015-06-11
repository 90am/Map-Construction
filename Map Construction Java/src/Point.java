import com.bbn.openmap.proj.coords.UTMPoint;

/**
 * Created by Andreas on 01/05/15.
 */

public class Point {
    private double lat;
    private double lon;
    private double x;
    private double y;
    private String time;
    private int pointId;
    private int ruteId;
    private double newX;
    private double newY;
    private double bearing;
    private double accuracy;

    public Point(double lat, double lon, double x, double y, String time, int pointId, int ruteId, double bearing, double accuracy){
        this.lat = lat;
        this.lon = lon;
        this.x = x;
        this.y = y;
        this.time = time;
        this.pointId = pointId;
        this.ruteId = ruteId;
        this.newX = x;
        this.newY = y;
        this.bearing = bearing;
        this.accuracy = accuracy;
    }

    public void setRuteId(int ruteId){
        this.ruteId = ruteId;
    }

    public double getBearing(){
        return bearing;
    }

    public double getAccuracy(){
        return accuracy;
    }

    public double getLat(){
        return lat;
    }

    public double getLon(){
        return lon;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getNewX(){
        return newX;
    }

    public double getNewY(){
        return newY;
    }

    public int getRuteId(){
        return ruteId;
    }

    public String getTime(){
        return time;
    }

    public int getPointId(){
        return pointId;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Point))
            return false;
        Point p = (Point) obj;
        return (x == p.getX() && y == p.getY());
    }

    @Override
    public int hashCode() {
        return (int) Math.pow(x * 31,y);
    }

    public void updatePoint(double force, Point p, double distance){
        if(p == null)
            System.out.println("P IS NULL!!!");
        double moveDistance = Math.sqrt(Math.pow(force*distance, 2)/2);
        if(p.getNewX() > newX){
            newX += moveDistance;
        }
        else{
            newX -= moveDistance;
        }
        if(p.getNewY() > newY){
            newY += moveDistance;
        }
        else{
            newY -= moveDistance;
        }
    }

}

