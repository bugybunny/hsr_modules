package ch.hsr.modules.db1.w14.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * The persistent class for the address database table.
 */
@Entity
public class Address {
    @Id
    private long         addressid;

    private String       city;

    private String       street;

    private int          zip;

    @OneToOne(mappedBy = "address")
    private BankCustomer customer;

    @OneToOne(mappedBy = "address")
    private BankManager  manager;

    public long getAddressid() {
        return addressid;
    }

    public void setAddressid(long addressid) {
        this.addressid = addressid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public BankCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(BankCustomer customer) {
        this.customer = customer;
    }

    public BankManager getManager() {
        return manager;
    }

    public void setManager(BankManager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\t" + addressid + "\t"
                + street + ", " + zip + " " + city;
    }

}
