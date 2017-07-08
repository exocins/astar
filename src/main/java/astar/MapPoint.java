package astar;

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
    
    MapPoint() {}
    
    @Override public boolean equals(Object o) 
    {
      return o instanceof MapPoint && ((MapPoint) o).x == x && ((MapPoint) o).y == y;
    }
    
    @Override public int hashCode() 
    {
        return x * 37 + y;
    }
}
