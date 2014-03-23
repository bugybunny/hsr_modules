package week5.exercise1;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class QuickSortSample extends RecursiveTask<Void> {
    public static final int NOF_ELEMENTS = 10;
    private int[]           array;
    private int             leftBorder;
    private int             rightBorder;

    public QuickSortSample(int[] anArray, int aLeftBorder, int aRightBorder) {
        array = anArray;
        leftBorder = aLeftBorder;
        rightBorder = aRightBorder;
    }

    @Override
    protected Void compute() {
        int i = leftBorder, j = rightBorder;
        long m = array[(leftBorder + rightBorder) / 2];
        do {
            while (array[i] < m) {
                i++;
            }
            while (array[j] > m) {
                j--;
            }
            if (i <= j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
                i++;
                j--;
            }
        }
        while (i <= j);

        if (j > leftBorder) {
            QuickSortSample sample1 = new QuickSortSample(array, leftBorder, j);
            quickSort(array, leftBorder, j);
        }
        if (i < rightBorder) {
            quickSort(array, i, rightBorder);
        }
        return null;
    }

    // sorts the partition between array[left] and array[right]
    public static void quickSort(int[] array, int left, int right) {
        int i = left, j = right;
        long m = array[(left + right) / 2];
        do {
            while (array[i] < m) {
                i++;
            }
            while (array[j] > m) {
                j--;
            }
            if (i <= j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
                i++;
                j--;
            }
        }
        while (i <= j);

        if (j > left) {
            quickSort(array, left, j);
        }
        if (i < right) {
            quickSort(array, i, right);
        }
    }

    public static int[] createRandomArray(int length) {
        Random random = new Random(4711);
        int[] numberArray = new int[length];
        for (int i = 0; i < length; i++) {
            numberArray[i] = random.nextInt();
        }
        return numberArray;
    }

    public static void checkSorted(int[] numberArray) {
        for (int i = 0; i < numberArray.length - 1; i++) {
            if (numberArray[i] > numberArray[i + 1]) {
                throw new RuntimeException("Not sorted");
            }
        }
    }

    public static void main(String[] args) {
        int[] numberArray = createRandomArray(NOF_ELEMENTS);
        long startTime = System.currentTimeMillis();
        quickSort(numberArray, 0, numberArray.length - 1);
        long stopTime = System.currentTimeMillis();
        System.out.println("Total time: " + (stopTime - startTime) + " ms");
        checkSorted(numberArray);
    }
}
