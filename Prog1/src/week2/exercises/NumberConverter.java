/**
 *
 */
package week2.exercises;

import java.util.HashMap;

/**
 * TODO COMMENT ME!
 * 
 * @author Marco
 */
public class NumberConverter {

    private static final HashMap<String, Byte> HEX_VALUES = new HashMap<>(16);

    static {
        for (byte b = 0; b < 10; b++) {
            HEX_VALUES.put(Byte.toString(b), Byte.valueOf(b));
        }
        for (byte b = (byte) 'A'; b <= (byte) 'F'; b++) {
            HEX_VALUES.put(Character.toString((char) b),
                    Byte.valueOf((byte) (b - 55)));
        }
    }

    /**
     * TODO COMMENT ME!
     * 
     * @param args
     */
    public static void main(String[] args) {

        hexToDecimal("CAFEBABE");
    }

    public static final String decimalToBinary(int aDecimalNumber) {
        int decimal = aDecimalNumber;
        String binary = "";

        while (decimal != 0) {
            if (decimal % 2 == 0) {
                binary = "0" + binary;
            }
            else {
                binary = "1" + binary;
            }
            decimal /= 2;
        }

        System.out.println(binary);

        return binary;
    }

    public static final long hexToDecimal(String aHexValue) {
        long decimal = 0;
        String hexValue = aHexValue.toUpperCase();

        short currentPosition = 0;
        for (int i = hexValue.length() - 1; i >= 0; i--) {
            int value = HEX_VALUES.get(String.valueOf(hexValue.charAt(i)))
                    .intValue();
            decimal += value * Math.pow(16, currentPosition);
            currentPosition++;
        }
        System.out.println(decimal);

        return decimal;
    }
}
