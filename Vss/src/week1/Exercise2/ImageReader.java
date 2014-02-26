package week1.Exercise2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageReader {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(
                "http:///files.newsnetz.ch/story/1/6/5/16533692/71/teaserbreit.jpg");

        File imageFile = new File("C:/Users/Marco/HSR/newImage.jpeg");
        imageFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(imageFile);

        int inputBytes;
        while ((inputBytes = fis.read()) != -1) {
            System.out.println(inputBytes);
            fos.write(inputBytes);
        }
        fis.close();
        fos.close();
    }
}
