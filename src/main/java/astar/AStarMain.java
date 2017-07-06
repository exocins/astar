package astar;

//import java.util.ArrayList;
import java.io.*;
import org.apache.commons.io.FileUtils;
//import java.io.File;
//import java.io.IOException;
import java.util.List;

//import astar;

public class AStarMain {
    //variables
    public static int x=0;
    public static int y=0;
    public static int xMax = 0;
    public static int yMax = 0;
    
    public static AStar graph;
    
    //Start function of program
    public static void main(String[] args) throws IOException {

        try {
            //
            
            //
            File f = new File("src/small_map.txt");

            System.out.println("Reading files using Apache IO:");

            List<String> lines = FileUtils.readLines(f, "UTF-8");
            x=0;
            y=0;
            yMax = lines.size();
            
            // loop each line in file
            for (String line : lines) {
                if(x == 0) 
                {
                    xMax = line.length();
                    //create graph
                    graph = new AStar(xMax,yMax);
                }
                x=0;    
                System.out.printf("\n");
                //System.out.println(line);
                
                //Add byte to tiles in graph
                for(char myByte : line .toCharArray()) {
                    System.out.printf("x=%d,y=%d,c=%c", x,y,myByte );
                    
                    //
                    MapPoint aPoint = new MapPoint(x,y);
                    graph.AddTokenToTile(aPoint, myByte);
                    
                    //next char in line
                    x++;
                }
                //Next line
                y++;
            }
            
            //
            graph.initGraphTileProperties();
            
            System.out.printf("\n xMax= %d, yMax= %d \n", xMax, yMax );
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
