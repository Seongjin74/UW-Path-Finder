import java.util.List;

/*
 * An interface for the backend that takes an instance of the GraphADT as a constructor parameter
 * and exposes the following functionality to the frontend.
 */
public interface BackendInterface {

  /*
   * CONSTRUCTOR
   * public IndividualBackendInterface(GraphADT graph);
   * 
   */
  
  
  /*
   * Loads in the data file that stores building and walk time information
   * 
   * @param filePath the path of the file to be read
   */
  public void readDataFile(String filePath);

  /*
   * Finds and obtains the shortest path between any two buildings
   * 
   * @param startPoint the starting building
   * @param endPoint the final building on path
   */
  public List<String> shortestPath(String startPoint, String endPoint);

  /*
   * Returns a string that contains the number of buildings(Nodes), edges (Paths), and the total
   * time between all buildings (sum of all weights)
   */
  
  public String mapData();
  
  double getWalkingTimeBetween(String node1, String node2);

}
