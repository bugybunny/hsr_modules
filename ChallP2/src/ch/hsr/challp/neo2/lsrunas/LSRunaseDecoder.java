package ch.hsr.challp.neo2.lsrunas;

import java.util.Base64;

//@formatter:off
/**
 * Class to decode a cipher encoded with {@link http://www.moernaut.com/?item=lsrunase}.
 * 
 * Requires java 1.8 to run (for java.util.Base64 class).
 * 
 * Without any error handling.
 * 
 * @author msyfrig
 */
//@formatter:on
public class LSRunaseDecoder {
    public static int[] base64decode(String encodedText) {
        return convertSignedByteArrayToUnsigned(Base64.getDecoder().decode(
                encodedText));
    }

    /** For testing only, not needed to decrypt. */
    public static int[] base64encode(String decodedText) {
        return convertSignedByteArrayToUnsigned(Base64.getEncoder().encode(
                decodedText.getBytes()));
    }

    // thanks to java having no unsigned data types i have to use the most ugly
    // codepiece ever. without this i get a lot of 0xff because of the java two
    // complement
    private static int[] convertSignedByteArrayToUnsigned(byte[] signed) {
        int[] unsigned = new int[signed.length];
        for (int i = 0; i < signed.length; i++) {
            unsigned[i] = signed[i] & 0xff;
        }
        return unsigned;
    }

    private static void printHex(int[] decoded) {
        for (int i : decoded) {
            System.out.print(Integer.toHexString(i).toUpperCase() + " ");
        }
        System.out.println();
    }

    private static int[] getTextAsAsciiArray(String s) {
        int[] ascii = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ascii[i] = s.charAt(i);
        }
        return ascii;
    }

    private static int[] getKey(int[] plaintextAsArray, int[] base64decoded) {
        int[] key = new int[plaintextAsArray.length];

        for (int i = 0; i < plaintextAsArray.length; i++) {
            key[i] = plaintextAsArray[i] ^ base64decoded[i];
        }

        return key;
    }

    private static String fillString(int n, char c) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Shows my way of developing the solution, the same as in your
     * presentation.
     */
    private static void doSomeTests() {
        // do some testing
        // aaaaaaaaaaaaaaaa (16x a)
        // ⇒ ec 5e 71 8e f7 f ff ae d8 89 a0 b9 4e d0 48 82
        printHex(base64decode("7F5xjvcP/67YiaC5TtBIgg"));
        // baaaaaaaaaaaaaaa (b + 15x a)
        // ⇒ ef 5e 71 8e f7 f ff ae d8 89 a0 b9 4e d0 48 82
        printHex(base64decode("715xjvcP/67YiaC5TtBIgg=="));
        // baaaaaaaaaaaaaaa (bb + 14x a)
        // ⇒ ef 5d 71 8e f7 f ff ae d8 89 a0 b9 4e d0 48 82
        printHex(base64decode("711xjvcP/67YiaC5TtBIgg=="));
        // abaaaaaaaaaaaaaa (ab + 14x a)
        // ⇒ ec 5d 71 8e f7 f ff ae d8 89 a0 b9 4e d0 48 82
        printHex(base64decode("7F1xjvcP/67YiaC5TtBIgg=="));

        // now get the key from 16 times a, (a=0x61 ascii btw) XOR the base64
        // decoded value from the password generated from LSrunase
        int[] asciiRepresentation = getTextAsAsciiArray("aaaaaaaaaaaaaaaa");
        int[] decoded = base64decode("7F5xjvcP/67YiaC5TtBIgg");
        int[] key = getKey(asciiRepresentation, decoded);
        System.out.print("key: ");
        for (int k : key) {
            System.out.print(Integer.toHexString(k) + " ");
        }
        System.out.println();

        // ⇒ aaaaaaaaaaaaaaaa, works
        for (int i = 0; i < key.length; i++) {
            System.out.print((char) (key[i] ^ decoded[i]));
        }
    }

    public static String getPlaintextPassword(String encrypted) {
        int[] decoded = base64decode(encrypted);
        int length = decoded.length;

        int[] key = getKey(
                getTextAsAsciiArray(fillString(1024, 'a')),
                base64decode("7F5xjvcP/67YiaC5TtBIgmX0bZWYjzfxEuz8fwT4f4V8fnWD5S5B4gkF9YRExcoeoWi2FhVdBN0N5rnep0jvPlJvOk7v3Ga/olMs+aaNUzoI+4ZqEnJCwXu1ECA9S3jeFJKuNHOXtZuCc9d5M73YX5nhygX14OuatJwVq5S5maH7Q0d1zR0PRN0nfJ1i4/gENvLZzctwm7j+X37Kc3qt9fspPeap/N4nHJiFa4R9y07RneUbpNPG2LtQr1gZJrrfPq7pCAijYL2qmQhQSkRM7vFlMuUll6yo+j+AbbvH5Puvlm3W/NprSCvboNx7ryfxuUKfWMdtQtkMnSbMhIRmc7iM3sc/z/twqKmD7ZGKO8sdXp7/IXUKFG7LQn1Vz5c4ltB4Xlp+bWdbOeU3A6AvV24E/2gHyUM3XFL3Xt8mP+/fxqxN2mAxjEx8FGXpQVLAPLxDU3FrC8p8V1URKCBdR7tUkGq+NNuhElj0Y17u3Ei5WeSJsSbGiXmnWgNYQ5rf+ecA4FLx5klA3iFJxhMl8UCBEDk1YQ8cTrpwt8AFN/lQzVVmdUQDU/YU7892NnLo3ext32gPj6miAYzNSmFma/ARjZaP4u/nI+SGVOFQWSH0l8pJ1dI0dn/G4gYUy068zbdDSegAR3NcFzY+ywXEUB5J243c/zzC4LT1b+aaTvyw8RkkdYNF+FqRa40cw/aoTqxrZH8vhd7ah40tmftecPfvnMtVsJqr6+x4QOy0K85zl1QXvAWZP2glagJxWXf24CWMOPwCWs3OEwPXResgyL21ocvcZMKY7Kzjof61pHUzXghzOv52YIoYe8DCHunV19T/BIXSdOKpTDVFmSEL6fhT+cDv0ckAKOTUxlFnzXyfUbQYkdfV8rc2t3uGe1sbN/wuBGN266I/WY0GL6rwUQtKp77JGRumYXkhhUxygdvPg1rPNJtg0zLTDwP8JLc2xlnTEcIzs/WsSOKvG3Y+/e+KGtP8qBbP73LZ442yucVYIFTgytKJYXLNYUHROYOjORGIVyn4Xte4ne6BEcwnTh4f+O7aoUNM5KYx/mY45dQWeOaeiZaO0sGK4cWNFoLWTwk+Naw9LZ6hUqfgTPLy4R4jTgCxjl8xrL/6P0VxwQppzOFtgeXAttZZuF/zbBmuPvKcSfVj1MB9kBzvxj5EpDHqU7AZCPnJtoEoL3YUHAzCNN4PcnWy0bsTSz2x+rlAj63oJ22y3BmekoM5kMky2fzRSoL+d1iuLFsapMRSgupEGFqJWJ5hKo0e0vUWgYyzJYGmJHQxJ6RJr2RNDgaxMdy4Ia5Jup4o1bCKWBYN2Cy3u2yqa5tz2x8v0pFSnmA1ovmLaHg9YFgvrznxW+XhlA=="));
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append((char) (key[i] ^ decoded[i]));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        System.out.println("cipher\t:\tplaintext password");
        for (String cipher : args) {
            System.out.println(cipher + "\t:\t" + getPlaintextPassword(cipher));
        }

        for (int i : base64decode("sRoK5vaaqJeIzKRMffh/GmkLbAY6CuydKyIglSRl+WtCwhg9OXtHVk8NURXhbhVI1DF6lMxqg2Y0lAHJSkVwoDjclOADJSw/WcyqOICiJt4AgmEmy8t1AKHGE3YNCcGSChHUiwDEDLgmxHYqT5dHfyTkTLYYqJpdv4T5TSm+JBZD+fT6DPMkXRNbaMjJASxJJopgeOn5KDklAlMhR3AlsndOvN35eFmn1B9HqBg54dOsgQBtaH1r213GYByrjqrjEHVGbFZWAST30DL3H/s5Nw7AQ9rGHZSDZza8h6trDogJMzC7DCRTssNJ/Ydt/pPE3CJ4LJSjZ85y8/oKmzHoF7JgY8ht6uFve8bXo/JpWm58PerQisYQQhncLlGPMW2w8vtr8eyI6myE8ZPWh9chMslw/YHQY+axEf8PRxh4pDE=")) {
            System.out.print((char) i);
        }

        // doSomeTests();
        // System.out.println(getPlaintextPassword("xX9zpLgirY7Lpg=="));
        // System.out.println(getPlaintextPassword("5FFktMtO/7zagaiKSsFbhnfwYoCYmj//Ha2gPgL8arB4Z2Cj9w5T4AEN1ZdXxdJX4mi2FhVdBN0N5rnep0jvPlIschQ="));
    }
}
