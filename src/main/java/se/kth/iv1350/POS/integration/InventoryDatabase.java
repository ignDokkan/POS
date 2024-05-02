package se.kth.iv1350.POS.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.POS.model.Item;

public class InventoryDatabase {
    private List<Item> inventory;

    public InventoryDatabase() {
        this.inventory = new ArrayList<>();
        //Creates a basic list to "imitate" a database
        inventory.add(new Item("abc123", "BigWheel Oatmeal", 29.90, 0.06, "BigWheel Oatmeal 500 g , whole grain oats, high fiber, gluten free"));
        inventory.add(new Item("def456", "YouGoGo Blueberry", 14.90, 0.06, "YouGoGO Blueberry 240g, low sugar yoghurt, blueberry flavour"));
    }

    /**
    * Retrieves an item by its ID from the inventory.
    * This method searches through the list of items stored in the inventory and returns
    * the item if it matches the specified ID
    * 
    * @param itemID The ID of the item to be retrieved
    * @return The item with the matching ID if found; null otherwise
    */
    public Item getItemByID(String itemID) {
        for (Item item : inventory) {
            if (item.getItemID().equals(itemID)) {
                return item; 
            }
        }
        return null; 
    }
}
