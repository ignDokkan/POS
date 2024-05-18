package se.kth.iv1350.POS.controller;

import se.kth.iv1350.POS.integration.InventoryDatabase;
import se.kth.iv1350.POS.model.Item;
import se.kth.iv1350.POS.model.Sale;
import se.kth.iv1350.POS.model.Receipt;


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
     * @param itemID The ID of the item to be added to the sale.
     * @param amount The quantity of the item to be added.
     * @return A string with details of the added item or an empty string if the item is not found.
 */
    public String addItemToSale(String itemID, int amount) {
        Item item = getItemByID(itemID);
        if (item != null) {
            for (int i = 0; i < amount; i++) {
                sale.addItem(new Item(item.getItemID(), item.getItemName(), item.getItemCost(), item.getVatRate(), item.getItemDescription()));
            }
            return formatItemDetails(item, amount);
        }
        return "";
    }
    
    /**
     * Formats the details of an item to a readable string that includes the quantity added,
     * item ID, name, cost, VAT rate, and description.
     * 
     * @param item The item whose details are to be formatted.
     * @param amount The quantity of the item that has been added.
     * @return A formatted string containing the item details.
     */
    private String formatItemDetails(Item item, int amount) {
        return "Added " + amount + " item(s) with item id " + item.getItemID() + ":\n" +
               "Item Name: " + item.getItemName() + "\n" +
               "Item Cost: " + item.getItemCost() + " SEK\n" +
               "VAT: " + (item.getVatRate() * 100) + "%\n" +
               "Item Description: " + item.getItemDescription() + "\n";
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
    
    /**
    * Completes the sale process by calculating the total cost and then
    * creating a receipt
    * 
    * @param amountPaid The total amount paid by the customer.
    */
   public void endSale(double amountPaid) {
       Sale currentSale = getSale();
       double totalCost = currentSale.getTotalCost();
       double change = amountPaid - totalCost;
       completeSale(amountPaid);

       Receipt receipt = new Receipt(currentSale, amountPaid, change);
       printReceipt(receipt);
   }
   
   /**
    * Prints the receipt details.
    * 
    * @param receipt The receipt object containing details to be printed.
    */
    private void printReceipt(Receipt receipt) {
        System.out.println("---------------------------Begin receipt---------------------------");
        System.out.println(receipt.toString());
        System.out.println("----------------------------End receipt----------------------------");
    }
}

