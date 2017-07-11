# astar
Java application, using A* algorithm, to calculate shortest path beween two nodes in graph.

Includes:
1. Main file "AStartMain.java" to start application.
2. Factory classes to build graph and nodes.
3. A* classes to perform algorithm.
4. Test Unit classes 
5. Sample test file data


Design Thoughts:
1. Object-oriented classes for graphs, tileNodes, tileTokens( default tile properties) and algorithm. 
2. Factory pattern to build class objects for graph and to load data from various inputs:, eg text lines. 
3. Singleton pattern for graph. 
4. Interface classes to encapsulate data. 
5. Iterator patterns using maps, list and priority queues. 
6. Hashing of queue lists, using comparators, to improve performance lookup. 

Pre-Build :
1. Use Eclipse IDE 
2. Install libraries:
   1.1. Java JDK and JRE for Java 1.8 
   1.2. common.io (include in folder thirdparty) 
   1.3. commons.cli (include in folder thirdparty) 
   1.4. junit4

Build:
1. Load project from astar directory.
2. Note: AStarMain.java contains main() function and generates AstarMain.class.
3. Run project to see no compile errors.
4. In Eclipse, select menu: File->Export->Java->Jar file
5. Follow prompts to select files and select jar filename and destination.

Run arguments: -i inputMapFile.txt
 This is optional, if default filename "src/small_map.txt" is used.

 Output:
  "src/out_path.txt" is created.
 