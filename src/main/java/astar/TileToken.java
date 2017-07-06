package astar;

public class TileToken {

    public boolean isStartToken = false;
    public boolean isDestinationTokem = false;
    // graph node moveability into
    public boolean walkable = false;
    public char tokenChar;
    public int tileCost= 0; 
    
    TileToken(char aChar)
    {
        this.tokenChar = aChar;
    }
    
    public void setTokenProperties(boolean start, boolean end, boolean walkable, int cost)
    {
        this.isStartToken = start;
        this.isDestinationTokem = end;
        this.walkable = walkable;
        this.tileCost = cost;
    }
}
