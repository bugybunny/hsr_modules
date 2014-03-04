package week2.exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class BankAccount {
    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance += amount;
        notify();
    }

    public synchronized boolean withdraw(int amount) {
        while (amount > balance) {

        }
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}

class BankCreditCustomer extends Thread {
    private static final int  NOF_TRANSACTIONS = 100;
    private final BankAccount account;
    private final int         amount;

    public BankCreditCustomer(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < NOF_TRANSACTIONS; i++) {
            while (account.getBalance() < amount) {
                try {
                    wait();
                }
                catch (InterruptedException anEx) {
                    anEx.printStackTrace();
                }
            }
            account.withdraw(amount);
            System.out.println("Use credit " + amount + " by "
                    + Thread.currentThread().getName());
            account.deposit(amount);
        }
    }
}

public class BankTest2 {
    private static final int NOF_CUSTOMERS = 10;
    private static final int START_BUDGET  = 1000;

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        List<BankCreditCustomer> customers = new ArrayList<>();
        Random random = new Random(4711);
        for (int i = 0; i < NOF_CUSTOMERS; i++) {
            customers
                    .add(new BankCreditCustomer(account, random.nextInt(1000)));
        }
        for (BankCreditCustomer customer : customers) {
            customer.start();
        }
        account.deposit(START_BUDGET);
        for (BankCreditCustomer customer : customers) {
            customer.join();
        }
    }
}
