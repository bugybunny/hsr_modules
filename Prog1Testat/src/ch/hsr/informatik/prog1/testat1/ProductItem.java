package ch.hsr.informatik.prog1.testat1;

/**
 * A ProductItem represents a sellable service product that has a price per unit
 * and the ordered amount.
 * 
 * @author Marco
 */
public class ProductItem extends Item {

    /**
     * Ordered amount of this item as double if there are items that can be
     * ordered in liters or kilograms whatever, the price per unit will be per
     * liter/kg then.
     */
    private double amount       = 0.0;
    /**
     * Price per unit. The total price will be calculated from this price (in
     * CHF) and the amount that was ordered. <br>
     * Initialize with {@code 0.1} if there’s an error with setting the value
     * and that we don’t give items for free.
     */
    private double pricePerUnit = 0.1;

    /**
     * Creates a new instance of this class with the given description, amount
     * and price per unit.
     * 
     * @param aDescription
     *            the description of this item
     * @param anAmount
     *            how many times this item was ordered, must be {@code > 0.0}
     * @param aPricePerUnit
     *            price per unit in CHF, must be {@code >0.0}
     */
    public ProductItem(String aDescription, double anAmount,
            double aPricePerUnit) {
        super(aDescription);
        if (anAmount > 0.0) {
            amount = anAmount;
        } else {
            throw new IllegalArgumentException(
                    "anAmount must be greater than 0.0");
        }
        if (aPricePerUnit > 0.0) {
            pricePerUnit = aPricePerUnit;
        } else {
            throw new IllegalArgumentException(
                    "aPricePerUnit must be greater than 0.0");
        }
    }

    /**
     * Calculates the prices for this whole product item order. The ordered
     * amount is multiplied with the price per unit.
     * 
     * @see Item#getPrice()
     */
    @Override
    public double getPrice() {
        return amount * pricePerUnit;
    }

    @Override
    public String toString() {
        return "ProductItem [amount=" + amount + ", pricePerUnit="
                + pricePerUnit + ", description=" + description + "]";
    }
}
