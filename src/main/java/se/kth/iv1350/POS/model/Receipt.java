package se.kth.iv1350.POS.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a receipt containing details of the sale transaction.
 */
public class Receipt {
    private final LocalDateTime timeOfSale; 
    private final Sale sale; 
    private final double amountPaid; 
    private final double change;

    /**
     * Constructs a Receipt object with the specified sale, amount paid, and change.
     *
     * @param sale       The sale associated with the receipt.
     * @param amountPaid The amount paid by the customer.
     * @param change     The change to be returned to the customer.
     */
    public Receipt(Sale sale, double amountPaid, double change) {
        this.sale = sale;
        this.timeOfSale = LocalDateTime.now(); // Record the time of the sale
        this.amountPaid = amountPaid;
        this.change = change;
    }

    /**
     * @return A string containing all the information on the receipt.
     */
    @Override
    public String toString() {
        //Builds the receipt text
        StringBuilder receiptText = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        receiptText.append("Time of Sale: ").append(timeOfSale.format(formatter)).append("\n");
        receiptText.append("\nItems Purchased:\n");
        //Iterates through the items in the sale and adds details to the receipt
        for (Item item : sale.getItems()) {
            receiptText.append(item.getItemName())
                       .append("\t\t\t").append(item.getQuantity())
                       .append(" x ").append(item.getItemCost())
                       .append(" SEK\t ").append(item.getTotalCostIncludingVAT())
                       .append(" SEK\n");
        }
        receiptText.append("Total: \t\t\t\t\t\t\t ").append(sale.getTotalCost()).append(" SEK\n");
        receiptText.append("VAT: \t\t\t\t\t    ").append(sale.getTotalVAT()).append(" SEK\n");
        receiptText.append("\nCash: \t\t\t\t\t\t\t").append(amountPaid).append(" SEK\n");
        receiptText.append("Change: \t\t\t\t   ").append(change).append(" SEK\n");
        return receiptText.toString();
    }
}
