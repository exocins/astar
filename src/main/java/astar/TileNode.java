package astar;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.*;

import astar.ITileNode;
import astar.MapPoint;
import astar.TileToken;

/**
 * @author seelann
 * Purpose: 
 */
public class TileNode implements ITileNode {
    // Constants
    //private final int H_V_MOVEMENT_COST = 10;
    //private final int DIAG_MOVEMENT_COST = 14;
    
    // (x,y) graph position of TileNode
    public MapPoint point;
    
    // Tile token representing terrain object default walkable properties
    public TileToken token;
    
    //Variables internal- used for movement algotithms
    private double hCost;           // Move cost from node to destination node
    private double gCost;           // Move cost from start node to this node
    //
    private TileNode parentNode;    // Node from which a move was made
    
    //Constrctor
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
    public void setTokenDefaultProperties(char aChar, boolean walkable, int moveCost)
    {
        token.setDefaultProperties( aChar, walkable, moveCost);
    }
    
    //
    public void calculateHCost(TileNode destNode)
    {   // Use Manhattan formula
        this.hCost = (Math.abs(this.point.x - destNode.point.x) 
                             + Math.abs(this.point.y - destNode.point.y)) ;
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
    //Get g tentitive value
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
        
}
