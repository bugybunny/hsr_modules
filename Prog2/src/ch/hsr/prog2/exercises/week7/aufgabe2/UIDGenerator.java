/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr  4 16:08:41 CEST 2013
 */

package ch.hsr.prog2.exercises.week7.aufgabe2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UIDGenerator {

    public static final String  SAVE_FILE  = "src/ch/hsr/prog2/exercises/week7/aufgabe2/uid.ser";

    public static final int     UID_LENGTH = 8;

    private Map<String, String> map        = new HashMap<>();

    public UIDGenerator(Map<String, String> m) {
        map = m;
    }

    private String replaceMutatedVowel(String s) {
        s = s.replaceAll("ä", "ae");
        s = s.replaceAll("ö", "oe");
        s = s.replaceAll("ü", "ue");
        s = s.replaceAll("Ä", "Ae");
        s = s.replaceAll("Ö", "Oe");
        s = s.replaceAll("Ü", "Ue");

        return s;
    }

    public String generateUID(String aFirstName, String aSurName) {
        String firstName = replaceMutatedVowel(aFirstName).toLowerCase();
        String surName = replaceMutatedVowel(aSurName).toLowerCase();

        StringBuilder generatedUID = new StringBuilder(8);
        // add first char of first name
        generatedUID.append(firstName.charAt(0));

        // now as many chars from the surname as possible and trim it to 8 chars
        // if needed
        generatedUID.append(surName);
        trimToSize(generatedUID, (short) 8);

        // check if uid is really unique and if not add numbers with length 1-7
        // to it after the first char
        AtomicInteger i = new AtomicInteger();

        while (map.containsKey(generatedUID.toString())) {
            i.getAndIncrement();
            String number = i.toString();
            generatedUID.insert(1, number);
            trimToSize(generatedUID, (short) 8);
        }
        if (i.get() != 0) {
            generatedUID.replace(i.toString().length() + 1, 8, surName);
        }

        return generatedUID.toString();
    }

    private void trimToSize(StringBuilder aString, short aLength) {
        if (aString.length() > 8) {
            aString.delete(8, -1);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java UIDGenerator first_name last_name");
            System.exit(1);
        }
        File f = new File(SAVE_FILE);
        System.out.println(f.getAbsolutePath());
        Map<String, String> uids;
        if (f.exists()) {
            FileInputStream fis = new FileInputStream(SAVE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            uids = (Map<String, String>) ois.readObject();
            ois.close();
        } else {
            uids = new HashMap<String, String>();
        }
        UIDGenerator gen = new UIDGenerator(uids);
        String uid = gen.generateUID(args[0], args[1]);
        uids.put(uid, args[0] + " " + args[1]);
        // save map
        FileOutputStream fos = new FileOutputStream(SAVE_FILE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(uids);
        oos.close();
        System.out.println("Username generated: " + uid);
        System.out.println("Usernames so far " + uids.size() + ":");
        Set<String> keys = uids.keySet();
        for (String key : keys) {
            System.out.println(uids.get(key) + ": " + key);
        }
    }

}

/*
 * Session-Log:
 * 
 * $ java -classpath Uebungen uebung07.as.aufgabe02.UIDGenerator John Smith
 * Username generated: jsmith Usernames so far 10: Hans Lueginsland: hluegins
 * Heinz Ketchup: hketchup Calanda Braeu: cbraeu John Smith: jsmith Hans
 * Lueginslandvoobe: h1luegin Hannibal Hans: hhans Anna Bolika: abolika Mueller
 * Meier: m2meier Michael Meier: m1meier Marco Meier: mmeier
 * 
 * 
 * $ xxd uid.ser 0000000: aced 0005 7372 0011 6a61 7661 2e75 7469
 * ....sr..java.uti 0000010: 6c2e 4861 7368 4d61 7005 07da c1c3 1660
 * l.HashMap......` 0000020: d103 0002 4600 0a6c 6f61 6446 6163 746f
 * ....F..loadFacto 0000030: 7249 0009 7468 7265 7368 6f6c 6478 703f
 * rI..thresholdxp? 0000040: 4000 0000 0000 0c77 0800 0000 1000 0000
 * 
 * @......w........ 0000050: 0a74 0008 686c 7565 6769 6e73 7400 1048
 * .t..hlueginst..H 0000060: 616e 7320 4c75 6567 696e 736c 616e 6474 ans
 * Lueginslandt 0000070: 0008 686b 6574 6368 7570 7400 0d48 6569
 * ..hketchupt..Hei 0000080: 6e7a 204b 6574 6368 7570 7400 0663 6272 nz
 * Ketchupt..cbr 0000090: 6165 7574 000d 4361 6c61 6e64 6120 4272 aeut..Calanda
 * Br 00000a0: 6165 7574 0006 6a73 6d69 7468 7400 0a4a aeut..jsmitht..J 00000b0:
 * 6f68 6e20 536d 6974 6874 0008 6831 6c75 ohn Smitht..h1lu 00000c0: 6567 696e
 * 7400 1548 616e 7320 4c75 6567 egint..Hans Lueg 00000d0: 696e 736c 616e 6476
 * 6f6f 6265 7400 0568 inslandvoobet..h 00000e0: 6861 6e73 7400 0d48 616e 6e69
 * 6261 6c20 hanst..Hannibal 00000f0: 4861 6e73 7400 0761 626f 6c69 6b61 7400
 * Hanst..abolikat. 0000100: 0b41 6e6e 6120 426f 6c69 6b61 7400 076d .Anna
 * Bolikat..m 0000110: 326d 6569 6572 7400 0d4d 7565 6c6c 6572 2meiert..Mueller
 * 0000120: 204d 6569 6572 7400 076d 316d 6569 6572 Meiert..m1meier 0000130:
 * 7400 0d4d 6963 6861 656c 204d 6569 6572 t..Michael Meier 0000140: 7400 066d
 * 6d65 6965 7274 000b 4d61 7263 t..mmeiert..Marc 0000150: 6f20 4d65 6965 7278 o
 * Meierx
 */

