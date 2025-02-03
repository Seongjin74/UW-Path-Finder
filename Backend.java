import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Backend implements BackendInterface {
    private GraphADT<String, Double> graph;
    private double totalWalkingTime = 0;
    public Backend(GraphADT<String, Double> graph) {
        this.graph = graph;
    }

    @Override
    /**
     * Read data from a DOT file and add nodes and edges to the graph.
     *
     * @param filePath the path to the DOT file
     */
     public void readDataFile(String filePath) {
	// Open the dot file using Buffered Reader
	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while (!(line = br.readLine()).contains("}")) {
                String[] decode = line.split("\"");
		// Split the line by Double quotes and decode each Node and edge weight value
                String node1 = decode[1];
                String node2 = decode[3];
                String weight = decode[4].substring((decode[4].indexOf("=") + 1), decode[4].indexOf("]"));
                double seconds = Double.parseDouble(weight);

//                System.out.println(node1 + " -> " + node2 + " takes " + seconds);
                totalWalkingTime += seconds;
		// Add the node using helper function
                addNodeAndEdge(node1, node2, seconds);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNodeAndEdge(String node1, String node2, double seconds) {
        // Add nodes if they don't exist
        if (!containsNode(node1)) {
            graph.insertNode(node1);
        }
        if (!containsNode(node2)) {
            graph.insertNode(node2);
        }

        // Add the edge
        graph.insertEdge(node1, node2, seconds);
    }


    @Override
    public List<String> shortestPath(String startPoint, String endPoint) {
        // Return the shortest Path data 
	return graph.shortestPathData(startPoint, endPoint);
    }

    @Override
    public String mapData() {
        int numNodes = graph.getNodeCount();
        int numEdges = graph.getEdgeCount();

	// Return string containing map information
        return "Number of nodes: " + numNodes + "\nNumber of edges: " + numEdges +
                "\nTotal walking time: " + totalWalkingTime;
    }

 /**
 * Returns the walking time between two nodes.
 * @param node1 Starting node
 * @param node2 Destination node
 * @return Walking time in seconds, or -1 if no edge exists
 */
public double getWalkingTimeBetween(String node1, String node2) {
    if (graph.containsEdge(node1, node2)) {
        return graph.getEdge(node1, node2);
    }
    return -1; // Edge가 없을 경우
}
   

    public boolean containsNode(String data) {
        return graph.containsNode(data);
    }


    
}

