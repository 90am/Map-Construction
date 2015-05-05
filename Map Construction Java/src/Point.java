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

    public Point(double lat, double lon, double x, double y, String time, int pointId, int ruteId){
        this.lat = lat;
        this.lon = lon;
        this.x = x;
        this.y = y;
        this.time = time;
        this.pointId = pointId;
        this.ruteId = ruteId;
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

    public int getRuteId(){
        return ruteId;
    }

    public String getTime(){
        return time;
    }

    public int getPointId(){
        return pointId;
    }

    public boolean equals(Point p){
        return (pointId == p.getPointId() && ruteId == p.getRuteId());
    }
}

