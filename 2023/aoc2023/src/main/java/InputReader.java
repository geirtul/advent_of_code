import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputReader {
    public ArrayList<String> lineByLine(String filePath) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Strip leading and trailing spaces before adding to the list
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return lines;
    }


    public char[][] fileToCharArray(String filePath){
        try {
            // Get the number of rows and columns in the file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int numRows = (int) reader.lines().count();
            int numCols = reader.lines().findFirst().orElse("").length();

            // Reset the reader to the beginning of the file
            reader.close();
            reader = new BufferedReader(new FileReader(filePath));

            // Create a 2D array to store the characters
            char[][] matrix = new char[numRows][numCols];

            // Read each line and populate the 2D array
            for (int i = 0; i < numRows; i++) {
                matrix[i] = reader.readLine().toCharArray();
            }

            return matrix;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
