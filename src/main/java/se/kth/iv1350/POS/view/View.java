package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.model.Item;
import se.kth.iv1350.POS.model.Sale;
import se.kth.iv1350.POS.model.Receipt;

/**
 * The View class represents the view in the POS system.
 * It handles user interactions and displays information.
 */
public class View {
    private Controller contr;

    /**
     * Constructs a View object with the specified controller.
     *
     * @param contr The controller to be associated with the view.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Starts a new sale.
     */
    public void startSale() {
        contr.startSale();
        System.out.println("A new sale has been started.");
    }
    
    /**
     * Ends the current sale and prints the receipt.
     */
    public void endSale() {
        Sale sale = contr.getSale(); 
        double totalCost = sale.getTotalCost();
        double amountPaid = 100.00; //Assuming the customer pays 100 SEK
        double change = amountPaid - totalCost; 
        contr.completeSale(amountPaid); 
        System.out.println("End sale:");
        System.out.println("Total cost (incl VAT): " + totalCost + " SEK");
        System.out.println("Total VAT: " + sale.getTotalVAT() + " SEK\n");
        printReceipt(sale, amountPaid, change); 
    }

    /**
     * Adds an item to the current sale.
     *
     * @param itemID The ID of the item to be added.
     * @param amount The quantity of the item to be added.
     */
    public void addItem(String itemID, int amount) {
        Item item = contr.getItemByID(itemID);
        //If the item is found in the database
        if (item != null) {
            contr.addItemToSale(itemID, amount);
            System.out.println("Add " + amount + " item(s) with item id " + itemID + ":");
            System.out.println("Item ID: " + item.getItemID());
            System.out.println("Item Name: " + item.getItemName());
            System.out.println("Item Cost: " + item.getItemCost() + " SEK");
            System.out.println("VAT: " + (item.getVatRate() * 100) + "%");
            System.out.println("Item Description: " + item.getItemDescription()+"\n");
        } else { 
            //If no item with the specified ID exists
            System.out.println("No item with the ID " + itemID + " exists");
        }   
    }

    /**
     * Prints the receipt for the current sale.
     *
     * @param sale       The sale for which the receipt is generated.
     * @param amountPaid The amount paid by the customer.
     * @param change     The change to be returned to the customer.
     */
    private void printReceipt(Sale sale, double amountPaid, double change) {
        Receipt receipt = new Receipt(sale, amountPaid, change);
        //Print the receipt to the terminal
        System.out.println("---------------------------Begin receipt---------------------------");
        System.out.println(receipt.toString());
        System.out.println("----------------------------End receipt----------------------------");
    }
}
