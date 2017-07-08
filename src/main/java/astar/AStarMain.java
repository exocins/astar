package astar;

//import java.util.ArrayList;
import java.io.*;
import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
//import java.io.File;
//import java.io.IOException;
import java.util.List;

//import astar;
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
            //
            //create graph
            graph = new AStar();            
            //
            File f = new File("src/small_map.txt");

            System.out.println("Reading files using Apache IO:");

            List<String> lines = FileUtils.readLines(f, "UTF-8");
            xMax = lines.get(0).length();
            yMax = lines.size();
            graph.SetGraphMapWidth(xMax);
            graph.SetGraphMapHeight(yMax);
 
            //Build graphmapand tiles from input data
            graph.GetGraphMapBuilder().StartGraphMapBuild(graph, lines);            
            //
            graph.GetPathFinder().SetMapSize(xMax, yMax);
            pathFound = graph.GetPathFinder().StartPathFind(graph.GetStartingTile(), graph.GetDestinationTile());
            //
            System.out.printf("\n xMax= %d, yMax= %d \n", xMax, yMax );
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
