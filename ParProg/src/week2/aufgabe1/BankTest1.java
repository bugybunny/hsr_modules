package week2.aufgabe1;

import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance += amount;
        notify();
    }

    public synchronized void withdraw(int amount) throws InterruptedException {
        while (amount > balance) {
            wait();
        }
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}

class BankCustomer extends Thread {
    private final static int  NOF_TRANSACTIONS = 10_000_000;
    private final BankAccount account;

    public BankCustomer(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int k = 0; k < NOF_TRANSACTIONS; k++) {
            account.deposit(100);
            try {
                account.withdraw(100);
            }
            catch (InterruptedException anEx) {
                System.err.println("withdraw had a race condition error");
                anEx.printStackTrace();
            }
        }
    }
}

public class BankTest1 {
    private final static int NOF_CUSTOMERS = 10;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BankAccount account = new BankAccount();
        List<BankCustomer> customerThreads = new ArrayList<>(NOF_CUSTOMERS);
        for (int i = 0; i < NOF_CUSTOMERS; i++) {
            BankCustomer customer = new BankCustomer(account);
            customerThreads.add(customer);
            customer.start();
        }
        for (BankCustomer tempCustomerThread : customerThreads) {
            try {
                tempCustomerThread.join();
            }
            catch (InterruptedException anEx) {
                // TODO Auto-generated catch block
                anEx.printStackTrace();
            }
        }

        System.out.println(account.getBalance());
        System.out.println("Runtime: "
                + (System.currentTimeMillis() - startTime) + "ms");
    }
}
