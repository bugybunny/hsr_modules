package week1.Exercise1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DomainNameSystem {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
        System.out.println(InetAddress.getByName("sidv0012.hsr.ch"));
        byte[] addr = new byte[] { (byte) 152, 96, 36, 121 };
        System.out.println(InetAddress.getByAddress(addr)
                .getCanonicalHostName());
        for (InetAddress googleAddr : InetAddress.getAllByName("google.com")) {
            System.out.println(googleAddr);
        }
    }
}
