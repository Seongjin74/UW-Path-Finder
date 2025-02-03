// --== CS400 File Header Information ==--
// Name: Seongjin Park
// Email: spark644@wisc.edu
// Group and Team: G28
// Group TA: GRANT WALDOW
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

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

}
