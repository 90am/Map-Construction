import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 21/05/15.
 *
 * Class for common methods
 */
public class Util {

    public Util(){}

    public double getAngle(Point p1, Point p2){
        double dx = p2.getX()-p1.getX();
        double dy = p2.getY()-p1.getY();
        return Math.atan2(dy, dx);
    }

    public HashMap<Integer, ArrayList<GridPosition>> linearRegression(HashMap<Integer, ArrayList<GridPosition>> data){
        HashMap<Integer, ArrayList<GridPosition>> result = new HashMap<Integer, ArrayList<GridPosition>>();
        for(Integer key : data.keySet()){
            if(data.get(key).size() > 1) {
                SimpleRegression regression = new SimpleRegression();
                double minX = Double.MAX_VALUE;
                double maxX = 0;
                for (GridPosition g : data.get(key)) {
                    if (g.getX() < minX)
                        minX = g.getX();
                    if (g.getX() > maxX)
                        maxX = g.getX();
                    regression.addData(g.getX(), g.getY());
                }
                double minY = regression.predict(minX);
                double maxY = regression.predict(maxX);
                ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
                temp.add(new GridPosition(minX, minY));
                temp.add(new GridPosition(maxX, maxY));
                result.put(key, temp);
            }
        }
        return result;
    }

    public HashMap<Integer, ArrayList<GridPosition>> curveFitting(HashMap<Integer, ArrayList<GridPosition>> data, int stepSize, int degree) {
        HashMap<Integer, ArrayList<GridPosition>> result = new HashMap<Integer, ArrayList<GridPosition>>();
        for(Integer key : data.keySet()) {
            if(data.get(key).size() > 1) {
                WeightedObservedPoints obs = new WeightedObservedPoints();
                double minX = Double.MAX_VALUE;
                double maxX = 0;
                for (GridPosition g : data.get(key)) {
                    if (g.getX() < minX)
                        minX = g.getX();
                    if (g.getX() > maxX)
                        maxX = g.getX();
                    obs.add(g.getX(), g.getY());
                }
                PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
                double[] coeff = fitter.fit(obs.toList());
                PolynomialFunction poly = new PolynomialFunction(coeff);
                double minY = poly.value(minX);
                ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
                temp.add(new GridPosition(minX, minY));
                double tempX = minX;
                double tempY;
                while(tempX+stepSize < maxX){
                    tempX += stepSize;
                    tempY = poly.value(tempX);
                    temp.add(new GridPosition(tempX, tempY));
                }
                double maxY = poly.value(maxX);
                temp.add(new GridPosition(maxX, maxY));
                result.put(key, temp);
            }
        }
        return result;
    }

    public HashMap<Integer, ArrayList<Point>> formatGridPositions(HashMap<Integer, ArrayList<GridPosition>> data, double xPixelWidth, double yPixelWidth, double xMin, double yMin){
        int pointId = 0;
        HashMap<Integer, ArrayList<Point>> result = new  HashMap<Integer, ArrayList<Point>>();
        for(Integer key : data.keySet()){
            ArrayList<Point> temp = new ArrayList<Point>();
            for(GridPosition g : data.get(key)) {
                UTMPoint current = new UTMPoint((g.getY() * yPixelWidth) + yMin, (g.getX() * xPixelWidth) + xMin, 32, 'N');
                LatLonPoint l = current.toLatLonPoint();
                Point p = new Point(l.getLatitude(), l.getLongitude(), current.easting, current.northing, "", pointId++, key);
                temp.add(p);
            }
            result.put(key, temp);
        }
        return result;
    }

}
