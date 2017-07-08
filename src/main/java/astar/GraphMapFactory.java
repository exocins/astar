package astar;

import java.util.List;
import java.util.Map;

import astar.AStar;

/**
 * @author seelann
 * Purpose: 
 */
public class GraphMapFactory {
    //constructor
    GraphMapFactory () {}

    public void StartGraphMapBuild(AStar graph, List<String> lines)
    {
        AddTilesToGraph(graph, lines);
        //
        initGraphProperties(graph);
    }
    
    //
    public void AddTilesToGraph(AStar graph, List<String> lines)
    {
        int x=0;
        int y=0;

        // loop each line in file
        for (String line : lines) {
            x=0;    
            //System.out.printf("\n");
            //System.out.println(line);
            
            //Add byte to tiles in graph
            for(char myByte : line.toCharArray()) {
                //System.out.printf("%d,%d,%c ", x,y,myByte );
                
                //create a MapPoint and add Tile to MapPoint to graph
                MapPoint aPoint = new MapPoint(x,y);
                AddTokenToTile(graph, aPoint, myByte);
                
                //next char in line
                x++;
            }
            //Next line
            y++;
        }
        //System.out.printf("\n\n");

    }

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

    //
    public void initGraphProperties(AStar graph)
    {
        //for (TileNode aTileNode : gridMap) {
        for (Map.Entry<MapPoint, TileNode> entry : graph.GetGraphMap().entrySet()) {
            //MapPoint aPoint = entry.getKey();
            TileNode aTileNode = entry.getValue();
           //System.out.printf("%d,%d,%c ", aTileNode.getMapPoint().x, aTileNode.getMapPoint().y, aTileNode.getTileToken().tokenChar );
            
            //
            initTileProperties(aTileNode);
        }
    }
    //
    public void initTileProperties(TileNode aTileNode)
    {
        aTileNode.calculateHCost(aTileNode);
        
    }

}
