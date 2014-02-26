package ch.hsr.modules.db1.w11.prepare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertCoffees {
    public static void main(String args[]) {
        final String user = "jdbctut";
        final String password = "jdbctut";
        final String database = "jdbc:postgresql://localhost/jdbctut";
        final String query = "select COF_NAME, PRICE from COFFEES";

        try (Connection connection = DriverManager.getConnection(database,
                user, password)) {

            try (Statement stmt = connection.createStatement()) {
                System.out.println("Inhalt von COFFEES loeschen");

                try (PreparedStatement prepStmt = connection
                        .prepareStatement("insert into COFFEES "
                                + "values(?, ?, ?, ?, ?)");) {
                    stmt.execute("delete from COFFEES");

                    List<Object[]> coffeeList = new ArrayList<>();
                    coffeeList
                            .add(new Object[] { "Colombian", 101, 7.99, 0, 0 });
                    coffeeList.add(new Object[] { "French_Roast", 49, 8.99, 0,
                            0 });
                    coffeeList
                            .add(new Object[] { "Espresso", 150, 9.99, 0, 0 });
                    coffeeList.add(new Object[] { "Colombian_Decaf", 101, 8.99,
                            0, 0 });
                    coffeeList.add(new Object[] { "French_Roast_Decaf", 49,
                            9.99, 0, 0 });

                    System.out.println("Inhalt von COFFEES abfuellen");
                    for (Object[] tempCoffee : coffeeList) {
                        prepStmt.setString(1, tempCoffee[0].toString());
                        prepStmt.setInt(2, (int) tempCoffee[1]);
                        prepStmt.setDouble(3, (double) tempCoffee[2]);
                        prepStmt.setInt(4, (int) tempCoffee[3]);
                        prepStmt.setInt(5, (int) tempCoffee[4]);
                        prepStmt.execute();
                    }

                    System.out.println("Coffee Break Coffees and Prices:");
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String s = rs.getString("COF_NAME");
                        float f = rs.getFloat("PRICE");
                        System.out.println(s + "   " + f);
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
