package week1.Exercise2;

import java.net.MalformedURLException;
import java.net.URL;

public class URLizer {

    public static void main(String[] args) throws MalformedURLException {
        if (args.length != 0) {
            for (String urlArgument : args) {
                URL url = new URL(urlArgument);
                System.out.println("URL: " + url);
                System.out.println("protocol: " + url.getProtocol());
                System.out.println("port: " + url.getPort());
                System.out.println("path: " + url.getPath());
                System.out.println("file: " + url.getFile());
                System.out.println("anchor: " + url.getRef() + "\n\n");

            }
        }
    }
}
