package se.kth.iv1350.POS.model;

/**
 * Represents the price of an item, including cost and VAT rate.
 */
public class Price {
    private double cost; // The base cost of the item
    private double vatRate; // The VAT rate applicable to the item

    /**
     * Constructs a Price object with the specified cost and VAT rate.
     *
     * @param cost    The base cost of the item.
     * @param vatRate The VAT rate applicable to the item.
     */
    public Price(double cost, double vatRate) {
        this.cost = cost;
        this.vatRate = vatRate;
    }

    /**
     * @return The total cost including VAT.
     */
    public double getTotalCost() {
        return cost + getVATAmount();
    }

    /**
     * @return The VAT amount.
     */
    public double getVATAmount() {
        return cost * vatRate;
    }

    /**
     * @return The base cost.
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return The VAT rate.
     */
    public double getVatRate() {
        return vatRate;
    }
}
