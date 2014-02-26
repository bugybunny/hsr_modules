package week1.Exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader {

    public static void main(String[] args) throws IOException {
        URL htmlSite = new URL("http://www.hsr.ch");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                htmlSite.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

}
