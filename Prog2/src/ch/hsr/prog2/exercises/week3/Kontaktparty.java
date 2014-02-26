package ch.hsr.prog2.exercises.week3;

import java.math.BigInteger;

public class Kontaktparty {
    static BigInteger bi(int x) {
        return BigInteger.valueOf(x);
    }

    static BigInteger arccot(BigInteger x) {
        BigInteger exp = bi(10).pow(8530).divide(x), sum = exp, n = bi(3), sgn = bi(-1), trm;
        for (;; sgn = sgn.negate(), n = n.add(bi(2))) {
            exp = exp.divide(x.multiply(x));
            trm = exp.divide(n);
            if (trm.equals(bi(0))) {
                return sum;
            }
            sum = sum.add(trm.multiply(sgn));
        }
    }

    public static void main(String[] args) {
        BigInteger pi = bi(4).multiply(arccot(bi(5))).subtract(arccot(bi(239)))
                .multiply(bi(4));
        StringBuilder sb = new StringBuilder();
        for (; !pi.equals(bi(0)); pi = pi.shiftRight(5)) {
            sb.append((char) (pi.and(bi(31)).intValue() % 26 + 65));
        }
        System.out
                .println("9. MÄRZ, 11 UHR, ETH KONTAKTPARTY. RASPBERRY PI HOLEN!");
        System.out.println("DAS CODEWORT IST "
                + sb.toString().substring(582, 588));
    }
}