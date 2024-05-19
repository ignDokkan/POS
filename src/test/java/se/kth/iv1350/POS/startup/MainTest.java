package se.kth.iv1350.POS.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for Main.
 */
public class MainTest {
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testMainOutput() {
        String[] args = null;
        Main.main(args);
        String printout = printoutBuffer.toString();
        assertTrue(printout.contains("A new sale has been started."), "Sale did not start as expected.");
        assertTrue(printout.contains("Added 2 item(s) with item id abc123"), "Item abc123 was not added correctly.");
        assertTrue(printout.contains("Added 1 item(s) with item id def456"), "Item def456 was not added correctly.");
        assertTrue(printout.contains("Failed to connect to the database."), "Database error was not handled correctly.");
        assertTrue(printout.contains("Total revenue:"), "Total revenue was not calculated/displayed.");
    }

    @Test
    public void testTotalRevenueFileOutput() throws IOException {
        String[] args = null;
        Main.main(args);

        // Check if the file exists and contains the correct total revenue
        File file = new File("total_revenue.txt");
        assertTrue(file.exists(), "Total revenue file was not created.");

        String content = new String(Files.readAllBytes(Paths.get("total_revenue.txt")));
        assertTrue(content.contains("."), "Total revenue was not written to the file correctly.");
    }

    @Test
    public void testExceptionFileOutput() throws IOException {
        String[] args = null;
        Main.main(args);

        // Check if the exceptions file exists and contains the correct exception messages
        File file = new File("exceptions.txt");
        assertTrue(file.exists(), "Exceptions file was not created.");

        String content = new String(Files.readAllBytes(Paths.get("exceptions.txt")));
        assertTrue(content.contains("No item with the ID def46 exists."), "Exception was not written to the file correctly.");
    }
}
