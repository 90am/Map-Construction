/**
 * Created by Andreas on 07/05/15.
 */
public class GridPosition {

    private double x;
    private double y;

    public GridPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof GridPosition))
            return false;
        GridPosition p = (GridPosition) obj;
        return (x==p.getX() && y==p.getY());
    }

    @Override
    public int hashCode() {
        return (int) Math.pow(x * 31,y);
    }
}
