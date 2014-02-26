package ch.hsr.modules.db1.w13.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the bankcustomer database table.
 * 
 */
@Entity
@Table(name = "bankcustomer")
public class BankCustomer {
    @Id
    @Column(name = "customerid")
    private int    id;
    private String name;
    private Date   birthdate;

    public int getId() {
        return id;
    }

    public void setId(int aId) {
        id = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date aBirthdate) {
        birthdate = aBirthdate;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\t" + name + "\t" + birthdate;
    }
}
