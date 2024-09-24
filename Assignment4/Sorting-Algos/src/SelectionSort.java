import java.util.Random;

public class SelectionSort {

    public static void main(String[] args) {

        int elements[] = { 100, 200, 400, 800, 1600, 3200, 6400 };

        // Warm up JIT
        for (int i = 0; i < 1000; i++) {
            bench(randomArray(1000));
        }

        for (int i = 0; i < elements.length; i++) {

            long minTime = Integer.MAX_VALUE;
            for (int j = 0; j < 1000; j++) {
                int arr[] = randomArray(elements[i]);
                long time = bench(arr);

                if (time < minTime) {
                    minTime = time;
                }
            }
            System.out.println("Time taken for " + elements[i] + " elements: " + minTime + " ns");
        }

    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int candidate = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[candidate]) {
                    candidate = j;
                }
            }
            swap(arr, i, candidate);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* Hear starts our help benchmarking functions */

    public static int[] randomArray(int n) {
        Random rnd = new Random();

        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = rnd.nextInt(n);
        return array;
    }

    /* The function that benchmarks our sorting Algotim */
    public static long bench(int[] arr) {
        long startTime = System.nanoTime();
        selectionSort(arr);
        long endTime = System.nanoTime();
        return endTime - startTime;
    };
};