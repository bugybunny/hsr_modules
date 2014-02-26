package ch.hsr.modules.db1.w12.aufgabe1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SQLMinus {
    private static final String URL      = "jdbc:postgresql://localhost/angproj";
    private static final String USER     = "anguser";
    private static final String PASSWORD = "angproj";

    private static enum Command {
        Executed, Pending, Quit
    };

    private Connection     connection = null;
    private BufferedReader input;
    private boolean        connected  = false;

    static public void main(String args[]) {
        SQLMinus program = new SQLMinus();
        program.initializeConnection();
        program.processCommands();
        program.closeConnection();
    }

    private void processCommands() {
        StringBuffer buffer = new StringBuffer();
        int line = 1;
        Command res = Command.Pending;
        while (res != Command.Quit) {
            printPromt(line);
            String cmd = readCommand();
            res = interpretCmd(cmd, buffer);
            if (res == Command.Pending) {
                buffer.append(" " + cmd);
                line++;
            } else {
                buffer.setLength(0);
                line = 1;
            }
        }
    }

    private Command interpretCmd(String cmd, StringBuffer buffer) {
        if (cmd.equals("commit")) {
            commit();
            return Command.Executed;
        } else if (cmd.equals("disconnect")) {
            disconnect();
            return Command.Executed;
        } else if (cmd.equals("connect")) {
            if (connected) {
                disconnect();
            }
            connected = connect();
            return Command.Executed;
        } else if (cmd.equals("go")) {
            if (!buffer.equals("")) {
                try {
                    executeStatement(buffer);
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            return Command.Executed;
        } else if (cmd.equals("quit")) {
            disconnect();
            return Command.Quit;
        } else if (cmd.equals("reset")) {
            return Command.Executed;
        } else if (cmd.equals("rollback")) {
            rollback();
            return Command.Executed;
        } else if (cmd.equals("show")) {
            try {
                showVersion();
            }
            catch (SQLException anEx) {
                anEx.printStackTrace();
            }
            return Command.Executed;
        } else {
            return Command.Pending;
        }
    }

    private void executeStatement(StringBuffer buff) throws SQLException {
        String sql = buff.toString();
        try (Statement statement = connection.createStatement()) {
            if (statement.execute(sql)) { // true if SQL text is a query
                processResults(statement.getResultSet());
            } else {
                int num = statement.getUpdateCount();
                if (num == 0) {
                    System.out.println("No rows affected.");
                } else if (num == 1) {
                    System.out.println("1 row affected.");
                } else {
                    System.out.println(num + " rows affected.");
                }
            }
        }
    }

    private String fill(char fillChar, int n) {
        char[] charArray = new char[n];
        Arrays.fill(charArray, fillChar);
        return String.copyValueOf(charArray);
    }

    private void processResults(ResultSet results) throws SQLException {
        ResultSetMetaData meta = results.getMetaData();
        System.out.println(fill('-', 10));
        for (int columnIndex = 1; columnIndex <= meta.getColumnCount(); columnIndex++) {
            if (columnIndex == 1) {
                System.out.print("   " + meta.getColumnLabel(columnIndex)
                        + "    ");
            } else {
                System.out.print("|   " + meta.getColumnLabel(columnIndex));
            }
        }
        System.out.println("\n" + fill('-', 25));

        int rows = 0;
        while (results.next()) {
            for (int columnIndex = 1; columnIndex <= meta.getColumnCount(); columnIndex++) {
                if (columnIndex == 1) {
                    System.out.print(results.getString(columnIndex));
                } else {
                    System.out.print("  |  " + results.getString(columnIndex));
                }
            }
            System.out.println("\n" + fill('-', 25));
            rows++;

        }
        System.out.println("found " + rows + " records");
    }

    private void showVersion() throws SQLException {
        System.out.println("SQLMinus v1.0");
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            System.out.println(dbm.getDriverVersion());
            System.out.printf("%s %s\n", dbm.getDatabaseProductName(),
                    dbm.getDatabaseProductVersion());
        }
        catch (SQLException e) {
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
        System.out.println("Connection closed.");
    }

    private String readCommand() {
        String tmp = "";
        try {
            tmp = input.readLine();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return tmp.trim();
    }

    private void printPromt(int line) {
        if (line == 1) {
            System.out.print("TM > ");
        } else {
            System.out.print(line + " -> ");
        }
        System.out.flush();
    }

    private void initializeConnection() {
        input = new BufferedReader(new InputStreamReader(System.in));
        connect();
    }

    private boolean connect() {
        if (!connected) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(false);
                connected = true;
                System.out.println("Connected to " + URL);
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Unable to connect to the database.");
                connected = false;
            }
        }
        return connected;
    }

    private void commit() {
        try {
            connection.commit();
            System.out.println("Commit successful.");
        }
        catch (SQLException e) {
            System.out.println("Error in commit: " + e.getMessage());
        }
    }

    private void rollback() {
        try {
            connection.rollback();
            System.out.println("Rollback successful.");
        }
        catch (SQLException e) {
            System.out.println("An error occurred during rollback: "
                    + e.getMessage());
        }
    }

    private void disconnect() {
        try {
            connection.close();
            connected = false;
            System.out.println("disconnected ... ");
        }
        catch (SQLException e) {
            System.out.println("Error in disconnect: " + e.getMessage());
        }
    }
}
