package ch.hsr.modules.db1.w11.prepare;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateCoffees {
    public static void main(String args[]) throws SQLException, IOException,
            ClassNotFoundException {

        final String user = "jdbctut";
        final String password = "jdbctut";
        final String database = "jdbc:postgresql://localhost/jdbctut";

        final String createString = "CREATE TABLE COFFEES "
                + "(COF_NAME varchar(32), " + "SUP_ID int, " + "PRICE float4, "
                + "SALES int, " + "TOTAL int)";

        try (Connection connection = DriverManager.getConnection(database,
                user, password)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createString);
            }
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
