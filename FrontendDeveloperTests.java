// --== CS400 File Header Information ==--
// Name: Seongjin Park
// Email: spark644@wisc.edu
// Group and Team: G28
// Group TA: GRANT WALDOW
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Scanner;
import java.util.List;

public class FrontendDeveloperTests {

    MapADT<String, BaseGraph<String, Double>.Node> map = new PlaceholderMap<>();
    GraphADT<String, Double> graph = new DijkstraGraph<>(map);
    BackendInterface backendInstance = new Backend(graph);
    Frontend sampleFrontend = new Frontend(new Scanner(System.in), backendInstance);

    /**
     * Test loading a data file with an invalid filename.
     */
    @Test
    public void testInvalidDataFileLoading() {
        TextUITester tester = new TextUITester("invalidFileName.txt");
        sampleFrontend.loadDataFile("invalidFileName.txt");
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Error loading data file"));
    }

    /**
     * Test displaying statistics without loading data.
     */
    @Test
    public void testDisplayStatisticsWithoutData() {
        String input = "2\n4\n"; // Simulating user input for display statistics command and exit
        Scanner scanner = new Scanner(input);
        Frontend sampleFrontend = new Frontend(scanner, backendInstance);

        TextUITester tester = new TextUITester(input);
        sampleFrontend.selectCommand(scanner);
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Please load data first"));
    }

    /**
     * Test Invalid Command Handling
     */
    @Test
    public void testInvalidCommandHandling() {
        String input = "invalidCommand\n4\n"; // Input an invalid command and then exit
        Scanner scanner = new Scanner(input);
        Frontend sampleFrontend = new Frontend(scanner, backendInstance);

        TextUITester tester = new TextUITester(input);
        sampleFrontend.selectCommand(scanner);
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Invalid command."));
    }

    /**
     * Test loading a valid data file and then displaying statistics.
     */
    @Test
    public void testLoadDataFileAndDisplayStatistics() {
        TextUITester tester = new TextUITester("campus.dot");
        sampleFrontend.loadDataFile("campus.dot");
        sampleFrontend.displayDatasetStatistics();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Number of nodes") && output.contains("Number of edges") && output.contains("Total walking time"));
    }

    /**
     * Exiting the application.
     */
    @Test
    public void testExitApplication() {
        TextUITester tester = new TextUITester("");
        sampleFrontend.exitApplication();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Exiting application"));
    }

/**
 * Test integration of shortest path functionality with nonexistent buildings.
 * It checks if the frontend correctly handles the case when the backend cannot find the buildings.
 */
@Test
public void testShortestPathWithNonexistentBuildingsIntegration() {
    String input = "1\ncampus.dot\n3\nNonexistentBuildingA\nNonexistentBuildingB\n4\n"; // Simulate loading data file and finding shortest path with nonexistent buildings
    Scanner scanner = new Scanner(input);
    Frontend sampleFrontend = new Frontend(scanner, backendInstance);

    TextUITester tester = new TextUITester(input);
    sampleFrontend.selectCommand(scanner);
    String output = tester.checkOutput();
    Assertions.assertTrue(output.contains("Error finding shortest path: Start or end node not found"));
}


/**
 * Test integration for loading an invalid data file.
 * It verifies the frontend's response when the backend reports a file loading failure.
 */
@Test
public void testLoadInvalidDataFileIntegration() {
    String input = "1\ninvalidFile.dot\ncampus.dot\n4\n"; // Simulate loading an invalid data file and then exiting
    Scanner scanner = new Scanner(input);
    Frontend sampleFrontend = new Frontend(scanner, backendInstance);

    TextUITester tester = new TextUITester(input);
    sampleFrontend.selectCommand(scanner);
    String output = tester.checkOutput();
    Assertions.assertTrue(output.contains("Please enter a valid file name."));
}

/**
 * Test cases for partner's code (Backend) to check if data loading is successful.
 * This indirectly verifies if buildings are likely added to the graph after loading data.
 */
@Test
public void testBuildingExistenceAfterLoadingData() {
    String input = "1\ncampus.dot\n4\n"; // Simulate loading a valid data file and then exiting
    Scanner scanner = new Scanner(input);
    Frontend sampleFrontend = new Frontend(scanner, backendInstance);

    TextUITester tester = new TextUITester(input);
    sampleFrontend.selectCommand(scanner);
    String output = tester.checkOutput();

    Assertions.assertTrue(output.contains("Data file loaded successfully."));
}
    /**
     * Test cases for  partner's code(Backend) of shortest path functionality with valid buildings.
     * It checks if the frontend correctly handles the case when the backend successfully finds a path.
     */    
    @Test
    public void testShortestPathWithValidBuildingsIntegration() {
        String input = "1\ncampus.dot\n3\nBascom Hall\nMemorial Union\n4\n"; // Simulate loading data file and finding shortest path with valid buildings
        Scanner scanner = new Scanner(input);
        Frontend sampleFrontend = new Frontend(scanner, backendInstance);

        TextUITester tester = new TextUITester(input);
        sampleFrontend.selectCommand(scanner);
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Shortest path from Bascom Hall to Memorial Union:"));
    	Assertions.assertTrue(output.contains("Education Building") && output.contains("Science Hall"));

    }
}
