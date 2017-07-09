package astar;

/**
 * Purpose: Class to keep properties on a path point.
 * 
 */
public class TileToken {

    // graph node movability properties
    public boolean walkable = false;
    public char tokenChar;
    public int moveCost= 0; 
    //Constructor
    TileToken()
    {
    }
    
    public void setDefaultProperties(char aChar, boolean walkable, int moveCost)
    {
        this.tokenChar = aChar;
        this.walkable = walkable;
        this.moveCost = moveCost;
    }
}
