import java.util.Arrays;
import java.util.Random;

public class SortingTester {
    public static final int ARRAY_SIZE = 1 << 20;
    public static final int WARMUP_ITERATIONS = 10;
    public static final int TEST_ITERATIONS = 100;

    private static Random rand = new Random();

    public static void randomFloatArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextFloat();
        }
    }



    public static void main(String[] args) {
        float[][] warmup = new float[WARMUP_ITERATIONS][ARRAY_SIZE];
        float[][] test = new float[TEST_ITERATIONS][ARRAY_SIZE];

        for (int i = 0; i < warmup.length; i++) {
            randomFloatArray(warmup[i]);
        }

        for (int i = 0; i < test.length; i++) {
            randomFloatArray(test[i]);
        }

        // test correctness

        System.out.println("Team: " + Sorting.TEAM_NAME);

        float[] copy1 = Arrays.copyOf(warmup[0], warmup[0].length);
        float[] copy2 = Arrays.copyOf(warmup[0], warmup[0].length);

        Sorting.baselineSort(copy1);
        Sorting.parallelSort(copy2);

        for (int i = 0; i < copy1.length; i++) {
            if (copy1[i] != copy2[i]) {
                System.out.println("correctness test failed\n" +
                        "i = " + i + "\n" +
                        "copy1[i] = " + copy1[i] + "\n" +
                        "copy2[i] = " + copy2[i]);
                return;
            }
        }

        // run warmup tests

        for (float[] arr : warmup) {
            Sorting.parallelSort(arr);
        }

        // run timing tests
        long start = System.nanoTime();

        for (float[] arr : test) {
            Sorting.parallelSort(arr);
        }

        long elapsedMS = (System.nanoTime() - start) / 1_000_000;

        System.out.println("correctness test passed\n" +
                "elapsed time: " + elapsedMS + "ms");

    }
}
