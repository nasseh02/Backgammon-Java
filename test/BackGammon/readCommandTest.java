// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class readCommandTest {

    // Tests the read file function
    @Test
    public void testReadFile() throws IOException {
        readCommand reader = new readCommand("test//BackGammon//commands.txt");
        String line = reader.readNextLine();
        assertNotNull(line);
        reader.close();
    }
}
