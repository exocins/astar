package astar;

//import java.util.ArrayList;
import java.io.*;
import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.List;

import astar.TileNode;

/**
 * @author seelann
 * Purpose: 
 */
public class AStarMain {
    //variables
    public static int xMax = 0;
    public static int yMax = 0;
    
    public static AStar graph;
    //
    public static ArrayList<TileNode> pathFound;
    
    //Start function of program
    public static void main(String[] args) throws IOException {

        try {
            //Get factory object to graph for require input load data type
            GraphMapFactory factory = GraphMapFactory.getFactory(LoadDataType.FILE_LINES);
                        
            //create graph
            graph = factory.createGraphMap();            
            //
            File f = new File("src/small_map.txt");

            List<String> lines = FileUtils.readLines(f, "UTF-8");
            xMax = lines.get(0).length();
            yMax = lines.size();
            graph.SetGraphMapWidth(xMax);
            graph.SetGraphMapHeight(yMax);
 
            //Build graph map and tiles from input data
            factory.BuildGraphMap(graph, lines);            
            //
            graph.GetPathFinder().SetMapSize(xMax, yMax);
            pathFound = graph.GetPathFinder().StartPathFind(graph.GetStartingTile(), graph.GetDestinationTile());
            //
            System.out.printf("\nGraph: xMax= %d, yMax= %d \n", xMax, yMax );

            //Print calculate path to console:
            //Collections.sort(pathFound, this.fComparator);
            //Loop all tiles in graph and setup initialize property fields 
            for (TileNode aTileNode : pathFound) 
            {
               System.out.printf("["+aTileNode.toString() +",%c] ", aTileNode.getTileToken().tokenChar );

            }
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
