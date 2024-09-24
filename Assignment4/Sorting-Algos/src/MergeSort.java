import java.util.Random;

// Description: Merge sort is a divide and conquer algorithm that was invented by John von Neumann in 1945.
public class MergeSort {

    public static void main(String[] args) {

        int elements[] = { 100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600 };

        // Warm up JIT
        for (int i = 0; i < 1000; i++) {
            bench(randomArray(1000));
        }

        for (int i = 0; i < elements.length; i++) {

            long minTime = Long.MAX_VALUE;
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

    public static void mergeSort(int[] array) {
        int arrayLength = array.length;

        // Base case: If the array has less than 2 elements, do nothing
        if (arrayLength < 2) {
            return;
        }

        int mid = arrayLength / 2;

        int[] leftArray = new int[mid];
        int[] rightArray = new int[arrayLength - mid];

        // Copy the left half of the array into leftArray
        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[i];
        }
        // Copy the right half of the array into rightArray
        for (int i = mid; i < arrayLength; i++) {
            rightArray[i - mid] = array[i];
        }

        // Recursively sort the left and right arrays
        mergeSort(leftArray);
        mergeSort(rightArray);

        // Merge the sorted left and right arrays
        merge(leftArray, rightArray, array);
    }

    // Merge function that helps to put the smaller functions toughter
    private static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;

        int l = 0;
        int r = 0;
        int k = 0;

        while (l < leftLength && r < rightLength) {
            if (leftArray[l] <= rightArray[r]) {
                array[k] = leftArray[l];
                l++;
            } else {
                array[k] = rightArray[r];
                r++;
            }
            k++;
        }

        while (l < leftLength) {
            array[k] = leftArray[l];
            l++;
            k++;
        }
        while (r < rightLength) {
            array[k] = rightArray[r];
            r++;
            k++;
        }
    }

    /* Hear starts our help benchmarking functions */
    public static int[] randomArray(int n) {
        Random rnd = new Random();

        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = rnd.nextInt(n);
        return array;
    }

    /* The function that benchmarks our Merge Sort Algorithm */
    public static long bench(int[] arr) {
        long startTime = System.nanoTime();
        mergeSort(arr);
        long endTime = System.nanoTime();
        return endTime - startTime;
    };

};
