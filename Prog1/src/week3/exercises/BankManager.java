/**
 *
 */
package week3.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco
 */
public class BankManager {
    private String             name;
    private int                managerId;

    private List<BankCustomer> customerList = new ArrayList<>();

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
                + ((customerList == null) ? 0 : customerList.hashCode());
        result = prime * result + managerId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        BankManager other = (BankManager) obj;
        if (customerList == null) {
            if (other.customerList != null) {
                return false;
            }
        }
        else if (!customerList.equals(other.customerList)) {
            return false;
        }
        if (managerId != other.managerId) {
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
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BankManager [name=" + name + ", managerId=" + managerId
                + ", customerList=" + customerList + "]";
    }
}
