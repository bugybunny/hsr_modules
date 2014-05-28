package week14.task1;
import java.util.ArrayList;
import java.util.Collection;

public class PerformanceTest {
	private static final int NOF_THREADS = 10;
	private static final int NOF_ACTIONS = 100_000;
	private static final int START_BALANCE = 100;
	
	public static void main(String[] args) throws InterruptedException {
		
		long startTime = System.currentTimeMillis();
		
		Bank bank = new Bank();
		
		Account mainAccount = bank.openAccount("Main account");
		
		Collection<Thread> allThreads = new ArrayList<>();
		mainAccount.deposit(START_BALANCE);
		for (int k = 0; k < NOF_THREADS; k++) {
			String accountId = "Account " + k;
			allThreads.add(new Thread(() -> {
				Account privateAccount = bank.openAccount(accountId);
				for (int i = 0; i < NOF_ACTIONS; i++) {
					mainAccount.withdraw(100);
					mainAccount.deposit(100);
					Account.transfer(mainAccount, privateAccount, 1);
					Account.transfer(privateAccount, mainAccount, 1);
				}
				if (privateAccount.getBalance() != 0) {
					throw new AssertionError("Inconsistency due to race condition");
				}
			}));
		}
		for (Thread t : allThreads) {
			t.start();
		}
		for (Thread t : allThreads) {
			t.join();
		}
		if (mainAccount.getBalance() != START_BALANCE) {
			throw new AssertionError("Inconsistency due to race condition");
		}
		System.out.println("Completed: last update " + mainAccount.getLastUpdate());
		System.out.println("Bank accounts : " + bank.nofAccounts());
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time: " + (endTime - startTime) + "ms");
	}
}
