package astar;

import java.io.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import astar.TileNode;
import astar.MapPoint;

/**
 * @author seelann
 * Purpose: Main start module for A* algorithm application
 */
public class AStarMain {
    //variables
    public static int xMax = 0;
    public static int yMax = 0;
    
    public static AStarGraph graph;
    //
    public static ArrayList<TileNode> pathFound;
    //
    public static String inputFilename =  "src/small_map.txt";
    public static String outputFilename = "src/out_path.txt";
  
    //Start function of program
    public static void main(String[] args) throws IOException {

        try {
            //Check if filename part of command line argument
            GetFileName(args);
            System.out.println("Using input filename: " + inputFilename);

            //Check input File
            File fIn = new File(inputFilename);
            if( !(fIn.exists() && !fIn.isDirectory()) )
            { 
                //
                System.out.println("Exiting - Input file not found: " + inputFilename);
                System.exit(1);
            }
            if(inputFilename == outputFilename)
            { 
                //
                System.out.println("Exiting - Input filename same as Output filaname: " + outputFilename);
                System.exit(1);
            }

            //Get factory object to graph for require input load data type
            GraphMapFactory factory = GraphMapFactory.getFactory(LoadDataType.FILE_LINES);

            //create graph
            graph = factory.createGraphMap();            

            //Read in text data
            List<String> inlines = FileUtils.readLines(fIn, "UTF-8");
            xMax = inlines.get(0).length();
            yMax = inlines.size();
            graph.SetGraphMapWidth(xMax);
            graph.SetGraphMapHeight(yMax);
 
            //Build graph map and tiles from input data
            factory.BuildGraphMap(graph, inlines);            
            //
            graph.GetPathFinder().SetMapSize(xMax, yMax);
            pathFound = graph.GetPathFinder().StartPathFind(graph.GetStartingTile(), graph.GetDestinationTile());
            //
            System.out.printf("\nGraph size and shortest path: xMax= %d, yMax= %d \n", xMax, yMax );

            //Print calculate path to console:
            //Collections.sort(pathFound, this.fComparator);
            //Loop all tiles in graph and setup initialize property fields 
            for (TileNode aTileNode : pathFound) 
            {
               System.out.printf("["+aTileNode.toString() +",%c] ", aTileNode.getTileToken().tokenChar );
            }
            //
            File fOut = new File(outputFilename);
            try
            {
                FileUtils.touch(fOut); //create or use existing
                
                char aChar;
                String outLine = "";
                //Get graph of TileNodes
                Map<MapPoint, TileNode> aMap = graph.GetGraphMap();
                // Put graph into treeMap, with a cusm comparator
                Map<MapPoint, TileNode> treeMap = new TreeMap<MapPoint, TileNode>(
                        new Comparator<MapPoint> () {
                        @Override       
                        public int compare(MapPoint a, MapPoint b) {
                            if ( (a.x + (a.y*xMax)) < (b.x + (b.y*xMax)) ) {
                                return -1;
                            } else 
                            if ( (a.x + (a.y*xMax)) > (b.x + (b.y*xMax))) {
                                return 1;
                            } else 
                            {
                                return 0; // same x and same y
                            }
                        }                                
                        } );
                // Put graph data into treeMap
                treeMap.putAll(aMap);
                // LoopTree<ap and output TileNode's visual char
                int x=0;
                for (Map.Entry<MapPoint, TileNode> entry : treeMap.entrySet())
                {                
                    TileNode aNode = entry.getValue();
                    aChar = aNode.getTileToken().tokenChar;
    
                    if(  pathFound.contains( aNode)  )
                    {
                        aChar= '#' ;
                    }
                    outLine += Character.toString(aChar);
                    x++;
                    if(x==xMax) 
                    {
                        x=0;
                        outLine += "\n";
                    }
    
                    FileUtils.write(fOut, outLine, "UTF-8");
                }
            }
            finally {
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void GetFileName(String[] args)
    {
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try 
        {
            cmd = parser.parse(options, args);
        } 
        catch (ParseException e) 
        {
            System.out.println("No filename passed in command parameters. Using Default.");
            //System.out.println(e.getMessage());
            //formatter.printHelp("utility-name", options);
            //
            return;
        }
        // Get 
        inputFilename = cmd.getOptionValue("input");

    }
}
