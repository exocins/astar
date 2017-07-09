package astar;

import java.util.List;
import java.util.Map;

import astar.AStar;
/**
 * Purpose: Used by factory to build map from list of lines
 * 
 */

public class BuildGraphFromTextLines extends GraphMapFactory {
    // used to create array objects for graph
    @Override
    public AStar createGraphMap() {
        return new AStar();
    }
    // Adds TileNodes and initialses properties 
    @Override
    public void BuildGraphMap(AStar graph, List<String> lines)
    {
        AddTilesToGraph(graph, lines);
        //
        initGraphProperties(graph);
    }
    
    //Build graph tieNodes from input custom data
    @Override 
    public void AddTilesToGraph(AStar graph, List<String> lines)
    {
        int x=0;
        int y=0;

        // loop each line in file
        for (String line : lines) {
            x=0;    
            
            //Add byte to tiles in graph
            for(char myByte : line.toCharArray()) 
            {              
                //create a MapPoint and add Tile to MapPoint to graph
                MapPoint aPoint = new MapPoint(x,y);
                AddTokenToTile(graph, aPoint, myByte);    
                //next char in line
                x++;
            }
            //Next line
            y++;
        }
    }

    //Setup custom terrain data to default TileNodes field
    @Override
    public void AddTokenToTile(AStar graph, MapPoint aPoint, char aToken)
    {
        graph.AddTileToGridMap(aPoint);
        TileNode aTileNode = graph.GetTileFromGridMap(aPoint);
       
        boolean walkable=false; 
        int cost=0;
        //Parse terrain token and set Tile's token properties
        switch(aToken){  
            case '~': //water
                walkable = false;
                cost = 0;
            break;  
            case '.': //flatland
                walkable = true;
                cost = 1;
            break;  
            case '*': // Forest
                walkable = true;
                cost = 2;
            break;  
            case '^': // Mountain
                walkable = true;
                cost = 3;
            break;  
            case '@': //start
                walkable = true;
                cost = 0;
                //
                graph.SetStartingTile(aTileNode);
            break;  
            case 'X': // Destination
                walkable = true;
                cost = 0;
                //
                graph.SetDestinationTile(aTileNode);
            break;  
            default:
                walkable = false;
                cost = 0;
            break;  
        }

        //Set terrain data to tile
        aTileNode.setTokenDefaultProperties( aToken, walkable, cost);
    }

    //Setup graph map properties that will not change during algorithm
    @Override
    public void initGraphProperties(AStar graph)
    {
        //Loop all tiles in graph and setup initialize property fields 
        for (Map.Entry<MapPoint, TileNode> entry : graph.GetGraphMap().entrySet()) 
        {
            //MapPoint aPoint = entry.getKey();
            TileNode aTileNode = entry.getValue();
           //System.out.printf("%d,%d,%c ", aTileNode.getMapPoint().x, aTileNode.getMapPoint().y, aTileNode.getTileToken().tokenChar );
            
            //Setup default properties for aTileNode
            initTileProperties(aTileNode);
        }
    }
    
    // Setup TileNode properties that will not change during algorithm
    @Override 
    public void initTileProperties(TileNode aTileNode)
    {
        // Setup H values for a TileNode
        aTileNode.calculateHCost(aTileNode);
    }

}
