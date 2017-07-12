package astar;

import java.util.Comparator;
import java.util.ArrayList;

import astar.OpenSet;
import astar.ClosedSet;

/**
 * Purpose: Implements A* algorithm.
 * Required input condition: Requires created graph "AStarGraph" with tilesNodes setup and initialized 
 */
public class PathFinder {
    public boolean debugToConsole = false;
    //fields for graph 
    AStarGraph graph;
    // List of neighbours of current TileNode
    public ArrayList<TileNode> currentNodeNeighbours;
    // Lst of TileNodes still being processed
    public OpenSet openList;
    //List to track already processed TIleNode
    public ClosedSet closedList;
    //constructor
    PathFinder(AStarGraph graph) 
    {
        this.graph = graph;
        this.currentNodeNeighbours = new ArrayList<TileNode>();
        
        this.openList = new OpenSet( new FCostTileComparator());
        this.closedList = new ClosedSet( new FCostTileComparator());
    }

    // Comparator for TileNode using FCost values; //ascending to get the lowest fcost
    static class FCostTileComparator implements Comparator<ITileNode> 
    {
        public int compare(ITileNode node1, ITileNode node2) {
            return Double.compare(node1.getFCost(), node2.getFCost());
        }
    }

    // Determines the shortest path in the graph
    public ArrayList<TileNode> StartPathFind(TileNode startNode, TileNode destinationNode)
    {
        //Initialise StartNode
        startNode.setGCost(0);
        startNode.setParent(null);
        // Clear openList and add starNode as begin point
        openList.clear();
        openList.add(startNode);
        //
        ITileNode currentNode = startNode;
        
        // Loop all nodes in openList
        while(openList.getSize() > 0 ) 
        {
            if(debugToConsole)
            {
                //debug output 
                System.out.println("Open set: " + openList.toString() + "\n"  
                        + ( "Current node: "+ currentNode.toString()+"\n")
                        + ( "Closed set: " + closedList.toString() ) +"\n");
            }
            //get element with the least sum of costs from the initial node 
            //and heuristic costs to the destinationNode 
            currentNode = openList.poll();  //get node from list

            //Check if reached destination node
            if (currentNode.getMapPoint().equals(destinationNode.point)) 
            {
                return this.calculatePath(destinationNode);
            }

            //get neighbour nodes
            this.currentNodeNeighbours = getTileNeighbours(currentNode);
            
            // Get neighbour node with smallest move  FCost
            for(ITileNode neighbourNode : this.currentNodeNeighbours ) 
            {
                boolean inOpenSet=false;
                if(closedList.contains(neighbourNode))
                {    
                    continue;
                }
                /* Note for nodes that are generated within other nodes:
                 * Ensure that the node and its g value are from the openSet if its already discovered.
                 */
                ITileNode discNeighbourNode = openList.getNode(neighbourNode);
                if(discNeighbourNode != null) 
                {
                    neighbourNode = discNeighbourNode;
                    inOpenSet = true;
                } else 
                {
                    inOpenSet = false;
                }
                
                //compute tentative GCost
                double tentativeG = currentNode.calculateGMoveCost(neighbourNode);
                //node was already discovered and this path is worse than the last one
                if(inOpenSet && tentativeG >= neighbourNode.getGCost())
                {
                    continue;
                }
                             
                //Set Parent of neighbour to be currentNode 
                neighbourNode.setParent( (TileNode) currentNode);

                //Add neighbour, with updated GCost, to OpenList
                if(inOpenSet) 
                {
                    // if neighbourNode is already in data structure it has to be inserted again to 
                    // regain the order
                    openList.remove(neighbourNode);
                    neighbourNode.setGCost(tentativeG);
                    openList.add(neighbourNode);
                } else 
                {
                    neighbourNode.setGCost(tentativeG);
                    openList.add(neighbourNode);
                }
            }
            closedList.add(currentNode);
        }
        // no path determined
        return null;
    }

    //Get list of neighbour nodes 
    public ArrayList<TileNode> getTileNeighbours(ITileNode currentTileNode)   
    {
        this.currentNodeNeighbours.clear();
        //Check 8 neighbour nodes of center currentTileNode
        for (int x=-1;x<2;x++) // -1 left of node, 2-1 right of node
        {
            for (int y=-1;y<2;y++)  // -1 botton of node, 2-1 top of node
            {
                if ((x == 0) && (y == 0)) //equal to node
                {
                    continue;
                }

                int xp = x + currentTileNode.getMapPoint().x;
                int yp = y + currentTileNode.getMapPoint().y;

                //Check that MapPoint of neighbour is a valid TileNode
                if (isPointInsideGraphBounds(xp, yp)) 
                {
                    MapPoint aPoint = new MapPoint(xp, yp);
                    try {                    
                        TileNode aTileNode = this.graph.GetTileFromGridMap(aPoint);
                        if(aTileNode.getTileToken().walkable )
                        {
                            this.currentNodeNeighbours.add(0, aTileNode);
                        }
                    }
                    finally {
                        // delete object from memory 
                        aPoint = null;
                    }
                }
            }
        }
        //
        return this.currentNodeNeighbours;
    } 
       
    //Determine post algorithm parsing of graph TileNodes to get list of path , via reverse lookup on node's parent
    private ArrayList<TileNode> calculatePath(TileNode destinationNode) 
    {
        ArrayList<TileNode> path = new ArrayList<TileNode>();
        TileNode node = destinationNode;
        //First add destinationNode to list
        path.add(node);
        //Loop nodes on condition that the node's parent is still a valid node
        while (node.getParentNode() != null) 
        {   // Now insert mode's parent to top of list, 
            path.add(0, node.getParentNode());
            node = node.getParentNode();
        }
        // Start node is now at top of list and destination node at end of list
        return path;
    }

    // Check that MapPoint(x,y) is a valid TileNode position within graph grid
    private boolean isPointInsideGraphBounds(int x, int y) 
    {
        return x >= 0 &&
               x < this.graph.GetGraphMapWidth() && 
               y >= 0 && 
               y < this.graph.GetGraphMapHeight();
    }

}
