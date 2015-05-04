
/* Created by AndreasMathisen on 20/04/15.
*
* Class representing a single node on a road segment
*/

public class Node {
    private double lat;
    private double lon;
    private double x;
    private double y;

    public Node(double lat, double lon, double x, double y){
        this.lat = lat;
        this.lon = lon;
        this.x = x;
        this.y = y;
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

}