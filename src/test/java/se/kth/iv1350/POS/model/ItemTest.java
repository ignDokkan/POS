package se.kth.iv1350.POS.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Item.
 */
public class ItemTest {
    private Item item;
    private final String testItemID = "001";
    private final String testItemName = "Apple";
    private final double testCost = 10.0;
    private final double testVatRate = 0.25;  // 25% VAT
    private final String testItemDescription = "Fresh apple";

    @BeforeEach
    void setUp() {
        //Create a new Item object before each test.
        item = new Item(testItemID, testItemName, testCost, testVatRate, testItemDescription);
    }

    @Test
    void testCreateItem() {
        //Test to ensure that the item is created with the correct attributes.
        assertEquals(testItemID, item.getItemID(), "Item ID not set correctly.");
        assertEquals(testItemName, item.getItemName(), "Item name not set correctly.");
        assertEquals(testCost, item.getItemCost(), "Item cost not set correctly.");
        assertEquals(testVatRate, item.getVatRate(), "VAT rate not set correctly.");
        assertEquals(testItemDescription, item.getItemDescription(), "Item description not set correctly.");
        assertEquals(1, item.getQuantity(), "Initial item quantity should be 1.");
    }

    @Test
    void testIncreaseQuantity() {
        //Test the increaseQuantity method increments the quantity correctly.
        item.increaseQuantity();
        item.increaseQuantity(); 
        assertEquals(3, item.getQuantity(), "Item quantity not incremented correctly.");
    }

    @Test
    void testGetTotalCostIncludingVAT() {
        //Test the total cost calculation including VAT.
        double expectedTotalCost = testCost * (1 + testVatRate);
        assertEquals(expectedTotalCost, item.getTotalCostIncludingVAT(), "Total cost including VAT not calculated correctly.");
        
        item.increaseQuantity();  //Add another item
        expectedTotalCost = (testCost * 2) * (1 + testVatRate);
        assertEquals(expectedTotalCost, item.getTotalCostIncludingVAT(), "Total cost including VAT not updated correctly after increase in quantity.");
    }

    @Test
    void testGetVATAmount() {
        //Test the VAT amount calculation.
        double expectedVATAmount = testCost * testVatRate;
        assertEquals(expectedVATAmount, item.getVATAmount(), "VAT amount not calculated correctly.");
        
        item.increaseQuantity();
        expectedVATAmount = (testCost * testVatRate) * 2;
        assertEquals(expectedVATAmount, item.getVATAmount(), "VAT amount not updated correctly after increase in quantity.");
    }
}
