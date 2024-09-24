import java.util.Random;

public class QuickSort {

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

    private static void quicksort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private static void quicksort(int[] array, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex) {
            return;
        }

        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quicksort(array, lowIndex, leftPointer - 1);
        quicksort(array, leftPointer + 1, highIndex);

    }

    private static int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer < rightPointer) {

            // Walk from the left until we find a number greater than the pivot, or hit the
            // right pointer.
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }

            // Walk from the right until we find a number less than the pivot, or hit the
            // left pointer.
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(array, leftPointer, rightPointer);
        }

        if (array[leftPointer] > array[highIndex]) {
            swap(array, leftPointer, highIndex);
        } else {
            leftPointer = highIndex;
        }

        return leftPointer;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /* HELPER FUNCTIONS FOR BENCH MARKING STARTS HERE */
    public static int[] randomArray(int n) {
        Random rnd = new Random();

        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = rnd.nextInt(n);
        return array;
    };

    /* The function that benchmarks our Merge Sort Algorithm */
    public static long bench(int[] arr) {
        long startTime = System.nanoTime();
        quicksort(arr);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}
