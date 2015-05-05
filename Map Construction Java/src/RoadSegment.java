import java.util.ArrayList;

/**
 * Created by AndreasMathisen on 20/04/15.
 *
 * Class representing a single road segment in a road network
 */


public class RoadSegment {

    private int roadSegmentId;
    private double length;
    private ArrayList<Node> nodeList;
    private ArrayList<RoadSegment> neighborList;


    public RoadSegment(int roadSegmentId, double length){
        this.roadSegmentId = roadSegmentId;
        this.length = length;
        this.nodeList = new ArrayList<Node>();
        this.neighborList = new ArrayList<RoadSegment>();
    }

    public int getRoadSegmentId(){
        return roadSegmentId;
    }

    public double getLength(){
        return length;
    }

    public void addNode(Node n){
        nodeList.add(n);
    }

    public ArrayList<Node> getNodeList(){
        return nodeList;
    }

    public void addNeighbor(RoadSegment r){
        neighborList.add(r);
    }

    public ArrayList<RoadSegment> getNeighborList(){
        return neighborList;
    }

    public boolean isNeighbor(RoadSegment r){
        for(RoadSegment n : neighborList){
            if(r.equals(n)){
                return true;
            }
        }
        return false;
    }

    public boolean equals(RoadSegment r){
        return r.getRoadSegmentId() == this.roadSegmentId;
    }
}
