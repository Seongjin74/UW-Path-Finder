import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in its node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * Constructor that sets the map that the graph uses.
     * @param map the map that the graph uses to map a data object to the node
     *        object it is stored in
     */
    public DijkstraGraph(MapADT<NodeType, Node> map) {
        super(map);
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        
	// Check if the start or end nodes are in the graph if not throw exception.   
	if (!nodes.containsKey(start) || !nodes.containsKey(end)) {
            throw new NoSuchElementException("Start or end node not found");
        }

	// Create a map to keep track of visited nodes to prevent revisiting.
        MapADT<NodeType, SearchNode> visited = new PlaceholderMap<>();
        
 	// PriorityQueue to pick the next lowest path cost node.
	PriorityQueue<SearchNode> pq = new PriorityQueue<>();
        
	// add the starting node to the priority queue with zero cost because it is beggining.
	pq.add(new SearchNode(nodes.get(start), 0, null));
        
        // loop until there are no more nodes to visit
        while (!pq.isEmpty()) {
            SearchNode currentNode = pq.poll();
            NodeType currentData = currentNode.node.data;
            
            // If the current node is the destination then return as end 
            if (currentData.equals(end)) {
                return currentNode;
            }
            // If this node has been visited before then continue to skip
            if (visited.containsKey(currentData)) {
                continue;
            }
             // Mark the current node as visited
            visited.put(currentData, currentNode);

	    // Check all the neighbors of current node.
            for (Edge edge : currentNode.node.edgesLeaving) {
                NodeType successorData = edge.successor.data;
		// Calculate the new cost to reach this neighbor.
		double newCost = currentNode.cost + edge.data.doubleValue();

		// If the neighbor hasn't been visited or the new cost is lower than the previous cost, add this neighbor to the priority queue.
                if (!visited.containsKey(successorData) || visited.get(successorData).cost > newCost) {
                    pq.add(new SearchNode(edge.successor, newCost, currentNode));
                }
            }
        }
       // If the priority queue is empty and end is not reached then path not found.
        throw new NoSuchElementException("Path not found");
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {

	// Find the end node 
	SearchNode endNode = computeShortestPath(start, end);
        
	// list to store the path
	List<NodeType> path = new LinkedList<>();
        while (endNode != null) {
           // add node's data to beginning list, this will eventually start with the start node and end with the end node
	    path.add(0, endNode.node.data);
            endNode = endNode.predecessor;
        }
        return path;
	
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
           return computeShortestPath(start, end).cost;

    }

    //@Test
    //public void testPrecomputedPath() {
    //    DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());

	// insert graph with node and edges 
      	    // graph.insertNode("A");
            // graph.insertNode("B");
            // graph.insertNode("C");
            // graph.insertNode("D");
            // graph.insertNode("E");
            // graph.insertNode("F");
            // graph.insertNode("G");
            // graph.insertNode("H");

            // graph.insertEdge("A", "B", 4);
            // graph.insertEdge("A", "C", 2);
            // graph.insertEdge("A", "E", 15);
            // graph.insertEdge("B", "E", 10);
            // graph.insertEdge("B", "D", 1);
            // graph.insertEdge("C", "D", 5);
            // graph.insertEdge("D", "E", 3);
            // graph.insertEdge("D", "F", 0);
            // graph.insertEdge("F", "D", 2);
            // graph.insertEdge("F", "H", 4);
            // graph.insertEdge("G", "H", 4);
	
	// expected cost is 8 and path is ABDE
	//     assertEquals(8, graph.shortestPathCost("A", "E"));
    //         assertEquals(List.of("A", "B", "D", "E"), graph.shortestPathData("A", "E"));
    // }
		 
    // @Test
    // public void testDifferentPath() {
    //     DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());

	// // insert graph with node and edges
	//     graph.insertNode("A");
    //         graph.insertNode("B");
    //         graph.insertNode("C");
    //         graph.insertNode("D");
    //         graph.insertNode("E");
    //         graph.insertNode("F");
    //         graph.insertNode("G");
    //         graph.insertNode("H");

    //         graph.insertEdge("A", "B", 4);
    //         graph.insertEdge("A", "C", 2);
    //         graph.insertEdge("A", "E", 15);
    //         graph.insertEdge("B", "E", 10);
    //         graph.insertEdge("B", "D", 1);
    //         graph.insertEdge("C", "D", 5);
    //         graph.insertEdge("D", "E", 3);
    //         graph.insertEdge("D", "F", 0);
    //         graph.insertEdge("F", "D", 2);
    //         graph.insertEdge("F", "H", 4);
    //         graph.insertEdge("G", "H", 4);

	//  // expected cost is 1 and path is BF
	//     assertEquals(1, graph.shortestPathCost("B", "F"));
    //         assertEquals(List.of("B", "D", "F"), graph.shortestPathData("B", "F"));

    // }

//     @Test
//     public void testNoPath() {
//         DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());

// 	// insert graph with node and edges
// 	    graph.insertNode("A");
//             graph.insertNode("B");
//             graph.insertNode("C");
//             graph.insertNode("D");
//             graph.insertNode("E");
//             graph.insertNode("F");
//             graph.insertNode("G");
//             graph.insertNode("H");

//             graph.insertEdge("A", "B", 4);
//             graph.insertEdge("A", "C", 2);
//             graph.insertEdge("A", "E", 15);
//             graph.insertEdge("B", "E", 10);
//             graph.insertEdge("B", "D", 1);
//             graph.insertEdge("C", "D", 5);
//             graph.insertEdge("D", "E", 3);
//             graph.insertEdge("D", "F", 0);
//             graph.insertEdge("F", "D", 2);
//             graph.insertEdge("F", "H", 4);
//             graph.insertEdge("G", "H", 4);
	
// 	// expected cost is infinity and path data is empty 
    	
//     try {
//         graph.shortestPathCost("E", "G");
//         fail("Expected a NoSuchElementException for no path");
//     } catch (NoSuchElementException e) {
//         // Expected exception
//     }

//     try {
//         List<String> path = graph.shortestPathData("E", "G");
//         assertTrue(path.isEmpty(), "Path should be empty when no");
//     } catch (NoSuchElementException e) {
//         // Expected exception
//     }
// }
    
    }


