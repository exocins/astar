package astar;

import java.util.Comparator;
import java.util.PriorityQueue;

import astar.ITileNode;

/**
 * @author 
 * Purpose: 
 */
public class OpenSet implements IOpenSet {

    private PriorityQueue<ITileNode> Q;

    public OpenSet(Comparator<ITileNode> comp) 
    {
        Q = new PriorityQueue<ITileNode>(1000, comp);
    }

    public void add(ITileNode node) 
    {
        this.Q.add(node);
    }

    
    public void remove(ITileNode node) {
        this.Q.remove(node);

    }
    //
    public void clear() 
    {
        this.Q.clear();
    }

    //retrieves and removes the head of this queue, or returns null if this queue is empty.
    public ITileNode poll() 
    {
        return this.Q.poll();
    }

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

    public int getSize() 
    {
        return this.Q.size();
    }
}
