import java.util.Random;

public class UnsortedSearch {

    public static void main(String[] args) {

        int elements[] = { 50000, 100000, 200000, 400000, 800000, 1000000 };
        int k = 10000;

        // warmup JIT
        bench(10000, 10000);

        for (int j = 0; j < elements.length; j++) {
            long time = bench(elements[j], k);
            System.out.println("Time for n = " + elements[j] + " is: " + time + " ns");
        }

    }

    // Metod för att söka i en osorterad array
    public static boolean unsorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    /* Här sker själva körningen av våran algoritm/funktion vi mäter */
    public static int run(int[] array, int[] keys) {
        int sum = 0;
        for (int i = 0; i < keys.length; i++) {
            unsorted_search(array, keys[i]);
            sum++; // Räknar hur många ggr vi kör unsorted
        }
        return sum;
    }

    // Metod för att mäta prestanda
    /*
     * n är antalet element i arrayen.
     * loop är antalet keys vi letar efter
     */
    public static long bench(int n, int loop) {
        Random rnd = new Random();

        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = rnd.nextInt(n);

        int[] keys = new int[loop];
        for (int i = 0; i < loop; i++)
            keys[i] = rnd.nextInt(n);

        long t0 = System.nanoTime();
        run(array, keys);
        long t1 = System.nanoTime();
        return ((t1 - t0) / loop);
    }
}

/* Main metod i för egen implementerad bench metod. */

// public static void main(String[] args) {

// // Mätning av vår sökalgoritms prestanda för en osorterad array av 100, 200,
// // 400, 800, 1600, 3200

// int[] NumberOfElement = { 10000, 20000, 40000, 80000, 160000 };
// Random rnd = new Random();
// int iterations = (int) Math.pow(10, 6);

// for (int i = 0; i < NumberOfElement.length; i++) {
// int[] array = new int[NumberOfElement[i]];
// for (int j = 0; j < NumberOfElement[i]; j++) {
// array[j] = rnd.nextInt(NumberOfElement[i]);
// }

// long totalDuration = 0;
// long minDuration = Long.MAX_VALUE;

// for (int k = 0; k < iterations; k++) {
// int key = rnd.nextInt(NumberOfElement[i]);
// long startTime = System.nanoTime();
// unsorted_search(array, key);
// long endTime = System.nanoTime();
// long duration = (endTime - startTime);
// totalDuration += duration;
// if (duration < minDuration) {
// minDuration = duration;
// }
// }

// long averageDuration = totalDuration / iterations;
// System.out.println("Average duration for " + NumberOfElement[i] + " elements:
// " + averageDuration + " ns");
// // System.out.println("Min duration for " + NumberOfElement[i] + " elements:
// " +
// // minDuration + " ns");
// }
// }