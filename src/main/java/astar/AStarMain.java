package astar;

import java.io.*;
import org.apache.commons.cli.*;
import java.util.ArrayList;

import astar.TileNode;
import astar.MapPoint;

/**
 * @author seelann
 * Purpose: Main start module for A* algorithm application
 */
public class AStarMain {
    //variables
    public static MapPoint maxMapValues;

    public static AStarGraph graph;
    //
    public static ArrayList<TileNode> pathFound;
    //
    public static String inputFilename =  "src/small_map.txt";
    public static String outputFilename = "src/out_path.txt";
  
    //Start function of program
    public static void main(String[] args) throws IOException {

    //Initialise fields    
    maxMapValues = new MapPoint(0,0);
       
    //Check if filename part of command line argument
    GetFileName(args);
    System.out.println("Using input filename: " + inputFilename);

    //Check input File exists or clashes with out filename
    File fIn = new File(inputFilename);
    try {
        if( !(fIn.exists() && !fIn.isDirectory()) )
        {
            System.out.println("Exiting - Input file not found: " + inputFilename);
            System.exit(1);
        }
        if(inputFilename == outputFilename)
        { 
            System.out.println("Exiting - Input filename same as Output filaname: " + outputFilename);
            System.exit(1);
        }
    }
    finally
    {
        fIn = null;
    }

    //Get factory object to graph for require input load data type
    GraphMapFactory factory = GraphMapFactory.getFactory(LoadDataType.FILE_LINES);

    //create graph
    graph = factory.createGraphMap();        

    //Read in data
    factory.ReadInputData(inputFilename, maxMapValues); 
 
    //Build graph map and tiles from input data
    factory.BuildGraphMap(graph, maxMapValues);            

    //Determine path of using algorithm
    graph.GetPathFinder().SetMapSize(maxMapValues.x, maxMapValues.y);
    pathFound = graph.GetPathFinder().StartPathFind(graph.GetStartingTile(), graph.GetDestinationTile());
    //
    System.out.printf("\nGraph size and shortest path: xMax= %d, yMax= %d \n", maxMapValues.x, maxMapValues.y );

    //Loop all tiles in graph and setup initialize property fields 
    for (TileNode aTileNode : pathFound) 
    {
       System.out.printf("["+aTileNode.toString() +",%c] ", aTileNode.getTileToken().tokenChar );
    }

    //Write pathFound to output 
    factory.WritePathFoundToOutput( graph, pathFound, outputFilename );
    }
   
    //Try to get inputFilename from command line argument: "-i inFilename"
    public static void GetFileName(String[] args)
    {
        Options options = new Options();
        // setup option for input arguments
        Option input = new Option("i", "input", true, "input file");
        input.setRequired(true); // set true to generate ParseException
        options.addOption(input);
        //create parser for input
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        // Parse arguments
        try 
        {
            cmd = parser.parse(options, args);
        } 
        catch (ParseException e) 
        {
            System.out.println("No filename passed in command parameters. Using Default.");
            return;
        }
        // Get filename from cmd args  
        inputFilename = cmd.getOptionValue("input");

    }    
}
