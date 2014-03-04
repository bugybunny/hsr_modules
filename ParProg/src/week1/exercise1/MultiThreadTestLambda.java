package week1.exercise1;

public class MultiThreadTestLambda {
    private static void startMyThread(String label, int nofIt) {
    new Thread(() -> {
      for (int i = 0; i < nofIt; i++) {
        System.out.println(i + " " + label);
      }
    }).start();
  }

    public static void main(String[] args) {
        startMyThread("A", 10);
        startMyThread("B", 10);
        System.out.println("main finished");
    }
}
