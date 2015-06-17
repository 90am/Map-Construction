import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Andreas on 28/05/15.
 */
public class Evaluation {

    private HashMap<Integer, ArrayList<Point>> groundTruth;
    private HashMap<Integer, ArrayList<Point>> testData;
    private Util util;
    ArrayList<Integer> distanceArray;
    ArrayList<Integer> angleArray;
    ArrayList<Double> recallArray;
    ArrayList<Double> precisionArray;
    ArrayList<Double> fscoreArray;

    public Evaluation(HashMap<Integer, ArrayList<Point>> groundTruth, HashMap<Integer, ArrayList<Point>> testData){
        this.groundTruth = groundTruth;
        this.testData = testData;
        this.util = new Util();
        distanceArray = new ArrayList<Integer>();
        angleArray = new ArrayList<Integer>();
        recallArray = new ArrayList<Double>();
        precisionArray = new ArrayList<Double>();
        fscoreArray = new ArrayList<Double>();
        calcFScores(5);
        report();
    }

    public void calcFScores(int iterations){
        for(int i=1; i<=iterations; i++){
            computeFScore(5*i, 40);
        }
    }

    public void report(){
        System.out.println("Ground truth length: "+getDistanceOfMap(groundTruth));
        System.out.println("Test map length: "+getDistanceOfMap(testData));
        System.out.println("Matching distances: ");
        for(int i=0; i<distanceArray.size(); i++){
            System.out.print(distanceArray.get(i)+" ");
        }
        System.out.println("");
        System.out.println("Matching angles: ");
        for(int i=0; i<angleArray.size(); i++){
            System.out.print(angleArray.get(i)+" ");
        }
        System.out.println("");
        System.out.println("Recall: ");
        for(int i=0; i<recallArray.size(); i++){
            System.out.print(recallArray.get(i)+" ");
        }
        System.out.println("");
        System.out.println("Precision: ");
        for(int i=0; i<precisionArray.size(); i++){
            System.out.print(precisionArray.get(i)+" ");
        }
        System.out.println("");
        System.out.println("F-score: ");
        for(int i=0; i<fscoreArray.size(); i++){
            System.out.print(fscoreArray.get(i)+" ");
        }
        System.out.println("");
        double displacement = computeGeographicDisplacement();
        System.out.println("Average displacement: " + displacement);
    }

    public void computeFScore(int distanceThreshold, int angleThreshold){
        double commonLength = computeCommonRoadLength(distanceThreshold, angleThreshold);
        double recall = commonLength/getDistanceOfMap(groundTruth);
        double precision = commonLength/getDistanceOfMap(testData);
        double FScore = (2*recall*precision)/(recall+precision);
        distanceArray.add(distanceThreshold);
        angleArray.add(angleThreshold);
        recallArray.add(recall);
        precisionArray.add(precision);
        fscoreArray.add(FScore);
    }

    public double getDistanceOfMap(HashMap<Integer, ArrayList<Point>> map){
        double result = 0;
        for(Integer key : map.keySet()){
            result += util.getDistanceOfSegment(map.get(key));
        }
        return result;
    }

    public double computeCommonRoadLength(int distanceThreshold, int angleThreshold){
        double commonLength = 0;
        HashMap<Integer, Double> usedSegments = new HashMap<Integer, Double>();
        for(Integer key : groundTruth.keySet()){
            ArrayList<Point> segment = groundTruth.get(key);
            for(int i=1; i<segment.size(); i++){
                Point p1 = segment.get(i-1);
                Point p2 = segment.get(i);
                ArrayList<Point> tempList = getSmallerSegments(p1, p2, 2);
                for(int j=1; j<tempList.size(); j++){
                    Point temp1 = tempList.get(j-1);
                    Point temp2 = tempList.get(j);
                    double angle = Math.toDegrees(util.getAngle(temp1, temp2));
                    boolean segmentNotFound = true;
                    HashSet<Integer> testedSegments = new HashSet<Integer>();
                    int iterations = 0;
                    while(segmentNotFound && iterations < 5) {
                        iterations++;
                        if(iterations>5){
                            System.out.println("ARG!!!");
                        }
                        double minDistance = Double.MAX_VALUE;
                        int segmentKey = 0;
                        for (Integer key2 : testData.keySet()) {
                            ArrayList<Point> testSegment = testData.get(key2);
                            for (int h = 1; h < testSegment.size(); h++) {
                                Point testPoint1 = testSegment.get(h - 1);
                                Point testPoint2 = testSegment.get(h);
                                double testAngle = Math.toDegrees(util.getAngle(testPoint1, testPoint2));
                                if (compareAngles(angle, testAngle, angleThreshold)) {
                                    double distance1 = util.getDistancePointToSegment(temp1, testPoint1, testPoint2);
                                    double distance2 = util.getDistancePointToSegment(temp2, testPoint1, testPoint2);
                                    double maxDistance = Math.max(distance1, distance2);
                                    if (maxDistance < minDistance && !testedSegments.contains(segmentKey)) {
                                        minDistance = maxDistance;
                                        segmentKey = key2;
                                    }
                                }
                            }
                        }
                        if (minDistance < distanceThreshold) {
                            if (usedSegments.containsKey(segmentKey)) {
                                double distanceLeft = usedSegments.get(segmentKey);
                                if (distanceLeft > util.getDistancePointToPoint(temp1, temp2)) {
                                    commonLength += util.getDistancePointToPoint(temp1, temp2);
                                    usedSegments.put(segmentKey, distanceLeft - util.getDistancePointToPoint(temp1, temp2));
                                    segmentNotFound = false;
                                } else {
                                    if(distanceLeft > 0) {
                                        commonLength += distanceLeft;
                                        usedSegments.put(segmentKey, 0.0);
                                        segmentNotFound = false;
                                    }
                                    else{
                                        testedSegments.add(segmentKey);
                                    }
                                }
                            } else {
                                commonLength += util.getDistancePointToPoint(temp1, temp2);
                                double distanceLeft = util.getDistanceOfSegment(testData.get(segmentKey)) - util.getDistancePointToPoint(temp1, temp2);
                                usedSegments.put(segmentKey, distanceLeft);
                                segmentNotFound = false;
                            }
                        }
                        else{
                            segmentNotFound = false;
                        }
                    }
                }
            }
        }
        return commonLength;
    }

    public double computeGeographicDisplacement(){
        double distance = 0;
        double counter = 0;
        for(Integer key : testData.keySet()){
            ArrayList<Point> segment = testData.get(key);
            for(int i=1; i<segment.size(); i++){
                Point p1 = segment.get(i-1);
                Point p2 = segment.get(i);
                ArrayList<Point> tempList = getSmallerSegments(p1, p2, 2);
                for(int j=0; j<tempList.size(); j++) {
                    Point temp = tempList.get(j);
                    double minDistance = Double.MAX_VALUE;
                    for (Integer key2 : groundTruth.keySet()) {
                        ArrayList<Point> testSegment = groundTruth.get(key2);
                        double tempDistance = util.getDistancePointToSegment(temp, testSegment);
                        if(tempDistance < minDistance)
                            minDistance = tempDistance;
                    }
                    distance += minDistance;
                    counter++;
                }
            }
        }
        return distance/counter;
    }

    public boolean compareAngles(double angle1, double angle2, double threshold){
        boolean result = false;
        if(Math.abs(angle2 - angle1) < threshold)
            result = true;
        return result;
    }

    public ArrayList<Point> getSmallerSegments(Point p1, Point p2, double length){
        ArrayList<Point> result = new ArrayList<Point>();
        result.add(p1);
        double distance = util.getDistancePointToPoint(p1, p2);
        Point current = p1;
        while(distance > length){
            current = getThirdPointOnLine(current, p2, length);
            distance -= length;
            result.add(current);
        }
        result.add(p2);
        return result;
    }

    public Point getThirdPointOnLine(Point p1, Point p2, double length){
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double k = Math.sqrt(Math.pow(length,2)/(Math.pow(dx,2)+Math.pow(dy,2)));
        double newX = p1.getX()+dx*k;
        double newY = p1.getY()+dy*k;
        return new Point(0, 0, newX, newY, "", 0, 0, 0, 0);
    }

}
