import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andreas on 28/05/15.
 */
public class Evaluation {

    private HashMap<Integer, ArrayList<Point>> groundTruth;
    private HashMap<Integer, ArrayList<Point>> testData;
    private Util util;

    public Evaluation(HashMap<Integer, ArrayList<Point>> groundTruth, HashMap<Integer, ArrayList<Point>> testData){
        this.groundTruth = groundTruth;
        this.testData = testData;
        this.util = new Util();
        getFScore();
    }

    public double getFScore(){
        System.out.println("Ground truth length: "+getDistanceOfMap(groundTruth));
        System.out.println("Test map length: "+getDistanceOfMap(testData));
        double commonLength = computeCommonRoadLength();
        System.out.println("Common length: "+commonLength);
        double recall = commonLength/getDistanceOfMap(groundTruth);
        System.out.println("Recall: "+recall);
        double precision = commonLength/getDistanceOfMap(testData);
        System.out.println("Precision: "+precision);
        double FScore = (2*recall*precision)/(recall+precision);
        System.out.println("F-score: "+FScore);
        return FScore;
    }

    public double getDistanceOfMap(HashMap<Integer, ArrayList<Point>> map){
        double result = 0;
        for(Integer key : map.keySet()){
            result += util.getDistanceOfSegment(map.get(key));
        }
        return result;
    }

    public double computeCommonRoadLength(){
        double commonLength = 0;
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
                    for(Integer key2 : testData.keySet()){
                        ArrayList<Point> testSegment = testData.get(key2);
                        boolean foundMatch = false;
                        for(int h=1; h<testSegment.size(); h++){
                            Point testPoint1 = testSegment.get(h-1);
                            Point testPoint2 = testSegment.get(h);
                            double testAngle = Math.toDegrees(util.getAngle(testPoint1, testPoint2));
                            if(compareAngles(angle, testAngle, 15)){
                                double distance1 = util.getDistancePointToSegment(temp1, testPoint1, testPoint2);
                                double distance2 = util.getDistancePointToSegment(temp2, testPoint1, testPoint2);
                                if(distance1 < 5 && distance2 < 5){
                                    commonLength += util.getDistancePointToPoint(temp1, temp2);
                                    foundMatch = true;
                                    break;
                                }
                            }
                        }
                        if(foundMatch){
                            break;
                        }
                    }
                }
            }
        }
        return commonLength;
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
        return new Point(0, 0, newX, newY, "", 0, 0, 0);
    }

}
