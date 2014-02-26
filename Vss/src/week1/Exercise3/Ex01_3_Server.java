package week1.Exercise3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Ex01_3_Server {
    public static final int PORT = 9595;

    public static void main(String[] args) throws Exception {
        System.out.println("[Server]Port:" + PORT);

        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = server.accept()) {
                    System.out.println("[Server]accepted");

                    try (BufferedReader fromClient = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()), 1);
                            BufferedWriter toClient = new BufferedWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream()), 1)) {
                        System.out.println("[Server]Lesen der Anfrage");
                        String input = fromClient.readLine();
                        if (input == null) {
                            System.err
                                    .println("[Server]Fehlerhafte Eingabe: \"String=\" fehlt");
                            continue;
                        }

                        // read over HEADER fields
                        while (!(input = fromClient.readLine()).equals("")) {
                            ;
                        }

                        input = fromClient.readLine();
                        System.out.println("[Server]Eingabe: " + input);

                        // invalid input?
                        if (input.indexOf("=") < 0) {
                            System.err
                                    .println("[Server]Fehlerhafte Eingabe: \"String=\" fehlt");
                            toClient.write("[Server]Falsche Eingabe (Zeichenkette): "
                                    + input);
                            toClient.newLine();
                            toClient.write("[Server]Erwartet wird: \"String=<IhreZeichenkette>\"");
                            toClient.newLine();
                            toClient.flush();
                            continue;
                        }

                        // String reverse and output
                        String reversed = new StringBuilder(
                                input.substring(input.indexOf("=") + 1))
                                .reverse().toString();
                        System.out.println("[Server]Reverse: Result="
                                + reversed);

                        toClient.write("HTTP/1.1 200 OK\n");
                        toClient.write("Date: " + new Date() + "\n");
                        toClient.write("Server: Apache\n");
                        toClient.newLine();
                        toClient.write(reversed);
                        toClient.flush();
                    }
                }
            }
        }
    }
}