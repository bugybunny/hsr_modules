package ch.hsr.informatik.prog1.testat1;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@code BundleItem} is a store for multiple {@link Item}s and it’s
 * subclasses. It’s possible to give a discount and calculate the reduced price.
 * 
 * @author msyfrig
 */
public class BundleItem extends Item {

    /**
     * The discountInPercent of the whole bundle.
     */
    private final double discountInPercent;

    /**
     * All items in this bundle. It is allowed to store bundles in this list.
     */
    private List<Item>   itemList = new ArrayList<>();

    /**
     * Creates a new instance of this class.
     * 
     * @param aDescription
     */
    public BundleItem(String aDescription, double aDiscountInPercent) {
        super(aDescription);
        discountInPercent = aDiscountInPercent;
    }

    /**
     * Calculates the price of all items in this order less the discount. The
     * result is rounded to 0.05 CHF.
     * 
     * @return the discounted price of all items in this bundle
     */
    @Override
    public double getPrice() {
        double totalPrice = 0.0;
        for (Item item : itemList) {
            totalPrice += item.getPrice();
        }
        double reducedPrice = totalPrice
                - (totalPrice / 100 * discountInPercent);

        return (double) Math.round(reducedPrice * 20) / 20;
    }

    /**
     * @return the discountInPercent
     */
    public double getDiscount() {
        return discountInPercent;
    }

    /**
     * @return the itemList
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Sets the list of items in this bundle. Already stored values will be
     * overwritten and also note, that the item with
     * 
     * @param anItemList
     *            the itemList to set
     */
    public void setItemList(List<Item> anItemList) {
        // prevent that we get in an infinite loop, if the list contains this
        // instance
        // we would get in an infinite loop in #getPrice()
        while (anItemList.contains(this)) {
            anItemList.remove(this);
        }
        itemList = anItemList;
    }

    /**
     * Adds an item to the itemlist of this bundle and returns if it was added
     * successfully or not. The item won’t be added if it has the same reference
     * as {@code this}.
     * 
     * @param anItemToAdd
     * @return {@code true} if the item has been added, otherwise {@code false}
     */
    public boolean addItem(Item anItemToAdd) {
        boolean added = false;
        if (anItemToAdd != this) {
            itemList.add(anItemToAdd);
            added = true;
        }

        return added;
    }

    /**
     * @return the discountInPercent
     */
    public double getDiscountInPercent() {
        return discountInPercent;
    }

    @Override
    public String toString() {
        return "BundleItem [discountInPercent=" + discountInPercent
                + ", itemList=" + itemList + ", description=" + description
                + "]";
    }
}
