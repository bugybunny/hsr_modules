/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Roboter {

    public static void main(String[] args) {
        Object[] obj = new Object[10];
        String str = new String("Hallo");
        obj[0] = str;
        System.out.println(obj[0]);
        str = "World";
        System.out.println(obj[0]);

        Test test = new Test();
        obj[1] = test;
        test.iTest = 20;
        Test neuerTest = (Test) obj[1];
        System.out.println(neuerTest.iTest);

        test.strTest = "Alles Klar!";
        System.out.println(neuerTest.strTest);

    }
}

class Test {
    int    iTest   = 10;
    String strTest = "Test";
}