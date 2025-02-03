// --== CS400 File Header Information ==--
// Name: Seongjin Park
// Email: spark644@wisc.edu
// Group and Team: G28
// Group TA: GRANT WALDOW
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.Scanner;
import java.util.List;
import java.io.File;

/**
 * The Frontend class implements the user interface for interacting with the backend.
 * It handles user input and displays the output based on the backend processing.
 */
public class Frontend implements FrontendInterface {

    private BackendInterface backend;
    private Scanner scanner;
    private boolean dataLoaded = false;
	
    /**
     * Constructor to initialize the frontend with a scanner and backend instance.
     * @param scnr Scanner for user input
     * @param backendInstance Instance of the backend interface
     */
    public Frontend(Scanner scnr, BackendInterface backendInstance) {
        this.backend = backendInstance;
        this.scanner = scnr;
    }

    /**
     * Main method to select and execute commands based on user input.
     * @param scnr Scanner for user input
     */
    @Override
    public void selectCommand(Scanner scnr) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Select a command:");
            System.out.println("1. Load Data File");
            System.out.println("2. Display Dataset Statistics");
            System.out.println("3. Find Shortest Path");
            System.out.println("4. Exit");

            String choice = scnr.nextLine();

            switch (choice) {
                case "1":
                    promptForDataFile();
                    break;
                case "2":
                    displayDatasetStatistics();
                    break;
                case "3":
                    System.out.println("Enter start building:");
                    String start = scnr.nextLine();
                    System.out.println("Enter destination building:");
                    String end = scnr.nextLine();
                    displayShortestPath(start, end);
                    break;
                case "4":
                    exitApplication();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
	
    /**
     * Prompts user for data file name and loads it.
     */
    @Override
    public void promptForDataFile() {
        String fileName = "";
        while (fileName == null || fileName.trim().isEmpty()) {
            System.out.println("Enter the name of the data file:");
            fileName = scanner.nextLine();
            if (fileName == null || fileName.trim().isEmpty()) {
                System.out.println("Please enter a valid data file name.");
            } else {
                File file = new File(fileName);
                if (!file.exists() || file.isDirectory()) {
                    System.out.println("File does not exist or is a directory. Please enter a valid file name.");
                    fileName = "";
                }
            }
        }
        loadDataFile(fileName);
    }

    /**
     * Loads the data file specified by the filename.
     * @param fileName Name of the file to be loaded
     */
    @Override
    public void loadDataFile(String fileName) {
    File file = new File(fileName);
    if (!file.exists() || file.isDirectory()) {
        System.out.println("Error loading data file: File does not exist or is a directory.");
        dataLoaded = false;
        return; // Early return to avoid attempting to load a non-existent file
    }

    try {
        backend.readDataFile(fileName);
        System.out.println("Data file loaded successfully.");
        dataLoaded = true;
    } catch (Exception e) {
        System.out.println("Error loading data file: " + e.getMessage());
        dataLoaded = false;
    }
}
    /**
     * Displays dataset statistics if data is loaded.
     */
    @Override
    public void displayDatasetStatistics() {
        if (dataLoaded) {
            try {
                String stats = backend.mapData();
                System.out.println(stats);
            } catch (Exception e) {
                System.out.println("Error displaying statistics: " + e.getMessage());
            }
        } else {
            System.out.println("Please load data first.");
        }
    }
    
     /**
     * Displays the shortest path between two points if data is loaded.
     * @param start Starting point for the path
     * @param end Ending point for the path
     */
    @Override
    public void displayShortestPath(String start, String end) {
        if (dataLoaded) {
            try {
                List<String> path = backend.shortestPath(start, end);
                if (path != null && !path.isEmpty()) {
                    System.out.println("Shortest path from " + start + " to " + end + ":");

                    double totalWalkTime = 0;
                    
                    // 각 구간별 이동 시간 출력
                    for (int i = 0; i < path.size() - 1; i++) {
                        String from = path.get(i);
                        String to = path.get(i + 1);
                        double time = backend.getWalkingTimeBetween(from, to);
                        totalWalkTime += time;

                        System.out.println(from + " -> " + to + " : " + time + " seconds");
                    }

                    System.out.println("Total walk time: " + totalWalkTime + " seconds");
                } else {
                    System.out.println("Path not found.");
                }
            } catch (Exception e) {
                System.out.println("Error finding shortest path: " + e.getMessage());
            }
        } else {
            System.out.println("Please load data first.");
        }
    }

	
    /**
     * Exits the application.
     */
    @Override
    public void exitApplication() {
        System.out.println("Exiting application...");
    }

/**
 * Main method to run the integrated application.
 * This method creates instances of both the backend and frontend and starts the frontend's command loop.
 * @param args Command line arguments.
 */
public static void main(String[] args) {
        MapADT<String, BaseGraph<String, Double>.Node> map = new PlaceholderMap<>();

        GraphADT<String, Double> graph = new DijkstraGraph<>(map);

        BackendInterface backend = new Backend(graph);

        Scanner scanner = new Scanner(System.in);

        Frontend frontend = new Frontend(scanner, backend);

        frontend.selectCommand(scanner);

        scanner.close();


}




}
