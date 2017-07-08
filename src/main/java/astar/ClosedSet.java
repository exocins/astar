package astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import astar.ITileNode;

/**
 * @author seelann
 * Purpose: 
 */
public class ClosedSet
{
    private ArrayList<ITileNode> list;
    private Comparator<ITileNode> comp;

    public ClosedSet(Comparator<ITileNode> comp) 
    {
        this.list = new ArrayList<ITileNode>();
        this.comp = comp;
    }

    public boolean contains(ITileNode node) 
    {
        return this.list.contains(node);
    }

    public void add(ITileNode node) 
    {
        this.list.add(node);
    }
    
    public void clear() 
    {
        this.list.clear();
    }

    public ITileNode min() 
    {
        return Collections.min(this.list, this.comp);
    }

}
