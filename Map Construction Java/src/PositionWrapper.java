import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 * Created by Andreas on 27/05/15.
 */
public class PositionWrapper implements Clusterable {
    private double[] points;
    private GridPosition pos;

    public PositionWrapper(GridPosition pos) {
        this.pos = pos;
        this.points = new double[] { pos.getX(), pos.getY() };
    }

    public GridPosition getGridPosition() {
        return pos;
    }

    public double[] getPoint() {
        return points;
    }
}
