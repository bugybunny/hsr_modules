package ch.hsr.modules.db1.w11.aufgabe1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertCoffees {
    public static void main(String args[]) {
        final String user = "postgres";
        final String password = "jdbctut";
        final String database = "jdbc:postgresql://localhost/jdbctut";
        final String query = "select COF_NAME, PRICE from COFFEES";

        try (Connection connection = DriverManager.getConnection(database,
                user, password)) {

            try (Statement stmt = connection.createStatement()) {
                System.out.println("Inhalt von COFFEES loeschen");
                stmt.execute("delete from COFFEES");

                System.out.println("Inhalt von COFFEES abfuellen");

                System.out.println("Coffee Break Coffees and Prices:");
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String s = rs.getString("COF_NAME");
                    float f = rs.getFloat("PRICE");
                    System.out.println(s + "   " + f);
                }
            }
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
