package astar;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
import java.util.*;

import astar.ITileNode;
import astar.MapPoint;
import astar.TileToken;

/**
 * @author seelann
 * Purpose: 
 */
public class TileNode implements ITileNode {
    // Constants
    private final int H_V_MOVEMENT_COST = 10;
    private final int DIAG_MOVEMENT_COST = 14;
    
    // x,y graph position
    public MapPoint point;
    
    // Tile token representing terring oject
    public TileToken token;
    
    //Variables internal
    private int hCost;
    //
    private int gCost;
    //
    private TileNode parentNode;
    
    //Constrctor
    public TileNode(MapPoint aPoint)
    {
        this.point = aPoint;
        //
        this.gCost = 0;
        this.hCost = 0;
        this.parentNode = null;
        this.token = null;
    }
    
    //
    public void addToken(char aChar)
    {
        this.token = new TileToken( aChar);
    }
    
    //
    public void setTokenProperties(boolean start, boolean end, boolean warkable, int cost)
    {
        token.setTokenProperties(start, end, warkable, cost);
    }
    
    //
    public void calculateHCost(TileNode destNode)
    {   // Multipy by 10 to be consistant to the g value also being multiplied by 10 (1.4 -> 14)
    //    this.hCost = (Math.abs(this.point.x - destNode.point.x) 
    //                         + Math.abs(this.point.y - destNode.point.y)) * 10;
    }
 
    //
    public void calculateGCost(TileNode toNode)
    {
        if((this.point.x == toNode.point.x) || (this.point.y == toNode.point.y) )
        {
            this.gCost += this.H_V_MOVEMENT_COST;
        } else
        {
            this.gCost += this.DIAG_MOVEMENT_COST;
        }             
    }
    
    // Set Heuristic value (h) - distance cost to destination node // todo - maybe redundant
    public void setHCost(int h)
    {
        this.hCost = h;
    }
    //Get Heuristic value (h)
    public int getHCost()
    {
        return this.hCost;
    }
    // Set g value - cost of movement from start node
    public void setGCost(int g)
    {
        this.gCost = g;
    }
    //Get g tentitive value
    public int getGCost()
    {
        return this.gCost;
    }
    //Get f value
    public int getFCost()
    {
        return this.gCost + this.hCost;
    }

    //Get Parent node for this node
    public TileNode getParentNode()
    {
        return this.parentNode;
    }
        
    //Get list of neighbour nodes // todo- recheck 
    public ArrayList<TileNode> getNeighbours()
    {
        ArrayList<TileNode> neighbours = new ArrayList<TileNode>();
        // Prefetch 
        TileNode tileNode = this.getNextNeighbour();
        while(tileNode != null) 
        {
            neighbours.add(0, tileNode);
            // Try fetch next neighbour
            tileNode = this.getNextNeighbour();             
        }
        return neighbours; 
    }
    
    public TileNode getNextNeighbour()
    {
    
    //private final TileNode tileNode = null;
        
        // todo
        
        return null; //tileNode;
    }
}
