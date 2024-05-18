package se.kth.iv1350.POS.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale transaction.
 */
public class Sale {
    private List<Item> items = new ArrayList<>();

    /**
     * Adds an item to the sale. If the item already exists in the sale, its quantity is increased.
     *
     * @param item The item to be added to the sale.
     */
    public void addItem(Item item) {
        for (Item saleItem : items) {
            if (saleItem.getItemID().equals(item.getItemID())) {
                saleItem.increaseQuantity();
                return;
            }
        }
        items.add(item);
    }

    /**
     * @return A list of items in the sale.
     */
    public List<Item> getItems() {
        return new ArrayList<>(items); 
    }

    /**
     * @return The total cost including VAT.
     */
    public double getTotalCost() {
        return items.stream().mapToDouble(Item::getTotalCostIncludingVAT).sum();
    }

    /**
     * @return The total VAT amount.
     */
    public double getTotalVAT() {
        return items.stream().mapToDouble(Item::getVATAmount).sum();
    }
}
