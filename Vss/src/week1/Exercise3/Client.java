package week1.Exercise3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(
                "http://localhost:9595").openConnection();
        conn.setDoOutput(true);

        OutputStream output = conn.getOutputStream();
        output.write("one=hello_world\n".getBytes());
        output.close();

        BufferedReader response = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        System.out.println(response.readLine());
        conn.disconnect();
    }
}
