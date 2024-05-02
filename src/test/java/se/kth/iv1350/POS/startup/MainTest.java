package se.kth.iv1350.POS.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(printout.contains("Add 2 item(s) with item id abc123"), "Item abc123 was not added correctly.");
        assertTrue(printout.contains("Add 1 item(s) with item id def456"), "Item def456 was not added correctly.");
        assertTrue(printout.contains("End sale:"), "Sale did not end as expected.");
        assertTrue(printout.contains("Total cost (incl VAT):"), "Total cost was not calculated/displayed.");
    }    
}
