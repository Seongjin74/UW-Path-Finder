import java.util.List;

/*
 * Stores the shortest path between two buildings
 */
public interface ShortestPath {

  /*
   * Returns the list of buildings(nodes) along the path
   * 
   * @return a list of building along the route
   */
  public List<String> getPath();

  /*
   * Returns the list of walking times on each edge along the path
   * 
   * @return a list of walking times
   */
  public List<Double> getWalkingTimes();

  /*
   * Returns the estimated time of arrival for a person walking along the path
   * 
   * @return total time it takes to walk from start building to end building.
   */
  public double getETA();
}
