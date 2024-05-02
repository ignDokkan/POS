package se.kth.iv1350.POS.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Price model.
 */
public class PriceTest {
    private Price price;
    private final double BASE_COST = 100.0;  //Example base cost
    private final double VAT_RATE = 0.05;    //Example VAT rate of 5%

    @BeforeEach
    void setUp() {
        price = new Price(BASE_COST, VAT_RATE);
    }

    @Test
    void testGetCost() {
        //Check if the base cost is correctly returned
        assertEquals(BASE_COST, price.getCost(), "Base cost returned is incorrect.");
    }

    @Test
    void testGetVatRate() {
        //Check if the VAT rate is correctly returned
        assertEquals(VAT_RATE, price.getVatRate(), "VAT rate returned is incorrect.");
    }

    @Test
    void testGetTotalCost() {
        //Calculate expected total cost including VAT
        double expectedTotalCost = BASE_COST + (BASE_COST * VAT_RATE);
        assertEquals(expectedTotalCost, price.getTotalCost(), "Total cost including VAT is incorrect.");
    }

    @Test
    void testGetVATAmount() {
        //Calculate expected VAT amount
        double expectedVATAmount = BASE_COST * VAT_RATE;
        assertEquals(expectedVATAmount, price.getVATAmount(), "VAT amount calculated is incorrect.");
    }
}
