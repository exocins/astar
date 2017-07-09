package astar;

import astar.ITileNode;
/**
 * Purpose:  Interface of OpenSet. Keeps set of to be processed TileNodes
 * 
 */

public interface IOpenSet {
    
    public void add(ITileNode node);
    public void remove(ITileNode node);
    public void clear();
    public ITileNode poll();
    //returns node if present otherwise null
    public ITileNode getNode(ITileNode node);
    public int getSize();

}