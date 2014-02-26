package week2;

import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private int balance = 0;

    public synchronized void withdraw(int amount) throws InterruptedException {
        while (amount > this.balance) {
            wait();
        }
        this.balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized void deposit(int amount) {
        this.balance += amount;
        notifyAll();
    }
}

public class BankMonitor {
    private final static int NOF_CUSTOMERS    = 10;
    private final static int NOF_TRANSACTIONS = 1000;

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        System.out.println(account.getBalance());
        List<Thread> customers = new ArrayList<>();
        for (int i = 0; i < NOF_CUSTOMERS; i++) {
            customers.add(new Thread(() -> {
                try {
                    for (int k = 0; k < NOF_TRANSACTIONS; k++) {
                        account.withdraw(100);
                        account.deposit(100);
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        account.deposit(100);
        for (Thread customer : customers) {
            customer.start();
        }
        for (Thread customer : customers) {
            customer.join();
        }
        System.out.println(account.getBalance());
    }
}
