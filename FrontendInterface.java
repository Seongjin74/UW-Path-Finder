import java.util.Scanner;
/**
 * Interface for the frontend of UW Path Finder
 */

public interface FrontendInterface {
  
        /*
         * Constructor for the Frontend class.
         * public Frontend(Scanner scnr, Backend backend);
         */

        /**
         * This method prompt the user to select a command, and based on
         * their input, run the command they asked for or continue to ask for
         * information needed for that command. This is the Starts the main
         * command loop for the user interface
         */
        public void selectCommand(Scanner scnr);
                    
        /**
         * Prompts user to input the name of the data file for the dataset.
         */
        void promptForDataFile();
    
        /**
         * This method calls the backend method to load the data by calling the backend
         * method readDataFromFile with the reference object. It will also modify the
         * dataLoaded variable to indicate that data is being loaded.
         * 
         * @param fileName
         */
        void loadDataFile(String fileName);
        
        /**
         * Displays statistics about the dataset including the number of buildings,
         * number of edges, and the total walking time.
         */
        void displayDatasetStatistics();
        
        /**
         * This is a method that return the shortest path to the user.
         * @return the String of result
         */
        void displayShortestPath(String start, String end);
        
        /**
         * This method exit the app.
         */
        void exitApplication();
        
        
} 