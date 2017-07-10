package astar;

import java.util.HashMap;
import java.util.Map;

import astar.PathFinder;
import astar.TileNode;
import astar.MapPoint;

/**
 * Purpose: Starting A* algorithm class for creating graph, tile map points and determining path.
 * 
 */

public class AStar {
    
    // HashMap for graph
    private HashMap<MapPoint, TileNode> gridMap;
 
    //Indicates graph map dimensions, after graph is built
    private int mapWidth;
    private int mapHeight;
   
    //Indicates start and end points for path find after graph is built
    private TileNode startTileNode;
    private TileNode destTileNode;
    
    //
    private PathFinder pathFind;
    
    public AStar()
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
    
    public void SetStartingTile(TileNode aTileNode)
    {
        this.startTileNode = aTileNode;
    }
    public void SetDestinationTile(TileNode aTileNode)
    {
        this.destTileNode = aTileNode;
    }
    
    //Get methods for class
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
   
    //Ass a Tile node to graph
    public void AddTileToGridMap(MapPoint aPoint )
    {
        MapPoint bPoint = new MapPoint(aPoint.x, aPoint.y);
        TileNode bTileNode = new TileNode( bPoint );

        this.gridMap.put(bPoint, bTileNode);
    }
    //Get a specific Tile from graph
    public TileNode GetTileFromGridMap(MapPoint aPoint)
    {
        TileNode aTileNode = this.gridMap.get(aPoint);
        
        return aTileNode;
    }

    
}
