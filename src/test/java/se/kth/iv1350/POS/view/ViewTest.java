package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.exceptions.ItemNotFoundException;
import se.kth.iv1350.POS.exceptions.FailedDatabaseException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the View class.
 */
public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        Controller contr = new Controller();
        instanceToTest = new View(contr);
        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testStartSale() {
        instanceToTest.startSale();
        String printout = printoutBuffer.toString();
        assertTrue(printout.contains("A new sale has been started."), "The startSale method did not output correctly.");
    }

    @Test
    public void testAddItemWithValidID() {
        instanceToTest.startSale(); // Needed to initialize the sale in the controller
        try {
            instanceToTest.addItem("abc123", 2); // Assume "abc123" is a valid item ID
        } catch (ItemNotFoundException | FailedDatabaseException e) {
            fail("Exception should not have been thrown for a valid item ID.");
        }
        String printout = printoutBuffer.toString();
        assertTrue(printout.contains("Added 2 item(s) with item id abc123"), "Item abc123 was not added correctly.");
    }

    @Test
    public void testAddItemWithInvalidID() {
        instanceToTest.startSale(); // Needed to initialize the sale in the controller
        try {
            instanceToTest.addItem("invalidID", 1); // Assume "invalidID" is an invalid item ID
            fail("Expected ItemNotFoundException to be thrown.");
        } catch (ItemNotFoundException e) {
            // Expected exception
        } catch (FailedDatabaseException e) {
            fail("Unexpected FailedDatabaseException was thrown.");
        }
        String printout = printoutBuffer.toString();
        assertTrue(printout.contains("Item with ID invalidID not found."), "Invalid item ID handling is incorrect.");
    }

    @Test
    public void testAddItemWithDatabaseError() {
        instanceToTest.startSale(); // Needed to initialize the sale in the controller
        try {
            instanceToTest.addItem("databaseErrorID", 1); // Assume "databaseErrorID" causes a database error
            fail("Expected FailedDatabaseException to be thrown.");
        } catch (ItemNotFoundException e) {
            fail("Unexpected ItemNotFoundException was thrown.");
        } catch (FailedDatabaseException e) {
            // Expected exception
        }
        String printout = printoutBuffer.toString();
        assertTrue(printout.contains("Failed to connect to the database."), "Database error handling is incorrect.");
    }

    @Test
    public void testEndSale() {
        instanceToTest.startSale(); // Needed to initialize the sale in the controller
        instanceToTest.endSale(100, 1); // Pass amount paid and updateDatabase value
        String printout = printoutBuffer.toString();
        assertTrue(printout.contains("End sale:"), "End sale output is incorrect.");
        assertTrue(printout.contains("Total cost (incl VAT):"), "Total cost was not displayed.");
    }
}
