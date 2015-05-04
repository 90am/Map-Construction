import java.util.ArrayList;

/**
 * Created by AndreasMathisen on 20/04/15.
 *
 * Class representing a single road segment in a road network
 */


public class RoadSegment {

    private int roadSegmentId;
    private double length;
    private String roadType;
    private ArrayList<Node> nodeList;
    private ArrayList<RoadSegment> neighborList;
    // Used in calculating shortest paths
    private long dijkstraId;
    private boolean visited;
    private RoadSegment fromRoadSegment;
    private double minDistanceToRoadSegment;

    public RoadSegment(int roadSegmentId, double length, String roadType){
        this.roadSegmentId = roadSegmentId;
        this.length = length;
        this.roadType = roadType;
        this.nodeList = new ArrayList<Node>();
        this.neighborList = new ArrayList<RoadSegment>();
        dijkstraId = 0;
        visited = false;
        fromRoadSegment = null;
        minDistanceToRoadSegment = Double.MIN_VALUE;
    }

    public int getRoadSegmentId(){
        return roadSegmentId;
    }

    public double getLength(){
        return length;
    }

    public long getDijkstraId(){
        return dijkstraId;
    }

    public RoadSegment getFromRoadSegment(){
        return fromRoadSegment;
    }

    public double getMinDistanceToRoadSegment(){
        return minDistanceToRoadSegment;
    }

    public boolean isVisited(){
        return visited;
    }

    public void resetForNewSearch(long dijkstraId){
        minDistanceToRoadSegment = Double.MAX_VALUE;
        fromRoadSegment = null;
        visited = false;
        this.dijkstraId = dijkstraId;
    }

    public void updateMinLengthToFromRoadSegment(double length, RoadSegment r){
        if(length < minDistanceToRoadSegment){
            minDistanceToRoadSegment = length;
            fromRoadSegment = r;
        }
    }

    public void setVisited(){
        visited = true;
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

    public String getRoadType(){
        return roadType;
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
