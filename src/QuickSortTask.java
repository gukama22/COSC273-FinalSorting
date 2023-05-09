import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * @author Lynca Kaminka and Sike Ogieva
 * May 2023
 */

public class QuickSortTask extends RecursiveAction {
    float[] data;
    int i;
    int j;

    public QuickSortTask(float[] d, int start, int end) {
        data = d;
        i = start;
        j = end;
    }

    @Override
    protected void compute() {
        // for smaller arrays
        if (j - i <= 500_000) {
            Arrays.sort(data, i, j + 1);
            return;
        }

       // for larger arrays
       if (j > i) {
            // piv[0] = left pivot and piv[1] = right pivot
            int[] piv;
            // splits the array into three partitions with two pivots
            piv = split(data, i, j);

            // recursively sort each of the three partitions
            QuickSortTask left = new QuickSortTask(data, i, piv[0] - 1);
            QuickSortTask middle = new QuickSortTask(data, piv[0] + 1, piv[1] - 1);
            QuickSortTask right = new QuickSortTask(data, piv[1] + 1, j);

          //found to be faster than any combination that included .fork
           left.compute();
           middle.compute();
           right.compute();
        }
    }


    /**
     * Split the array into according to the pivot indices low and high.
     * After this operation, the indices a and b are returned
     * such that all values at indices i...a-1 are <= pivotOne,
     * all values at indices a ... b are between pivotOne and pivotTwo
     * and all values at indices b+1...j are > pivotTwo.
     * @param arr the input array to be split
     * @param low the starting index
     * @param high the ending index
     */
    public static int[] split(float[] arr, int low, int high) {
        // If the first element in the range is greater than the last element, swap them
        if (arr[low] > arr[high]) swap(arr, low, high);

        // Initialize pointers
        int a = low + 1;
        int b = high - 1;
        int c = low + 1;

        // p is the left pivot, and q is the right pivot.
        float p = arr[low];
        float q = arr[high];

        // Partition the array
        while (c <= b) {
            // If the element at index c is less than the pivot p,
            // swap it with the element at index a and increment a
            if (arr[c] < p) {
                swap(arr, c, a);
                a++;
            }

            // If the element at index c is greater than or equal to the pivot q,
            // swap it with an element at index b
            // that is less than or equal to q, then decrement b.
            // If the swapped element is less than p, swap it with
            // an element at index a and increment a
            else if (arr[c] >= q) {
                while (arr[b] > q && c < b) b--;
                swap(arr, c, b);
                b--;

                if (arr[c] < p) {
                    swap(arr, c, a);
                    a++;
                }
            }
            // Increment c to move to the next element to be compared
            c++;
        }

        // Decrement a and increment b to position them
        // at the indices of the pivot elements
        a--; b++;

        // Bring pivots to their appropriate positions.
        swap(arr, low, a);
        swap(arr, high, b);

        // Return an array containing the indices of the pivot elements
        return new int[] { a, b };
    }

    /** Swap two elements in an array
     * @param a the input float array
     * @param i an index whose value is to be swapped
     * @param j an index whose value is to be swapped
     */
    private static void swap(float[] a, int i, int j) {
        float x = a[j];
        a[j] = a[i];
        a[i] = x;
    }
}
