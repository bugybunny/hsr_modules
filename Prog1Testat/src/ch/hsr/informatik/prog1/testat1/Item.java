package ch.hsr.informatik.prog1.testat1;

/**
 * Abstract class for an item. Subclasses have to overwrite the
 * {@link #getPrice()} method.
 * 
 * @author msyfrig
 */
public abstract class Item {
    /** Description what the name and purpose of this item is, unlimited length. */
    protected String description = "";

    /**
     * Creates a new instance of this class with the given description.
     * 
     * @param aDescription
     *            the description of this item
     */
    protected Item(String aDescription) {
        super();
        description = aDescription;
    }

    public Item() {

    }

    /**
     * Calculates the total price of this item in CHF and returns the calculated
     * value.
     * 
     * @return the total price of this item in CHF
     */
    public abstract double getPrice();

    /**
     * Writes the class name and description to the standard output.
     */
    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Item [description=" + description + "]";
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}