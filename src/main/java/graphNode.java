package astar;
import java.util.ArrayList;

import astar.IBaseNode;

/**
 * @author seelann
 * Purpose: 
 */
public abstract class graphNode implements IBaseNode {
    // Constants
    private final int H_V_MOVEMENT_COST = 10;
    private final int DIAG_MOVEMENT_COST = 14;
    
    // x,y graph position
    public final MapPoint point;
    // graph node moveability into
    public boolean walkableNode = false;
    
    //Variables internal
    private int distanceCost = 0;
    //
    private int movementCost = 0;
    //
    private IBaseNode parentNode = null;
    
    //Constrctor
    public graphNode(MapPoint point)
    {
        this.point = point;
    }
    
    //
    public void calculateDistanceCost(graphNode destNode)
    {
        this.distanceCost = (Math.abs(this.point.x - destPoint.point.x) 
                             + Math.abs(this.point.y - destPoint.point.y)) ;
    }
 
    //
    public void calculateMovementCost(graphNode point)
    {
        if((this.point.x == point.x) || (this.point.y == point.y) )
        {
            this.movementCost += this.H_V_MOVEMENT_COST;
        ) else
        {
            this.movementCost += this.DIAG_MOVEMENT_COST;
        )             
    }
    
    // Set Heuristic value (h) - distance cost to destination node // todo - maybe redundant
    public void setDistanceCost(int h)
    {
        this.distanceCost = h;
    }
    //Get Heuristic value (h)
    public int getDistanceCost()
    {
        return this.distanceCost;
    }
    // Set g value - cost of movement from start node
    public void setMovementCost(int g)
    {
        this.movementCost = g;
    }
    //Get g tentitive value
    public int getMovementCost()
    {
        return this.movementCost;
    }
    //Get f value
    public int getTotalCost()
    {
        return this.movementCost + this.distanceCost;
    }
    // Set Parent node for this node 
    public void setParentNode(IBaseNode parentNode)
    {
        this.parentNode = parentNode;
    }
    //Get Parent node for this node
    public IBaseNode getParentNode()
    {
        return this.parentNode;
    }
    
    //Get list of neighbour nodes // todo- recheck 
    public ArrayList<IBaseNode> getNeighbours()
    {
        ArrayList<IBaseNode> neighbours = new ArrayList<IBaseNode>();
        // Prefetch 
        IBaseNode graphNode = this.getNextNeighbour();
        while(graphNode != null) 
        {
            neighbours.add(0, graphNode);
            // Try fetch next neighbour
            graphNode = this.getNextNeighbour();             
        }
        return neighbours; 
    }
    
    
    //
    private IBaseNode getNextNeighbour()  // todo - recheck
    {   
        IBaseNode graphNode = null;
        
        // todo
        
        return graphNode;
    }
}
