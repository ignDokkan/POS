package se.kth.iv1350.POS.exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemID) {
        super("No item with the ID " + itemID + " exists.");
    }
}
