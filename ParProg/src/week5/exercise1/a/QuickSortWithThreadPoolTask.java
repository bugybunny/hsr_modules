package week5.exercise1.a;

import static week5.exercise1.QuickSortSample.NOF_ELEMENTS;
import static week5.exercise1.QuickSortSample.checkSorted;
import static week5.exercise1.QuickSortSample.createRandomArray;
import static week5.exercise1.QuickSortSample.quickSort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class QuickSortWithThreadPoolTask extends RecursiveTask<Void> {
    private int[] array;
    private int   leftBorder;
    private int   rightBorder;

    public QuickSortWithThreadPoolTask(int[] anArray, int aLeftBorder,
            int aRightBorder) {
        array = anArray;
        leftBorder = aLeftBorder;
        rightBorder = aRightBorder;
    }

    @Override
    protected Void compute() {
        int n = rightBorder - leftBorder;
        if (n > 100000) {
            int middle = leftBorder + (n / 2);
            QuickSortWithThreadPoolTask leftPart = new QuickSortWithThreadPoolTask(
                    array, leftBorder, middle);
            QuickSortWithThreadPoolTask rightPart = new QuickSortWithThreadPoolTask(
                    array, middle + 1, rightBorder);
            invokeAll(leftPart, rightPart);
        }
        quickSort(array, leftBorder, rightBorder);

        return null;
    }

    public static void main(String[] args) {
        int[] numberArray = createRandomArray(NOF_ELEMENTS);
        long startTime = System.currentTimeMillis();
        ForkJoinPool threadPool = new ForkJoinPool();
        threadPool.invoke(new QuickSortWithThreadPoolTask(numberArray, 0,
                numberArray.length - 1));
        long stopTime = System.currentTimeMillis();
        System.out.println("Total time: " + (stopTime - startTime) + " ms");
        checkSorted(numberArray);
    }
}
