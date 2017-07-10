package astar;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import astar.AStarGraph;
/**
 * Purpose: Used by factory to build map from Text file of lines
 * 
 */

public class BuildGraphFromTextLines extends GraphMapFactory {
    
    //
    public List<String> inputLines;

    //constructor
    BuildGraphFromTextLines ()
    {
    }
    // used to create array objects for graph
    @Override
    public AStarGraph createGraphMap() {
        return new AStarGraph();
    }
    
    //Read input Data
    @Override
    public void ReadInputData(String inputFilename, MapPoint maxMapValues)
    {
        File fIn = new File(inputFilename);
        //Read in text data
        try {
            inputLines = FileUtils.readLines(fIn, "UTF-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        maxMapValues.x = inputLines.get(0).length();
        maxMapValues.y = inputLines.size();
    }


    //Write output Data
    @Override
    public void WritePathFoundToOutput( AStarGraph graph, ArrayList<TileNode> pathFound, String outputFilename)
    {
        File fOut = new File(outputFilename);
        try
        {
            FileUtils.touch(fOut); //create file or use existing
            
            char aChar;
            String outLine = "";
            //Get graph of TileNodes
            Map<MapPoint, TileNode> aMap = graph.GetGraphMap();
            // Put graph into treeMap, with a cusm comparator
            Map<MapPoint, TileNode> treeMap = new TreeMap<MapPoint, TileNode>(
                    new Comparator<MapPoint> () {
                    @Override       
                    public int compare(MapPoint a, MapPoint b) {
                        if ( (a.x + (a.y* graph.GetGraphMapWidth() )) < (b.x + (b.y*graph.GetGraphMapWidth() )) ) {
                            return -1;
                        } else 
                        if ( (a.x + (a.y*graph.GetGraphMapWidth() )) > (b.x + (b.y*graph.GetGraphMapWidth() ))) {
                            return 1;
                        } else 
                        {
                            return 0; // same x and same y
                        }
                    }                                
                    } );
            // Put graph data into treeMap
            treeMap.putAll(aMap);
            // LoopTreemap and output TileNode's visual char
            int x=0;
            for (Map.Entry<MapPoint, TileNode> entry : treeMap.entrySet())
            {                
                TileNode aNode = entry.getValue();
                aChar = aNode.getTileToken().tokenChar;
                
                //If token is in both lists, output special character
                if(  pathFound.contains( aNode)  )
                {
                    aChar= '#' ;
                }
                outLine += Character.toString(aChar);
                x++;
                if(x== graph.GetGraphMapWidth() ) 
                {
                    x=0;
                    outLine += "\n";
                }

                FileUtils.write(fOut, outLine, "UTF-8");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    // Adds TileNodes and initialses properties 
    @Override
    public void BuildGraphMap(AStarGraph graph, MapPoint maxMapValues)
    {
        //Set max parameters of graph
        graph.SetGraphMapWidth(maxMapValues.x);
        graph.SetGraphMapHeight(maxMapValues.y);

        //Add Tile nodes from input data to graph
        AddTilesToGraph(graph);
        //Initialise graph 
        initGraphProperties(graph);
    }
    
    //Build graph tieNodes from input custom data
    @Override 
    public void AddTilesToGraph(AStarGraph graph)
    {
        int x=0;
        int y=0;

        try {
            // loop each line in file
            for (String line : inputLines) {
                x=0;    
                if( y >= graph.GetGraphMapHeight() )
                {
                    /*create custom exeception*/
                    throw new Exception("Cannot add tile to graph: point y >= yMax");
                }
    
                //Add byte to tiles in graph
                for(char myByte : line.toCharArray()) 
                {
                    if( x >= graph.GetGraphMapWidth() )
                    {
                        /*create custom exeception*/
                        throw new Exception("Cannot add tile to graph: point x >= xMax");
                    }
                    
                    //create a MapPoint and add Tile to MapPoint to graph
                    MapPoint aPoint = new MapPoint(x,y);
                    graph.AddTileToGridMap(aPoint);
                    AddTokenToTile(graph, aPoint, myByte);    
                    //next char in line
                    x++;
                }
                //Next line
                y++;
            }
        }
        catch(Exception e)
        {
            System.out.println("Inside catch block: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    //Setup custom terrain data to default TileNodes field
    @Override
    public void AddTokenToTile(AStarGraph graph, MapPoint aPoint, char aToken)
    {
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
    public void initGraphProperties(AStarGraph graph)
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
