/**
 *
 */
package week3.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco
 */
public class BankCustomer {
    private String            name;
    private String            prename;
    private int               age;
    private String            address;

    private BankManager       manager     = new BankManager();
    private List<BankAccount> accountList = new ArrayList<>();

    /**
     * Creates a new instance of this class.
     * 
     * @param aName
     * @param aPrename
     */
    public BankCustomer(String aName, String aPrename) {
        super();
        name = aName;
        prename = aPrename;
    }

    /**
     * Creates a new instance of this class.
     * 
     * @param aName
     * @param aPrename
     * @param aAge
     * @param aAddress
     */
    public BankCustomer(String aName, String aPrename, int aAge, String aAddress) {
        super();
        name = aName;
        prename = aPrename;
        age = aAge;
        address = aAddress;
    }

    public BankAccount openNewAccount(long anAccountNumber) {
        BankAccount account = new BankAccount(anAccountNumber, this);
        accountList.add(account);
        return account;

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the prename
     */
    public String getPrename() {
        return prename;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the manager
     */
    public BankManager getManager() {
        return manager;
    }

    /**
     * @return the accountList
     */
    public List<BankAccount> getAccountList() {
        return accountList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountList == null) ? 0 : accountList.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + age;
        result = prime * result + ((manager == null) ? 0 : manager.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((prename == null) ? 0 : prename.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BankCustomer other = (BankCustomer) obj;
        if (accountList == null) {
            if (other.accountList != null) {
                return false;
            }
        }
        else if (!accountList.equals(other.accountList)) {
            return false;
        }
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        }
        else if (!address.equals(other.address)) {
            return false;
        }
        if (age != other.age) {
            return false;
        }
        if (manager == null) {
            if (other.manager != null) {
                return false;
            }
        }
        else if (!manager.equals(other.manager)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!name.equals(other.name)) {
            return false;
        }
        if (prename == null) {
            if (other.prename != null) {
                return false;
            }
        }
        else if (!prename.equals(other.prename)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BankCustomer [name=" + name + ", prename=" + prename + ", age="
                + age + ", address=" + address + ", manager=" + manager
                + ", accountList=" + accountList + "]";
    }
}
