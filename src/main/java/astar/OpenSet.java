package astar;

import java.util.Comparator;
import java.util.PriorityQueue;

import astar.ITileNode;

/**
 * Purpose:  Keeps set of to be processed TileNodes
 * 
 */
public class OpenSet implements IOpenSet {
    // Constants
    private final int INITIAL_QUEUE_SIZE = 1000;

    // internal Set
    private PriorityQueue<ITileNode> Q;

    // Constructor
    public OpenSet(Comparator<ITileNode> comp) 
    {   // create Set instance , using  Function comp to set sort order in Set
        Q = new PriorityQueue<ITileNode>(INITIAL_QUEUE_SIZE, comp);
    }

    public void add(ITileNode node) 
    {
        this.Q.add(node);
    }

    //Remove a single TileNode element from Set
    public void remove(ITileNode node) 
    {
        this.Q.remove(node);
    }

    //Remove all TileNode elements from Set
    public void clear() 
    {
        this.Q.clear();
    }

    //retrieves and removes the head of this queue, or returns null if this queue is empty.
    public ITileNode poll() 
    {
        return this.Q.poll();
    }
    // Retrieve a specified node from Set
    public ITileNode getNode(ITileNode node) 
    {
        for(ITileNode openTileNode : this.Q) 
        {
            if(openTileNode.equals(node)) 
            {
                return openTileNode;
            }
        }
        return null;
    }
    // Gets total cont of elements in Set
    public int getSize() 
    {
        return this.Q.size();
    }
    

    @Override
    public String toString() 
    {
        return "("+ this.Q +")";
    }

}
