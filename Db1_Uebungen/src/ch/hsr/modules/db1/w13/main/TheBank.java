package ch.hsr.modules.db1.w13.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.hsr.modules.db1.w13.model.Address;
import ch.hsr.modules.db1.w13.model.BankAccount;
import ch.hsr.modules.db1.w13.model.BankCustomer;
import ch.hsr.modules.db1.w13.model.BankManager;

public class TheBank {

    private static EntityManagerFactory factory;

    /**
     * @param args
     */
    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory("Bank");
        EntityManager em = factory.createEntityManager();
        try {
            printAddresses(em);
            printBankAccounts(em);
            printBankCustomers(em);
            printBankManagers(em);
        }
        finally {
            em.close();
        }
    }

    private static void printAddresses(EntityManager em) {
        Query query = em.createQuery("select a from Address a");
        @SuppressWarnings("unchecked")
        List<Address> list = query.getResultList();
        for (Address b : list) {
            System.out.println(b);
        }
    }

    private static void printBankAccounts(EntityManager em) {
        Query query = em.createQuery("select a from BankAccount a");
        @SuppressWarnings("unchecked")
        List<BankAccount> list = query.getResultList();
        for (BankAccount b : list) {
            System.out.println(b);
        }
    }

    private static void printBankCustomers(EntityManager em) {
        Query query = em.createQuery("select a from BankCustomer a");
        @SuppressWarnings("unchecked")
        List<BankCustomer> list = query.getResultList();
        for (BankCustomer b : list) {
            System.out.println(b);
        }
    }

    private static void printBankManagers(EntityManager em) {
        Query query = em.createQuery("select a from BankManager a");
        @SuppressWarnings("unchecked")
        List<BankManager> list = query.getResultList();
        for (BankManager b : list) {
            System.out.println(b);
        }
    }

}
