package ch.hsr.modules.db1.w13.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the bankmanager database table.
 * 
 */
@Entity
@Table(name = "bankmanager")
public class BankManager {
    @Id
    @Column(name = "managerid")
    private int    id;
    private String name;

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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\t" + name;
    }

}
