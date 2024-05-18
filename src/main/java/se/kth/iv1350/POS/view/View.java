package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.controller.Controller;

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
     * @param amount The amount paid by the customer
     */
    public void endSale(double amount) {
    contr.endSale(amount);
}


    /**
     * Adds an item to the current sale.
     *
     * @param itemID The ID of the item to be added.
     * @param amount The quantity of the item to be added.
     */
    public void addItem(String itemID, int amount) {
    String itemDetails = contr.addItemToSale(itemID, amount);
    if (!itemDetails.isEmpty()) {
        System.out.println(itemDetails);
    } else {
        System.out.println("No item with the ID " + itemID + " exists");
    }
}

}
