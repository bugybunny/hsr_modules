package ch.hsr.modules.db1.w13.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "addressid")
    private int    id;
    private String street;
    private int    zip;
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int aId) {
        id = aId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String aStreet) {
        street = aStreet;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int aZip) {
        zip = aZip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String aCity) {
        city = aCity;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\t" + street + "\t" + zip
                + "\t" + city;
    }

}
