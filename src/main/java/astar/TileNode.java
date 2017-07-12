package astar;

import astar.ITileNode;

import astar.MapPoint;

/**
 * Purpose: Class to represent path data on a grid
 *  
 */
public class TileNode implements ITileNode {
    
    // (x,y) graph position of TileNode
    public MapPoint point;
    
    // Tile token representing terrain object default walkable properties
    public TileToken token;
    
    //Variables internal- used for movement algorithms
    private double hCost;           // Move cost from node to destination node
    private double gCost;           // Move cost from start node to this node
    //
    private TileNode parentNode;    // Node from which a move was made
    
    //Constructor
    public TileNode(MapPoint aPoint)
    {
        this.point = aPoint;
        // Initialize internal fields
        this.gCost = 0;
        this.hCost = 0;
        this.parentNode = null;
        this.token = new TileToken();   //Create instance object
    }

    //
    public void Reset()
    {
        this.gCost = 0;
        this.hCost = 0;
        this.parentNode = null; //will not delete parent object , but reference, since still in graph
    }
    //
    public void setTokenDefaultProperties(char aChar, boolean walkable, int moveCost)
    {
        token.setDefaultProperties( aChar, walkable, moveCost);
    }
    
    //
    public double calculateHCost(TileNode destNode)
    {   // Use Manhattan formula
        this.hCost = (Math.abs(this.point.x - destNode.point.x) 
                             + Math.abs(this.point.y - destNode.point.y));
        return this.hCost;
    }
 
    //
    public double calculateGMoveCost(ITileNode toNode)
    {   // add tentative cost of node and default toNode cost 
         return this.gCost +  toNode.getTileToken().moveCost;             
    }
    
    //Get MapPoint of TileNode
    public MapPoint getMapPoint()
    {
        return this.point;
    }

    //Get TileToken of TileNode
    public TileToken getTileToken()
    {
        return this.token;
    }

    // Set Heuristic value (h) - distance cost to destination node // todo - maybe redundant
    public void setHCost(double h)
    {
        this.hCost = h;
    }
    //Get Heuristic value (h)
    public double getHCost()
    {
        return this.hCost;
    }
    // Set g value - cost of movement from start node
    public void setGCost(double g)
    {
        this.gCost = g;
    }
    //Get g tentative value
    public double getGCost()
    {
        return this.gCost;
    }
    //Get f value
    public double getFCost()
    {
        return this.gCost + this.hCost;
    }

    //set parent
    public void setParent(TileNode parent)
    {
        this.parentNode = parent;
    }

    //Get Parent node for this node
    public TileNode getParentNode()
    {
        return this.parentNode;
    }
        
    @Override
    public String toString() {
        return this.getMapPoint().toString();
    }

}
