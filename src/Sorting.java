import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Notes:
 * Baseline: 4544 ms
 * QuickSort: 9559 ms
 * MergeSort: 10037 ms
 */

public class Sorting {
    // Named after Grace Hopper, pioneer of Computing
    public static final String TEAM_NAME = "hopper";

    /**
     * Sorts an array of doubles in increasing order. This method is a
     * single-threaded baseline implementation.
     * @param data the array of doubles to be sorted
     */
    public static void baselineSort (float[] data) {
        Arrays.sort(data, 0, data.length);
    }

    /**
     * Sorts an array of doubles in increasing order. This method is a
     * multi-threaded optimized sorting algorithm. For large arrays (e.g., arrays of size at least 1 million) it should be significantly faster than baselineSort.
     * @param data the array of doubles to be sorted
     */
    public static void parallelSort (float[] data) {
        quickSort(data, 0, data.length - 1, new Random());
        /// mergeSort(data, 0, data.length);
        // baselineSort(data);
    }

    private static void quickSort(float[] a, int i, int j, Random r) {
        if (j <= i) {
            return;
        }
        int p = i + r.nextInt(j-i);
        int m = split(a, i, j, p);
        quickSort(a, i, m-1, r);
        quickSort(a, m+1, j, r);
    }


    // Split the array between indices i and j according to the pivot
    // index p. After this operation, the index m is returned such
    // that all values at indices i..m-1 are <= pivot, a[m] = pivot,
    // and all values at indices m+1...j are > pivot
    public static int split(float[] a, int i, int j, int pIndex) {
        float pivot = a[pIndex];
        // move pivot to right-most index
        swap(a, pIndex, j);
        // the largest index of values <= pivot
        int small = i - 1;
        // values between small and cur are > pivot
        for (int cur = i; cur <= j; cur++) {
            if (a[cur] <= pivot) {
                small++;
                swap(a,small,cur);
            }
        }
        // return the index of the pivot
        return small;
    }

    // swap two elements in an array
    private static void swap(float[] a, int i, int j) {
        float x = a[j];
        a[j] = a[i];
        a[i] = x;
    }

    /**
     * Determines if an array of doubles is sorted in increasing order.
     * @param data the array to check for sorted-ness
     * @return `true` if the array is sorted, and `false` otherwise
     */
    public static boolean isSorted (float[] data) {
        double prev = data[0];
        for (int i = 1; i < data.length; ++i) {
            if (data[i] < prev) {
                return false;
            }
            prev = data[i];
        }
        return true;
    }
}
