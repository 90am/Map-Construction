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

    public Point(double lat, double lon, double x, double y, String time, int pointId, int ruteId){
        this.lat = lat;
        this.lon = lon;
        this.x = x;
        this.y = y;
        this.time = time;
        this.pointId = pointId;
        this.ruteId = ruteId;
        this.newX = x;
        this.newY = y;
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

    public boolean equals(Point p){
        return (pointId == p.getPointId() && ruteId == p.getRuteId());
    }

    public void updatePoint(double force, Point p, double distance){
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

