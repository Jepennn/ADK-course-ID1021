import java.util.Random;

public class SortedSearch {

    public static void main(String[] args) {
        int elements[] = { 50000, 100000, 200000, 400000, 800000, 1000000 };
        int k = 1000;

        // warmup JIT
        bench(10000, 10000);

        for (int j = 0; j < elements.length; j++) {
            long time = bench(elements[j], k);
            System.out.println("Time for n = " + elements[j] + " is: " + time + " ns");
        }
    }

    // Metod för att söka i en sorterad array
    public static boolean sorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
            // Om key är mindre än array[index] så finns inte key i arrayen
            if (array[index] > key) {
                return false;
            }
        }
        return false;
    }

    // Metod för att skapa en sorterad array
    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;

        for (int i = 0; i < n; i++) {
            nxt += rnd.nextInt(10) + 1;
            array[i] = nxt;
        }
        return array;
    }

    /* Här sker själva körningen av våran algoritm/funktion vi mäter */
    public static int run(int[] array, int[] keys) {
        int sum = 0;
        for (int i = 0; i < keys.length; i++) {
            sorted_search(array, keys[i]);
            sum++; // Räknar hur många ggr vi kör sorted
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

        int[] array = sorted(n);
        int[] keys = new int[loop];

        for (int i = 0; i < loop; i++)
            keys[i] = rnd.nextInt(n);

        long t0 = System.nanoTime();
        run(array, keys);
        long t1 = System.nanoTime();
        return ((t1 - t0) / loop);
    };
}
