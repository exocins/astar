package astar;

import java.util.HashMap;
import java.util.Map;

import astar.PathFinder;
import astar.TileNode;
import astar.MapPoint;

/**
 * @author seelann
 * Purpose: A* algorithm class for graph, tile map points, tile tokens.
 * 
 */

public class AStarGraph {
    
    // HashMap for graph
    private HashMap<MapPoint, TileNode> gridMap;
 
    //Indicates graph map dimensions, after graph is built
    private int mapWidth;
    private int mapHeight;
   
    //Indicates start and end points for path find after graph is built
    private TileNode startTileNode;
    private TileNode destTileNode;
    
    // Class object to perform path algorithm
    private PathFinder pathFind;
    //Constructor
    public AStarGraph()
    {
        this.mapWidth = 0;
        this.mapHeight = 0;
        this.startTileNode = null;
        this.destTileNode = null;
        
        // Create instance for class objects
        this.gridMap = new HashMap<MapPoint, TileNode>();
        //
        this.pathFind = new PathFinder(this);
    }
    
    //Setter methods for class
    public void SetGraphMapWidth(int width)
    {
        this.mapWidth = width;
    }
    public void SetGraphMapHeight(int gHeight)
    {
        this.mapHeight = gHeight;
    }
    //Set starting tile
    public void SetStartingTile(TileNode aTileNode)
    {
        this.startTileNode = aTileNode;
    }
    //Set destination tile
    public void SetDestinationTile(TileNode aTileNode)
    {
        this.destTileNode = aTileNode;
    }
    
    //Get helper methods for class
    public int GetGraphMapWidth()
    {
        return this.mapWidth;
    }
    public int GetGraphMapHeight()
    {
        return this.mapHeight;
    }
    public TileNode GetStartingTile()
    {
        return this.startTileNode;
    }
    public TileNode GetDestinationTile()
    {
        return this.destTileNode;
    }

    //Get graph object
    public Map<MapPoint, TileNode> GetGraphMap()
    {
        return this.gridMap;
    }

    //Get object to calculate shortest path using A*
    public PathFinder GetPathFinder()
    {
        return this.pathFind;
    }
   
    //Assign a Tile node to graph
    public TileNode AddTileToGridMap(MapPoint aPoint )
    {
        MapPoint bPoint = new MapPoint(aPoint.x, aPoint.y);
        TileNode bTileNode = new TileNode( bPoint );

        this.gridMap.put(bPoint, bTileNode);
        //return added TileNode
        return bTileNode;
    }

    //Get a specific Tile from graph
    public TileNode GetTileFromGridMap(MapPoint aPoint)
    {
        TileNode aTileNode = this.gridMap.get(aPoint);
        
        return aTileNode;
    }

}
