package astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import astar.ITileNode;

/**
 * Purpose: Keeps set of already processed TileNodes
 * 
 */
public class ClosedSet
{
    private ArrayList<ITileNode> list;
    private Comparator<ITileNode> comp;
    //Constructor
    public ClosedSet(Comparator<ITileNode> comp) 
    {
        this.list = new ArrayList<ITileNode>();
        this.comp = comp;
    }
    
    //Helper methods 
    public boolean contains(ITileNode node) 
    {
        return this.list.contains(node);
    }

    public void add(ITileNode node) 
    {
        this.list.add(node);
    }
    //Remove all TileNode elements from Set
    public void clear() 
    {
        this.list.clear();
    }
    //Gets element from Set, based on smallest value sort order defined by comp 
    public ITileNode min() 
    {
        return Collections.min(this.list, this.comp);
    }

    @Override
    public String toString() 
    {
        return "("+ this.list +")";
    }

}
