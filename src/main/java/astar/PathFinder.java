package astar;

import java.util.Comparator;
import java.util.ArrayList;
//import java.util.*;

import astar.OpenSet;
import astar.ClosedSet;

/**
 * @author seelann
 * Purpose: 
 */
public class PathFinder {
    //fields fir graph 
    private int width;
    private int height;
    AStar graph;
    //
    public ArrayList<TileNode> currentNodeNeighbours;
    //
    public OpenSet openList;
    //
    public ClosedSet closedList;
    //constructor
    PathFinder(AStar graph) 
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

    //
    public void SetMapSize(int width, int height) 
    {
        this.width = width;
        this.height = height;
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
            //debug output 
            System.out.println("Open set: " + openList.toString() + "\n"  
                        + ( "Current node: "+ currentNode.toString()+"\n")
                        + ( "Closed set: " + closedList.toString() ) +"\n");

            //get element with the least sum of costs from the initial node 
            //and heuristic costs to the goal 
            currentNode = openList.poll();

            //Check if reached destination node
            if (currentNode.getMapPoint().equals(destinationNode.point)) 
            {
                return this.calculatePath(destinationNode);
            }

            //get neighbour nodes
            this.currentNodeNeighbours = getTileNeighbours(currentNode);
            
            // et neighbour node with smallest move  FCost
            for(ITileNode neighbourNode : this.currentNodeNeighbours ) 
            {
                boolean inOpenSet=false;
                if(closedList.contains(neighbourNode))
                {    
                    continue;
                }
                /* Special rule for nodes that are generated within other nodes:
                 * We need to ensure that we use the node and
                 * its g value from the openSet if its already discovered
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
                
                //Set Parent
                neighbourNode.setParent( (TileNode) currentNode);
                if(inOpenSet) 
                {
                    // if successorNode is already in data structure it has to be inserted again to 
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
        
        //bestNodeAfterSearch = closedList.min();
        return null;
    }

    
    //Get list of neighbour nodes 
    public ArrayList<TileNode> getTileNeighbours(ITileNode currentTileNode)   
    {
        this.currentNodeNeighbours.clear();

        for (int x=-1;x<2;x++) 
        {
            for (int y=-1;y<2;y++) 
            {
                if ((x == 0) && (y == 0)) 
                {
                    continue;
                }

                int xp = x + currentTileNode.getMapPoint().x;
                int yp = y + currentTileNode.getMapPoint().y;

                
                if (isInsideGraphBounds(xp, yp)) 
                {
                    MapPoint aPoint = new MapPoint(xp, yp);
                    
                    TileNode aTileNode = this.graph.GetTileFromGridMap(aPoint);
                    if(aTileNode.getTileToken().walkable )
                    {
                        this.currentNodeNeighbours.add(0, aTileNode);
                    }    
                    // delete object from memory 
                    aPoint = null;
                }
            }
        }
        //
        return this.currentNodeNeighbours; 

    }
    
    
    //
    private ArrayList<TileNode> calculatePath(TileNode destinationNode) 
    {
        ArrayList<TileNode> path = new ArrayList<TileNode>();
        TileNode node = destinationNode;

        while (node.getParentNode() != null) {
            path.add(node);
            node = node.getParentNode();
        }

        return path;
    }


    //
    private boolean isInsideGraphBounds(int x, int y) {
        return x >= 0 &&
               x < this.width && 
               y >= 0 && 
               y < this.height;
    }

}
