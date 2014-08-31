package week14.task1;

import java.util.Map;

import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

public class Bank {
  private final Map<String, Account> accounts = STM.newMap();

  public Account openAccount(String name) {
    return STM.atomic(() -> {
      if (getAccount(name) != null) {
        throw new RuntimeException("Account already exists");
      }
      Ref.View<Account> account = STM.newRef(new Account());
      accounts.put(name, account.get());
      return account.get();
    });
  }

  public int nofAccounts() {
    return accounts.size();
  }

  public Account getAccount(String name) {
    return accounts.get(name);
  }
}
