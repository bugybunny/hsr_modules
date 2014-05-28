package week14.task1.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import week14.task1.Account;

public class AccountTest extends ConcurrentTest {

    @Test
    public void testParallelWithdrawDeposit() throws InterruptedException {
        final Account account = new Account();
        final int startBalance = 100;

        Collection<Thread> allThreads = new ArrayList<>();
        account.deposit(startBalance);
        for (int k = 0; k < 10; k++) {
            allThreads.add(new Thread(() -> {
                for (int i = 0; i < 100000; i++) {
                    account.withdraw(100);
                    account.deposit(100);
                }
            }));
        }
        for (Thread t : allThreads) {
            t.start();
        }
        for (Thread t : allThreads) {
            t.join();
        }
        assertEquals("Race condition", startBalance, account.getBalance());
    }

    @Test
    public void testParallelTransfer() throws InterruptedException {
        Account account1 = new Account();
        Account account2 = new Account();
        final int startBalance = 100;

        Collection<Thread> allThreads = new ArrayList<>();
        account1.deposit(startBalance);
        for (int k = 0; k < 10; k++) {
            allThreads.add(new Thread(() -> {
                for (int i = 0; i < 100000; i++) {
                    Account.transfer(account1, account2, 100);
                    Account.transfer(account2, account1, 100);
                }
            }));
        }
        for (Thread t : allThreads) {
            t.start();
        }
        for (Thread t : allThreads) {
            t.join();
        }
        assertEquals("Race condition", startBalance, account1.getBalance());
        assertEquals("Race condition", 0, account2.getBalance());
    }

    @Test
    public void testLossFreeTransfer() throws InterruptedException {
        Account account1 = new Account();
        Account account2 = new Account();

        final int amount = 1000;
        account2.setClosed(true);
        account1.deposit(amount);
        try {
            Account.transfer(account1, account2, amount);
            fail("Account not closed");
        }
        catch (RuntimeException e) {
        }

        assertEquals("Money lost",
                account1.getBalance() + account2.getBalance(), amount);
    }
}
