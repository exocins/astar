package astar;

/**
 * Purpose: Class for a map point on graph grid
 * 
 */

public class MapPoint 
{
    // Constants
    private final int INITIAL_HASH_OFFSET = 1000;
    //Internal fields
    int x;
    int y;
    //Constructors
    MapPoint(int x, int y) 
    {
      this.x = x;
      this.y = y;
    }
    // Compares this object to another 
    @Override 
    public boolean equals(Object o) 
    {
      return o instanceof MapPoint && ((MapPoint) o).x == x && ((MapPoint) o).y == y;
    }
    // Internal hashing code
    @Override 
    public int hashCode() 
    {
        return y * INITIAL_HASH_OFFSET + x;
    }
    // converts x,y to string
    @Override
    public String toString() 
    {
        return "("+ this.x+","+this.y+")";
    }

}
