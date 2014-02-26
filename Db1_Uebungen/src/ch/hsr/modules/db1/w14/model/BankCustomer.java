package ch.hsr.modules.db1.w14.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The persistent class for the bankcustomer database table.
 * 
 */
@Entity
public class BankCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long                    customerid;

    private Date                    birthdate;

    @OneToOne(optional = true)
    @JoinColumn(name = "Customer_AddressId")
    private Address                 address;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    private Collection<BankManager> managers = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "Account_CustomerId", referencedColumnName = "CustomerId")
    private Collection<BankAccount> accounts = new ArrayList<>();

    private String                  name;

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<BankManager> getManagers() {
        return managers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManagers(Collection<BankManager> managers) {
        this.managers = managers;
    }

    public Collection<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\t" + customerid + "\t"
                + name + "\t" + birthdate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BankCustomer)) {
            return false;
        }
        return ((BankCustomer) obj).customerid == customerid;
    }

}
