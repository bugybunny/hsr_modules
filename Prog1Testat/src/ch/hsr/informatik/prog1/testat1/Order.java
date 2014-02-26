package ch.hsr.informatik.prog1.testat1;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Order {

    /** List that contains all items in this order. */
    private List<Item> itemList = new ArrayList<>();

    /**
     * Creates a new instance of this class.
     */
    public Order() {
        super();
    }

    /**
     * Creates a new instance of this class with the given itemlist.
     * 
     * @param aItemList
     *            the itemlist to set
     */
    public Order(List<Item> aItemList) {
        super();
        itemList = aItemList;
    }

    /**
     * Returns the total prico of all items in this order. The price is rounded
     * to 0.05 CHF (=5 centimes).
     * 
     * @return the total price of all items in this order in CHF
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;

        for (Item item : itemList) {
            totalPrice += item.getPrice();
        }

        // round to 0.05 CHF (Rappen)
        return (double) Math.round(totalPrice * 20) / 20;
    }

    /**
     * Prints all items with their values on the standard output stream.
     */
    public void printItems() {
        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    /**
     * @return the itemList
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * @param aItemList
     *            the itemList to set
     */
    public void setItemList(List<Item> aItemList) {
        itemList = aItemList;
    }

    /**
     * Adds an item to this order.
     * 
     * @param anItemToAdd
     */
    public void addItem(Item anItemToAdd) {
        itemList.add(anItemToAdd);
    }
}
