
import java.util.ArrayList;
import java.util.List;

public class BackendPlaceholder implements BackendInterface {

    private boolean dataLoaded = false;
    private List<String> dummyPath = new ArrayList<>();
    private String dummyStatistics = "Number of buildings: 0, Number of edges: 0, Total walking time: 0";

    public BackendPlaceholder() {
        // Initialize dummy data for testing
        dummyPath.add("Building A");
        dummyPath.add("Building B");
    }

    @Override
    public void readDataFile(String filePath) {
        if (filePath.equals("validDataFile.txt")) {
            this.dataLoaded = true;
        } else {
            this.dataLoaded = false;
        }
    }

    @Override
    public List<String> shortestPath(String startPoint, String endPoint) {
        return new ArrayList<>(dummyPath);
    }

    @Override
    public String mapData() {
        return dummyStatistics;
    }
    
    @Override
    public double getWalkingTimeBetween(String node1, String node2) {
        // 더미 데이터를 사용하는 클래스이므로, 기본적으로 0.0을 반환하거나 임의의 값을 설정할 수 있음
        return 0.0; 
    }


}
