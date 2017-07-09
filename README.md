# astar
Java application, using A* algorithm, to calculate shortest path beween two nodes in graph.

Includes:
1. Main file "AStartMain.java" to start application.
2. Factory classes to build graph and nodes.
3. A* classes to perform algorithm.
4. Test Unit classes 
5. Sample test file data


Design Method:
Object-oriented classes for graphs, tileNodes, tileTokens( default tile properties) and algorithm.
Factory pattern to build class objects for graph and to load data from various inputs:, eg text lines. 
Singleton pattern for graph.
Interface classes to encapsulate data.
Iterator patterns using array list and priority queues. Hashing of queue lists, using comparators,
  to improve performance lookup.


 