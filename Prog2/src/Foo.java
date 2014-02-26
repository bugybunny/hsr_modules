import java.util.Arrays;
import java.util.Comparator;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Foo {

    public static void main(String[] args) {
        Integer[] intArray = { 3, 2, 5, 1, 0, 7, 3, 5, 9, 2, 7, 9, 8, 9, 7, 6,
                9 };
        quickSort(intArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer aO1, Integer aO2) {
                return aO1.compareTo(aO2);
            }
        });
        System.out.println(Arrays.toString(intArray));
    }

    public static void quickSort(Object[] S, Comparator c) {
        if (S.length < 2) {
            return; // the array is already sorted in this case
        }
        quickSortStep(S, c, 0, S.length - 1); // recursive sort method
    }

    private static void quickSortStep(Object[] S, Comparator c, int leftBound,
            int rightBound) {
        if (leftBound >= rightBound) {
            return; // the indices have crossed
        }
        Object temp; // temp object used for swapping
        Object pivot = S[rightBound];
        System.out.println(pivot);
        int leftIndex = leftBound; // will scan rightward
        int rightIndex = rightBound - 1; // will scan leftward

        while (leftIndex <= rightIndex) { // scan right until larger than the
            System.out.println(Arrays.toString(S));
            // pivot
            while ((leftIndex <= rightIndex)
                    && (c.compare(S[leftIndex], pivot) <= 0)) {
                leftIndex++;
            }
            // scan leftward to find an element smaller than the pivot
            while ((rightIndex >= leftIndex)
                    && (c.compare(S[rightIndex], pivot) >= 0)) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) { // both elements were found
                temp = S[rightIndex];
                S[rightIndex] = S[leftIndex]; // swap these elements
                S[leftIndex] = temp;
            }
        } // the loop continues until the indices cross
        temp = S[rightBound]; // swap pivot with the element at leftIndex
        S[rightBound] = S[leftIndex];
        S[leftIndex] = temp; // the pivot is now at leftIndex, so recurse
        System.out.println(Arrays.toString(S));
        quickSortStep(S, c, leftBound, leftIndex - 1);
        quickSortStep(S, c, leftIndex + 1, rightBound);
    }
}
