package ch.hsr.ch.bsys2.aufgabe8;

import java.io.IOException;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Cam {
    public static void main(String[] args) throws IOException {
        int[] cam = { 0xf83c, 0x0a93, 0xda1f, 0x093a, 0x8f1d, 0x4444, 0x9269,
                0xc645, 0xaffe, 0x0000, 0x7e3b, 0x038a };
        boolean[] valid = { false, true, true, false, false, true, true, true,
                false, true, false, true };
        int muster, maske;

        // System.out.println("Bitte Musterwert eingeben: ");
        // muster = System.in.read();
        // System.out.println("\nBitte Maskenwert eingeben: ");
        // maske = System.in.read();
        // System.out.println();

        muster = 0x7e3b;
        maske = 0x038a;

        for (int i = 0; i < cam.length; i++) {
            int bitmuster = muster & maske;
            if (valid[i]) {
                System.out.println(Integer.toBinaryString(bitmuster));
                System.out.println(Integer.toBinaryString(maske & cam[i]));
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

                boolean matches = bitmuster == (maske & cam[i]);

                System.out.println("0x" + Integer.toHexString(cam[i])
                        + " matches: " + matches);
            }
        }

    }
}
