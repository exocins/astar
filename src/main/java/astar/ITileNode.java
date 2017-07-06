package astar;

import java.util.*;

/**
 * author seelann
 * Purpose: Define interface ITileNode for a tile node on grid for a* algotithm
 * 
 */
public interface ITileNode {
    // Set Heuristic value (h) - distance cost to destination node
    public void setHCost(int h);
    //Get Heuristic value (h)
    public int getHCost();
    // Set g value - cost of movement from start node
    public void setGCost(int g);
    //Get g tentitive value
    public int getGCost();
    //Get f value
    public int getFCost();
    //Get Parent reference
    public TileNode getParentNode();

    
    //Get list of neighbour nodes 
    public ArrayList<TileNode> getNeighbours(); 

}
