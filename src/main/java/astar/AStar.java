package astar;

import java.util.HashMap;
import java.util.Map;

import astar.TileNode;
import astar.MapPoint;

/**
 * @author seelann
 *
 */

public class AStar {

    private final int mapWidth;
    private final int mapHeight;
    // Create HashMap
    private final Map<MapPoint, TileNode> gridMap;
    
    private TileNode startTileNode;
    private TileNode destTileNode;
    
    public AStar(int width, int height)
    {
        this.mapWidth = width;
        this.mapHeight = height;
        // Create instnace for gridMap
        this.gridMap = new HashMap<MapPoint, TileNode>();
    }   

    public void AddTileToGridMap(MapPoint aPoint )
    {
        MapPoint bPoint = new MapPoint(aPoint.x, aPoint.y);
        TileNode bTileNode = new TileNode( bPoint );

        this.gridMap.put(bPoint, bTileNode);
    }
    
    public TileNode GetTileFromGridMap(MapPoint aPoint)
    {
        TileNode gTileNode = this.gridMap.get(aPoint);
        
        return gTileNode;
    }
    
    public void AddTokenToTile(MapPoint aPoint, char aToken)
    {
        AddTileToGridMap(aPoint);
        TileNode aTileNode = GetTileFromGridMap(aPoint);
        
        //Add terrain data to tile
        aTileNode.addToken(aToken);
        
        boolean start = false;
        boolean end; 
        boolean walkable; 
        int cost;
        
        switch(aToken){  
            case '~': //water
                start = false;
                end = false;
                walkable = false;
                cost = 0;
            break;  
            case '.': //flatland
                start = false;
                end = false;
                walkable = true;
                cost = 1;
            break;  
            case '*': // Forest
                start = false;
                end = false;
                walkable = true;
                cost = 2;
                start = false;
            break;  
            case '^': // Mountain
                end = false;
                walkable = true;
                cost = 3;
            break;  
            case '@': //start
                start = true;
                end = false;
                walkable = true;
                cost = 0;
                //
                startTileNode = aTileNode;
            break;  
            case 'X': // Destination
                start = false;
                end = true;
                walkable = true;
                cost = 0;
                //
                destTileNode = aTileNode;
            break;  
            default:
                start = false;
                end = false;
                walkable = false;
                cost = 0;
            break;  
        }
        //
        aTileNode.setTokenProperties( start, end, walkable, cost);
    }
    
    //
    public void initGraphTileProperties()
    {
        //for (TileNode aTileNode : gridMap) {
        for (Map.Entry<MapPoint, TileNode> entry : gridMap.entrySet()) {
            //MapPoint aPoint = entry.getKey();
            TileNode aTileNode = entry.getValue();
            //
            initTileProperties(aTileNode);
        }
    }
    //
    public void initTileProperties(TileNode aTileNode)
    {
        aTileNode.calculateHCost(destTileNode);
        
    }
}
