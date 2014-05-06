package week8.exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class BankCustomer extends Thread {
    private static final int NofTransactions = 1000000;
    private BankAccount      account;
    private int              maxCredit;

    public BankCustomer(BankAccount account, int maxCredit) {
        this.account = account;
        this.maxCredit = maxCredit;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < NofTransactions; i++) {
            int amount = random.nextInt(maxCredit);
            account.deposit(amount);
            boolean success = account.withdraw(amount);
            if (!success) {
                throw new AssertionError("Race condition");
            }
        }
    }
}

public class BankTest {
    private final static int NofCustomers = 10;
    private final static int StartBudget  = 1000;

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        List<BankCustomer> customers = new ArrayList<BankCustomer>();
        Random random = new Random(4711);
        for (int i = 0; i < NofCustomers; i++) {
            customers
                    .add(new BankCustomer(account, random.nextInt(StartBudget)));
        }
        account.deposit(StartBudget);
        long startTime = System.currentTimeMillis();
        for (BankCustomer c : customers) {
            c.start();
        }
        for (BankCustomer c : customers) {
            c.join();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Total time " + (stopTime - startTime) + " ms");
        if (account.getBalance() != StartBudget) {
            throw new AssertionError("Incorrect final balance: "
                    + account.getBalance());
        }
    }
}
