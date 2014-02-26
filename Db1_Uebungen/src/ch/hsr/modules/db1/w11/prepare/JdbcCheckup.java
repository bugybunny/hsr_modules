package ch.hsr.modules.db1.w11.prepare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcCheckup {
    public static void main(String args[]) {
        final String user = "jdbctut";
        final String password = "jdbctut";
        final String database = "jdbc:postgresql://localhost/jdbctut";
        System.out.println("Connecting...");
        try (Connection conn = DriverManager.getConnection(database, user,
                password)) {
            System.out.println("connected.");

            try (Statement stmt = conn.createStatement()) {
                ResultSet rset = stmt.executeQuery("SELECT CURRENT_USER");
                while (rset.next()) {
                    System.out.println(rset.getString(1));
                }
                rset.close();
            }

            System.out.println("Your JDBC installation is correct.");
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println(e.toString());
        }
    }
}
