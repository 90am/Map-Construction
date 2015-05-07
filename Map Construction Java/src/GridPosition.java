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

    public boolean equals(GridPosition p){
        return (x==p.getX() && y==p.getY());
    }
}
