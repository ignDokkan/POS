package se.kth.iv1350.POS.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test class for the Sale model.
 */
public class SaleTest {
    private Sale sale;
    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        item1 = new Item("001", "Apple", 10.0, 0.25, "Red apple");
        item2 = new Item("002", "Banana", 20.0, 0.25, "Bralizian banana");
    }

    @Test
    void testAddNewItem() {
        sale.addItem(item1);
        List<Item> items = sale.getItems();
        assertTrue(items.contains(item1), "Item1 should be added to the sale.");
        assertEquals(1, items.size(), "Sale should contain one item.");
    }

    @Test
    void testAddSameItemIncreasesQuantity() {
        sale.addItem(item1);
        sale.addItem(item1);  // Add the same item again
        assertEquals(1, sale.getItems().size(), "Sale should still contain only one item.");
        assertEquals(2, item1.getQuantity(), "Quantity of item1 should be increased to 2.");
    }

    @Test
    void testAddDifferentItems() {
        sale.addItem(item1);
        sale.addItem(item2);
        List<Item> items = sale.getItems();
        assertTrue(items.contains(item1) && items.contains(item2), "Sale should contain both items.");
        assertEquals(2, items.size(), "Sale should contain two different items.");
    }

    @Test
    void testGetTotalCost() {
        sale.addItem(item1);
        sale.addItem(item2);
        double expectedTotal = item1.getTotalCostIncludingVAT() + item2.getTotalCostIncludingVAT();
        assertEquals(expectedTotal, sale.getTotalCost(), "Total cost should be the sum of both items' total cost including VAT.");
    }

    @Test
    void testGetTotalVAT() {
        sale.addItem(item1);
        sale.addItem(item2);
        double expectedTotalVAT = item1.getVATAmount() + item2.getVATAmount();
        assertEquals(expectedTotalVAT, sale.getTotalVAT(), "Total VAT should be the sum of both items' VAT.");
    }
}
