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
 *
 * ForkJoin Pool 01: 13941 ms
 * 23 April (3:41 PM) Sike
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
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new QuickSortTask(data));
        // quickSort(data, 0, data.length - 1, new Random());
        /// mergeSort(data, 0, data.length);
        // baselineSort(data);
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
