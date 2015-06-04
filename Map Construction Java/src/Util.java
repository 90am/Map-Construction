import com.bbn.openmap.proj.coords.LatLonPoint;
import com.bbn.openmap.proj.coords.UTMPoint;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Andreas on 21/05/15.
 *
 * Class for common methods
 */
public class Util {

    public Util(){}

    public double getAngle(Point one, Point two){
        Point p1 = one;
        Point p2 = two;
        if(p1.getY() > p2.getY()){
            p1 = two;
            p2 = one;
        }
        double dx = p2.getX()-p1.getX();
        double dy = p2.getY()-p1.getY();
        return Math.atan2(dy, dx);
    }

    public int getAngleIndex(Point p1, Point p2, int angles){
        double angle;
        if(p1.getY() < p2.getY())
            angle = getAngle(p1, p2);
        else
            angle = getAngle(p2, p1);
        int angleIndex = (int)Math.floor((angles * ((angle + Math.PI / (angles * 2)) / (Math.PI)))) % angles;
        return angleIndex;
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

    public ArrayList<ArrayList<GridPosition>> getClusters(Set<GridPosition> data, int NumberOfClusters){
        ArrayList<ArrayList<GridPosition>> result = new ArrayList<ArrayList<GridPosition>>();
        if(getLargestDistance(data) > 50) {
            List<PositionWrapper> clusterInput = new ArrayList<PositionWrapper>();
            for (GridPosition g : data) {
                clusterInput.add(new PositionWrapper(g));
            }
            KMeansPlusPlusClusterer<PositionWrapper> clusterer = new KMeansPlusPlusClusterer<PositionWrapper>(NumberOfClusters, 10000);
            List<CentroidCluster<PositionWrapper>> clusterResults = clusterer.cluster(clusterInput);
            for (int i = 0; i < clusterResults.size(); i++) {
                ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
                for (PositionWrapper p : clusterResults.get(i).getPoints()) {
                    temp.add(p.getGridPosition());
                }
                result.add(temp);
            }
        }
        else{
            ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
            for (GridPosition g : data) {
                temp.add(g);
            }
            result.add(temp);
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

    public HashMap<Integer, ArrayList<GridPosition>> weightedCurveFitting(HashMap<Integer,HashMap<GridPosition, Double>> data, int stepSize, int degree) {
        int curveId = 1;
        HashMap<Integer, ArrayList<GridPosition>> result = new HashMap<Integer, ArrayList<GridPosition>>();
        for(Integer key : data.keySet()) {
            if(data.get(key).size() > 1) {
                ArrayList<ArrayList<GridPosition>> clusters = getClusters(data.get(key).keySet(), 3);
                GridPosition prevLast = null;
                GridPosition prevFirst = null;
                for(ArrayList<GridPosition> l : clusters) {
                    if(xLargerThanY(l)) {
                        WeightedObservedPoints obs = new WeightedObservedPoints();
                        double minX = Double.MAX_VALUE;
                        double maxX = 0;
                        for (GridPosition g : l) {
                            if (g.getX() < minX)
                                minX = g.getX();
                            if (g.getX() > maxX)
                                maxX = g.getX();
                            obs.add(data.get(key).get(g), g.getX(), g.getY());
                        }
                        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
                        double[] coeff = fitter.fit(obs.toList());
                        PolynomialFunction poly = new PolynomialFunction(coeff);
                        /*double diff1 = -1;
                        double diff2 = -1;
                        if(prevLast != null){
                            diff1 = Math.abs(prevLast.getX()-minX);
                            diff2 = Math.abs(prevFirst.getX()- maxX);
                            if(diff1 < diff2)
                                minX = prevLast.getX();
                            else
                                maxX = prevFirst.getX();
                        }*/
                        double minY = poly.value(minX);
                        /*if(prevLast != null && diff1 < diff2 && diff1 != -1) {
                            minY = (minY + prevLast.getY())/2;
                            result.get(curveId-1).remove(result.get(curveId-1).size()-1);
                            result.get(curveId-1).add(new GridPosition(minX, minY));
                        }*/
                        ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
                        temp.add(new GridPosition(minX, minY));
                        double tempX = minX;
                        double tempY;
                        while (tempX + stepSize < maxX) {
                            tempX += stepSize;
                            tempY = poly.value(tempX);
                            temp.add(new GridPosition(tempX, tempY));
                        }
                        double maxY = poly.value(maxX);
                        /*if(prevFirst != null && diff2 < diff1 && diff2 != -1){
                            maxY = (maxY + prevFirst.getY())/2;
                            result.get(curveId-1).remove(0);
                            result.get(curveId-1).add(0, new GridPosition(maxX, maxY));
                        }*/
                        temp.add(new GridPosition(maxX, maxY));
                        result.put(curveId++, temp);
                        //prevFirst = result.get(curveId-1).get(0);
                        //prevLast = result.get(curveId-1).get(result.get(curveId-1).size()-1);
                    }
                    else{
                        WeightedObservedPoints obs = new WeightedObservedPoints();
                        double minY = Double.MAX_VALUE;
                        double maxY = 0;
                        for (GridPosition g : l) {
                            if (g.getY() < minY)
                                minY = g.getY();
                            if (g.getY() > maxY)
                                maxY = g.getY();
                            obs.add(data.get(key).get(g), g.getY(), g.getX());
                        }
                        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
                        double[] coeff = fitter.fit(obs.toList());
                        PolynomialFunction poly = new PolynomialFunction(coeff);
                        /*double diff1 = -1;
                        double diff2 = -1;
                        if(prevLast != null){
                            minY = prevLast.getY();
                            diff1 = Math.abs(prevLast.getY()-minY);
                            diff2 = Math.abs(prevFirst.getY() - maxY);
                            if(diff1 < diff2)
                                minY = prevLast.getY();
                            else
                                maxY = prevFirst.getY();
                        }*/
                        double minX = poly.value(minY);
                        /*if(prevLast != null && diff2 < diff1 && diff2 != -1) {
                            minX = (minX + prevLast.getX() / 2);
                            result.get(curveId-1).remove(result.get(curveId-1).size()-1);
                            result.get(curveId-1).add(new GridPosition(minX, minY));
                        }*/
                        ArrayList<GridPosition> temp = new ArrayList<GridPosition>();
                        temp.add(new GridPosition(minX, minY));
                        double tempY = minY;
                        double tempX;
                        while (tempY + stepSize < maxY) {
                            tempY += stepSize;
                            tempX = poly.value(tempY);
                            temp.add(new GridPosition(tempX, tempY));
                        }
                        double maxX = poly.value(maxY);
                        /*if(prevFirst != null && diff2 < diff1 && diff2 != -1){
                            maxX = (maxX + prevFirst.getX())/2;
                            result.get(curveId-1).remove(0);
                            result.get(curveId-1).add(0, new GridPosition(maxX, maxY));
                        }*/
                        temp.add(new GridPosition(maxX, maxY));
                        result.put(curveId++, temp);
                        //prevFirst = result.get(curveId-1).get(0);
                        //prevLast = result.get(curveId-1).get(result.get(curveId-1).size()-1);
                    }
                }
            }
        }
        return result;
    }

    public boolean xLargerThanY(ArrayList<GridPosition> data){
        double minX = Double.MAX_VALUE;
        double maxX = 0;
        double minY = Double.MAX_VALUE;
        double maxY = 0;
        for(GridPosition g : data){
            if(g.getX() > maxX)
                maxX = g.getX();
            if(g.getX() < minX)
                minX = g.getX();
            if(g.getY() > maxY)
                maxY = g.getY();
            if(g.getY() < minY)
                minY = g.getY();
        }
        double xDiff = Math.abs(maxX-minX);
        double yDiff = Math.abs(maxY-minY);
        return xDiff >= yDiff;
    }

    public double getLargestDistance(Set<GridPosition> data){
        double minX = Double.MAX_VALUE;
        double maxX = 0;
        double minY = Double.MAX_VALUE;
        double maxY = 0;
        for(GridPosition g : data){
            if(g.getX() > maxX)
                maxX = g.getX();
            if(g.getX() < minX)
                minX = g.getX();
            if(g.getY() > maxY)
                maxY = g.getY();
            if(g.getY() < minY)
                minY = g.getY();
        }
        double xDiff = Math.abs(maxX-minX);
        double yDiff = Math.abs(maxY-minY);
        return Math.max(xDiff, yDiff);
    }

    public HashMap<Integer, ArrayList<Point>> formatGridPositions(HashMap<Integer, ArrayList<GridPosition>> data, double xPixelWidth, double yPixelWidth, double xMin, double yMin){
        int pointId = 0;
        HashMap<Integer, ArrayList<Point>> result = new  HashMap<Integer, ArrayList<Point>>();
        for(Integer key : data.keySet()){
            ArrayList<Point> temp = new ArrayList<Point>();
            for(GridPosition g : data.get(key)) {
                UTMPoint current = new UTMPoint((g.getY() * yPixelWidth) + yMin, (g.getX() * xPixelWidth) + xMin, 32, 'N');
                LatLonPoint l = current.toLatLonPoint();
                Point p = new Point(l.getLatitude(), l.getLongitude(), current.easting, current.northing, "", pointId++, key, 0);
                temp.add(p);
            }
            result.put(key, temp);
        }
        return result;
    }

    public double getDistancePointToPoint(GridPosition p1, GridPosition p2){
        double xd = p2.getX()-p1.getX();
        double yd = p2.getY()-p1.getY();
        return Math.sqrt(xd * xd + yd * yd);
    }

    public double getDistancePointToPoint(Point p1, Point p2){
        double xd = p2.getX()-p1.getX();
        double yd = p2.getY()-p1.getY();
        return Math.sqrt(xd*xd+yd*yd);
    }

    public double getDistanceOfSegment(ArrayList<Point> segment){
        double result = 0;
        for(int i=1; i<segment.size(); i++){
            result += getDistancePointToPoint(segment.get(i-1), segment.get(i));
        }
        return result;
    }

    // Calculates the distance from a gps point to a given road segment
    public double getDistancePointToSegment(GridPosition p, ArrayList<GridPosition> segment){
        double result = Double.MAX_VALUE;
        for(int i=1; i<segment.size(); i++){
            GridPosition one = segment.get(i-1);
            GridPosition two = segment.get(i);
            double temp = Double.MAX_VALUE;
            // Calculate distance, if less than result update
            double vx = two.getX() - one.getX();
            double vy = two.getY() - one.getY();
            double wx = p.getX() - one.getX();
            double wy = p.getY() - one.getY();
            double c1 = vx * wx + vy * wy;
            double c2 = vx * vx + vy * vy;
            // p is before line
            if(c1 <= 0){
                temp = getDistancePointToPoint(p, one);
            }
            // p is after line
            else if(c2 <= c1){
                temp = getDistancePointToPoint(p, two);
            }
            // p is in between endpoints of line
            else{
                double b = c1/c2;
                double x = one.getX()+ vx * b;
                double y = one.getY()+ vy * b;
                GridPosition tempPoint = new GridPosition(x, y);
                temp = getDistancePointToPoint(p, tempPoint);
            }
            if(temp < result){
                result = temp;
            }
        }
        return result;
    }

    public double getDistancePointToSegment(Point p, ArrayList<Point> segment){
        double result = Double.MAX_VALUE;
        for(int i=1; i<segment.size(); i++){
            Point one = segment.get(i-1);
            Point two = segment.get(i);
            double temp = Double.MAX_VALUE;
            // Calculate distance, if less than result update
            double vx = two.getX() - one.getX();
            double vy = two.getY() - one.getY();
            double wx = p.getX() - one.getX();
            double wy = p.getY() - one.getY();
            double c1 = vx * wx + vy * wy;
            double c2 = vx * vx + vy * vy;
            // p is before line
            if(c1 <= 0){
                temp = getDistancePointToPoint(p, one);
            }
            // p is after line
            else if(c2 <= c1){
                temp = getDistancePointToPoint(p, two);
            }
            // p is in between endpoints of line
            else{
                double b = c1/c2;
                double x = one.getX()+ vx * b;
                double y = one.getY()+ vy * b;
                Point tempPoint = new Point(0, 0, x, y, "", 0, 0, 0);
                temp = getDistancePointToPoint(p, tempPoint);
            }
            if(temp < result){
                result = temp;
            }
        }
        return result;
    }

    public double getDistancePointToSegment(Point testP, Point p1, Point p2){
        double result = Double.MAX_VALUE;
        // Calculate distance, if less than result update
        double vx = p2.getX() - p1.getX();
        double vy = p2.getY() - p1.getY();
        double wx = testP.getX() - p1.getX();
        double wy = testP.getY() - p1.getY();
        double c1 = vx * wx + vy * wy;
        double c2 = vx * vx + vy * vy;
        // p is before line
        if(c1 <= 0){
            result = getDistancePointToPoint(testP, p1);
        }
        // p is after line
        else if(c2 <= c1){
           result = getDistancePointToPoint(testP, p2);
        }
        // p is in between endpoints of line
        else{
            double b = c1/c2;
            double x = p1.getX()+ vx * b;
            double y = p1.getY()+ vy * b;
            Point tempPoint = new Point(0, 0, x, y, "", 0, 0, 0);
            result = getDistancePointToPoint(testP, tempPoint);
        }
        return result;
    }

    public double compareSegments(ArrayList<Point> s1, ArrayList<Point> s2){
        double result = 0;
        for(Point p : s1){
            double distance = getDistancePointToSegment(p, s2);
            result += distance;
        }
        return result;
    }

    public Vector3D getVector(GridPosition s){
        return new Vector3D(s.getX(), s.getY(), 0);
    }

}
