package astar;

/**
 * Purpose: Class for a map point on graph grid
 * 
 */

public class MapPoint 
{
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
        return x * 1000 + y;
    }
    // converts x,y to string
    @Override
    public String toString() 
    {
        return "("+ this.x+","+this.y+")";
    }

}
