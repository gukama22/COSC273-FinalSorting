import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction {
    float[] data;

    public QuickSortTask(float[] d) {
        data = d;
    }

    @Override
    protected void compute() {
        quickSort(data, 0, data.length - 1, new Random());
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


    /**
     * Split the array between indices i and j according to the pivot index p.
     * After this operation, the index m is returned such that all values at indices i...m-1 are
     * <= pivot, a[m] = pivot, and all values at indices m+1...j are > pivot.
     * @param a the input array to be split
     * @param i the starting index
     * @param j the ending index
     * @param pIndex the index of the pivot value
     */
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
