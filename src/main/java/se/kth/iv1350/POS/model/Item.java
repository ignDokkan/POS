package se.kth.iv1350.POS.model;

/**
 * Represents an item that can be sold in the store.
 */
public class Item {
    private String itemID;
    private String itemName;
    private Price price;
    private String itemDescription;
    private int quantity;

    /**
     * Constructs an Item object with the specified properties.
     *
     * @param itemID          The ID of the item.
     * @param itemName        The name of the item.
     * @param cost            The cost of the item.
     * @param vatRate         The VAT rate applicable to the item.
     * @param itemDescription The description of the item.
     */
    public Item(String itemID, String itemName, double cost, double vatRate, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = new Price(cost, vatRate);
        this.itemDescription = itemDescription;
        this.quantity = 1;
    }

    /**
     * Increases the quantity of the item by one.
     */
    public void increaseQuantity() {
        this.quantity++;
    }

    /**
     * @return The item ID.
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @return The item name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @return The item cost.
     */
    public double getItemCost() {
        return price.getCost();
    }

    /**
     * @return The VAT rate.
     */
    public double getVatRate() {
        return price.getVatRate();
    }

    /**
     * @return The item description.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @return The item quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return The total cost including VAT.
     */
    public double getTotalCostIncludingVAT() {
        return price.getTotalCost() * quantity;
    }

    /**
     * @return The total VAT amount.
     */
    public double getVATAmount() {
        return price.getVATAmount() * quantity;
    }
}
