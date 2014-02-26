package week1.Exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client2 {

    public static void doPost(String s) throws IOException {
        URL server = new URL(s);
        HttpURLConnection huc = (HttpURLConnection) server.openConnection();

        // huc.setRequestProperty("Accept", "text/html");

        huc.setDoOutput(true);
        huc.connect();

        PrintWriter pw0 = new PrintWriter(huc.getOutputStream());
        pw0.println("String= Hello World");
        pw0.close();

        InputStream is = huc.getInputStream();

        InputStreamReader pr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(pr);
        System.out.println(br.readLine());
        huc.disconnect();
    }

    public static void main(String[] args) throws IOException {
        doPost("http://localhost:9595");
    }
}
