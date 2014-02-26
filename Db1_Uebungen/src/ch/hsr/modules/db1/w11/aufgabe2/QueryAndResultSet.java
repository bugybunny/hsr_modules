package ch.hsr.modules.db1.w11.aufgabe2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QueryAndResultSet {
    private final static Scanner in                        = new Scanner(
                                                                   System.in);
    private static final String  SELECT_COFFEE_LIKE_STRING = "SELECT * FROM Coffees WHERE cof_name ILIKE ?";

    public static String readCoffeeName() {
        System.out.println("What coffee are you looking for?");
        return in.nextLine();
    }

    public static void main(String args[]) {
        final String user = "jdbctut";
        final String password = "jdbctut";
        final String database = "jdbc:postgresql://localhost/jdbctut";

        try (Connection connection = DriverManager.getConnection(database,
                user, password)) {
            connection.setAutoCommit(false);
            connection
                    .setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            try (PreparedStatement prepStmt = connection
                    .prepareStatement(SELECT_COFFEE_LIKE_STRING)) {
                // build query
                prepStmt.setString(1, '%' + readCoffeeName() + '%');

                System.out.println(prepStmt);
                // output results
                ResultSet rs = prepStmt.executeQuery();
                System.out.println("Coffee Break Coffees and Prices:");
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
