package astar;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import astar.TileNode;

/**
 * Purpose: UnitTest for AStarGraph
 * 
 */
public class AStarGraphTest {
    List<String> inputLines;
    MapPoint maxMapValues;
    AStarGraph graph;
    BuildGraphFromTextLines factory;
    PathFinder pathFind;
    ArrayList<TileNode> pathFound;
   
    @Before
    public void setUp() throws Exception {
        inputLines = new ArrayList<String>();
        inputLines.add("@*^^^");
        inputLines.add("~~*~.");
        inputLines.add("**...");
        inputLines.add("^..*~");
        inputLines.add("~~*~X");
        
        maxMapValues = new MapPoint(5,5);
        graph = new AStarGraph();
        factory = new BuildGraphFromTextLines();
        factory.inputLines = inputLines;  // setup reference in factory to above Lines list.
        factory.createGraphMap();
        factory.BuildGraphMap(graph, maxMapValues);
        pathFind = graph.GetPathFinder();
        pathFound = graph.GetPathFinder().StartPathFind(graph.GetStartingTile(), graph.GetDestinationTile());
    }

    @After
    public void tearDown() throws Exception {
        inputLines.clear();
        inputLines = null;
        maxMapValues = null;
        graph = null;
        factory = null;
        pathFind = null;
        pathFound.clear();
        pathFound = null;
    }

    @Test
    public void test() {

        assertEquals(5, graph.GetGraphMapWidth(), 0);

        TileNode aTileNode = graph.GetTileFromGridMap(new MapPoint(0,0));
        assertEquals( '@', aTileNode.getTileToken().tokenChar);
        aTileNode = graph.GetTileFromGridMap(new MapPoint(3,3));
        assertEquals( '*', aTileNode.getTileToken().tokenChar);

        aTileNode = graph.GetStartingTile();
        assertEquals( '@', aTileNode.getTileToken().tokenChar);
        aTileNode = graph.GetDestinationTile();
        assertEquals( 'X', aTileNode.getTileToken().tokenChar);

        assertEquals(6, pathFound.size(), 0);
        assertEquals("MapPoint index 3","(3,2)", pathFound.get(3).toString());

    }


}


