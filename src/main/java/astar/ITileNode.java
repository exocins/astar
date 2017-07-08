package astar;

/**
 * author seelann
 * Purpose: Define interface ITileNode for a tile node on grid for a* algotithm
 * 
 */
public interface ITileNode {
    //Get mapoint for node
    public MapPoint getMapPoint();
    // Set Heuristic value (h) - distance cost to destination node
    public void setHCost(double h);
    //Get Heuristic value (h)
    public double getHCost();
    // Set g value - cost of movement from start node
    public void setGCost(double g);
    //Get g tentitive value
    public double getGCost();
    //move cost to toNode
    public double calculateGMoveCost(ITileNode toNode);
    //Get f value
    public double getFCost();
    //set parent
    public void setParent(TileNode parent);
    //Get Parent
    public TileNode getParentNode();
    //get properties of node
    public TileToken getTileToken();
}
