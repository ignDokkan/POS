package se.kth.iv1350.POS.controller;

import se.kth.iv1350.POS.integration.InventoryDatabase;
import se.kth.iv1350.POS.model.Item;
import se.kth.iv1350.POS.model.Sale;

public class Controller {
    private Sale sale;
    private final InventoryDatabase inventoryDB = new InventoryDatabase();

    /**
     * Starts a new sale transaction.
     * This method must be called before any items can be added to the sale
     */
    public void startSale() {
        sale = new Sale();
    }

    /**
     * Retrieves an item by its ID from the inventory database.
     * 
     * @param itemID The ID of the item to retrieve
     * @return The item if found, null otherwise
     */
    public Item getItemByID(String itemID) {
        return inventoryDB.getItemByID(itemID);
    }

    /**
     * Adds a specified amount of an item to the current sale
     * If the item does not exist, nothing is added
     * 
     * @param itemID The ID of the item to add
     * @param amount The quantity of the item to add
     */
    public void addItemToSale(String itemID, int amount) {
        Item item = getItemByID(itemID);
        if (item != null) {
            for (int i = 0; i < amount; i++) {
                sale.addItem(new Item(item.getItemID(), item.getItemName(), item.getItemCost(), item.getVatRate(), item.getItemDescription()));
            }
        }
    }

    /**
     * @return The current sale
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * Completes the sale and sends sale data to external systems
     * 
     * @param amountPaid The amount paid by the customer.
     */
    public void completeSale(double amountPaid) {
        System.out.println("Customer pays " + amountPaid + " SEK:");
        sendSaleToAccountingSystem();
        updateInventorySystem();
    }

    /**
     * Simulates sending the sale information to an external accounting system
     */
    private void sendSaleToAccountingSystem() {
        System.out.println("Sent sale info to external accounting system.");
    }

    /**
     * Simulates updating the external inventory system
     */
    private void updateInventorySystem() {
        for (Item item : sale.getItems()) {
            System.out.println("Told external inventory system to decrease inventory quantity of item " + item.getItemID() + " by " + item.getQuantity() + " units.");
        }
    }
}
