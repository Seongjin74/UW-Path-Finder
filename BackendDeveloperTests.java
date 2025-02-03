import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;
public class BackendDeveloperTests {
    // test the Read Data File method
    @Test
    public void testReadDataFile() {
	    GraphADT<String, Double> graph = new DijkstraGraph<>(new PlaceholderMap());
	    Backend backend = new Backend(graph);
        backend.readDataFile("campus.dot");

        Assertions.assertNotNull(backend);
	Assertions.assertTrue(backend.containsNode("Wendt Commons"));
    }
    // Test the shortest path method
    @Test
    public void testShortestPath() {
        GraphADT<String, Double> graph = new DijkstraGraph<>(new PlaceholderMap());
        Backend backend = new Backend(graph);
        backend.readDataFile("campus.dot");
        List<String> path = backend.shortestPath("Russell Laboratories", "Wendt Commons"); 
        Assertions.assertTrue(!path.isEmpty());
    
    }
    // Test the map data method for the right amount of nodes and edges
    @Test
    public void testMapData() {
        GraphADT<String, Double> graph = new DijkstraGraph<>(new PlaceholderMap());
        Backend backend = new Backend(graph);
        backend.readDataFile("campus.dot");

//        System.out.println(backend.mapData());
        Assertions.assertTrue(backend.mapData().contains("nodes: 160"));
        Assertions.assertTrue(backend.mapData().contains("edges: 800"));


    }
    // Test for a shortest path for a graph which is empty
    @Test
    public void testShortestPathEmptyGraph() {
        GraphADT<String, Double> graph = new DijkstraGraph<>(new PlaceholderMap());
        Backend backend = new Backend(graph);
        // backend.readDataFile("campus.dot");

        boolean flag = false;
       	try {
       	    List<String> path = backend.shortestPath("Russell Laboratories", "InvalidNode");
        }
	    catch (Exception e) {
            flag = true;
	    }
        Assertions.assertTrue(flag);
    }
    // Test for the path with an Invalid Node
    @Test
    public void testShortestPathInvalidNode() {
	    GraphADT<String, Double> graph = new DijkstraGraph<>(new PlaceholderMap());
        Backend backend = new Backend(graph);
        backend.readDataFile("campus.dot");

        boolean flag = false;
        try {
            backend.shortestPath("InvalidNode", "Memorial Arch");
        }
        catch (Exception e) {
            flag = true;
        }
        Assertions.assertTrue(flag);
    }
}

