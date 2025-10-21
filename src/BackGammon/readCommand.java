// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readCommand {

    private BufferedReader reader;

    // Constructor to initialize the reader
    public readCommand(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
    }

    // Method to read the next line
    public String readNextLine() throws IOException {
        return reader.readLine();
    }

    // Close the reader when done
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}