package astar;

import java.util.ArrayList;

import astar.AStarGraph;

enum LoadDataType {
    FILE_LINES, FILE_JSON
}
/**
 * Purpose: Abstract factory class
 * 
 */
abstract class GraphMapFactory {

    private static final BuildGraphFromTextLines GraphFromTextLines = new BuildGraphFromTextLines();
    
    // Returns a concrete factory object that is an instance of the
    // concrete factory class appropriate for the given architecture.
    static GraphMapFactory getFactory(LoadDataType loadType) {
        GraphMapFactory factory = null;
        switch (loadType) {
            case FILE_LINES:
                factory = GraphFromTextLines;
                break;
            case FILE_JSON:
                factory = null;  // todo
                break;
        }
        return factory;
    }
    
    // Creates graph object
    public abstract AStarGraph createGraphMap();

    //Read input Data
    public abstract void ReadInputData(String inputFilename, MapPoint maxMapValues);

    //Write output Data
    public abstract void WritePathFoundToOutput( AStarGraph graph, ArrayList<TileNode> pathFound, String outputFilename);

    // Builds graph, adds Tiles and Initialises Tiles
    public abstract void BuildGraphMap(AStarGraph graph, MapPoint maxMapValues);
    
    //Build graph tieNodes from input custom data
    public abstract void AddTilesToGraph(AStarGraph graph);
    
    //Setup custom terrain data to default TileNodes field
    public abstract void AddTokenDataToTile(AStarGraph graph, MapPoint aPoint, char aToken);

    //Setup graph map properties that will not change during algorithm
    public abstract void initGraphProperties(AStarGraph graph);
    
    // Setup TileNode properties that will not change during algorithm
    public abstract void initTileProperties(TileNode aTileNode);

}
