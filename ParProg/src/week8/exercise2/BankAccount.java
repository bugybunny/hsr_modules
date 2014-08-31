package week8.exercise2;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
  private AtomicInteger balance = new AtomicInteger();

  public void deposit(int amount) {
    balance.addAndGet(amount);
  }

  public boolean withdraw(int amount) {
    int oldValue = 0;
    do {
      oldValue = balance.get();
      if (amount >= oldValue) {
        return false;
      }
    } while (!balance.compareAndSet(oldValue, oldValue - amount));

    return true;
  }

  public int getBalance() {
    return balance.intValue();
  }
}
