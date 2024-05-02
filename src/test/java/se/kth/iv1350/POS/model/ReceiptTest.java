package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Test class for the Receipt model.
 */
public class ReceiptTest {
    private Sale sale;
    private Receipt receipt;
    private Item item1;
    private Item item2;
    private final double amountPaid = 100.0;
    private final double change = 10.0;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        item1 = new Item("001", "Apple", 10.0, 0.25, "Fresh apple");
        item2 = new Item("002", "Banana", 20.0, 0.25, "Fresh banana");
        sale.addItem(item1);
        sale.addItem(item2);
        receipt = new Receipt(sale, amountPaid, change);
    }

    @Test
    void testReceiptToString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String expectedReceipt = "Time of Sale: " + now.format(formatter) + "\n" +
                                 "\nItems Purchased:\n" +
                                 "Apple\t\t\t1 x 10.0 SEK\t 12.5 SEK\n" +
                                 "Banana\t\t\t1 x 20.0 SEK\t 25.0 SEK\n" +
                                 "Total: \t\t\t\t\t\t\t 37.5 SEK\n" +
                                 "VAT: \t\t\t\t\t    7.5 SEK\n" +
                                 "\nCash: \t\t\t\t\t\t\t100.0 SEK\n" +
                                 "Change: \t\t\t\t   10.0 SEK\n";
        //This line ensures that the receipt is tested only for the data content and not the exact timestamp.
        String actualReceipt = receipt.toString().substring(0, receipt.toString().indexOf("Time of Sale:") + 31) + 
                               receipt.toString().substring(receipt.toString().indexOf("\n"));

        assertEquals(expectedReceipt, actualReceipt, "Receipt string is not formatted correctly.");
    }
}
