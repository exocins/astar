package astar;
import java.util.*;

/**
 * author seelann
 * Purpose: Define interface for a base graph node for a* algotithm
 * 
 */
public interface IBaseNode {
    // Set Heuristic value (h) - distance cost to destination node
    public void setDistanceCost(int h);
    //Get Heuristic value (h)
    public int getDistanceCost();
    // Set g value - cost of movement from start node
    public void setMovementCost(int g);
    //Get g tentitive value
    public int getMovementCost();
    //Get f value
    public int getTotalCost();
    // Set Heuristic value - distance value to destination node
    public void setParentNode(IBaseNode parentNode);
    //Get Heuristic value
    public IBaseNode getParentNode();
    
    //Get list of neighbour nodes 
    public ArrayList<IBaseNode> getNeighbours(); 

}
