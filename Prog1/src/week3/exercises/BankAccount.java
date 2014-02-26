package week3.exercises;

/**
 * @author Marco
 */
public class BankAccount {
    private final long         accountnumber;
    private double             amount;
    private final BankCustomer owner;

    /**
     * Creates a new instance of this class.
     * 
     * @param aAccountnumber
     * @param aAmount
     */
    public BankAccount(long anAccountnumber, BankCustomer anOwner) {
        super();
        accountnumber = anAccountnumber;
        amount = 0;
        owner = anOwner;
    }

    public double deposit(double anAmount) {
        amount += anAmount;
        return amount;
    }

    public boolean withDraw(double anAmount) {
        boolean allowed = false;
        if (amount - anAmount >= 0) {
            amount -= anAmount;
            allowed = true;
        }

        return allowed;
    }

    public boolean sameCustomer(BankAccount anOtherBankAccount) {
        return anOtherBankAccount.getOwner().equals(owner);
    }

    public BankManager getManager() {
        return owner.getManager();
    }

    /**
     * @return the accountnumber
     */
    public long getAccountnumber() {
        return accountnumber;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the owner
     */
    public BankCustomer getOwner() {
        return owner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BankAccount [accountnumber=" + accountnumber + ", amount="
                + amount + ", owner=" + owner + "]";
    }
}
