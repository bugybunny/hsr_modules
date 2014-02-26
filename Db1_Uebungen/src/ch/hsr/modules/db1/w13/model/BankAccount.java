package ch.hsr.modules.db1.w13.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the bankaccount database table.
 * 
 */
@Entity
@Table(name = "bankaccount")
public class BankAccount {
    @Id
    @Column(name = "accountid")
    private int      id;
    private double   balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public int getId() {
        return id;
    }

    public void setId(int aId) {
        id = aId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double aBalance) {
        balance = aBalance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency aCurrency) {
        currency = aCurrency;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\t" + balance + "\t"
                + currency;
    }
}
