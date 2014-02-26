package ch.hsr.informatik.prog1.testat1;

/**
 * A ServiceItem represents a sellable service that has a flat price.
 * 
 * @author msyfrig
 */
public class ServiceItem extends Item {

    private double price = 0.1;

    /**
     * Creates a new instance of this class with the given description and the
     * price for this service.
     * 
     * @param aDescription
     *            description of this service item
     * @param aPrice
     *            price for this service in CHF
     */
    public ServiceItem(String aDescription, double aPrice) {
        super(aDescription);
        price = aPrice;
    }

    /**
     * @return the price for this service in CHF
     */
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ServiceItem [price=" + price + ", description=" + description
                + "]";
    }
}
