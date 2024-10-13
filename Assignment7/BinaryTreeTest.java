
import java.util.Random;

public class BinaryTreeTest {
    // public static void main(String[] args) {
    // BinaryTree tree = new BinaryTree();

    // // Testa att lägga till värden i trädet
    // tree.add(10);
    // tree.add(5);
    // tree.add(15);
    // tree.add(1);
    // tree.add(70);
    // tree.add(2);

    // // tree.printTree();
    // tree.stackPrintTree();
    // }

    public static void main(String[] args) {

        // create an array of integers och tests binary_search
        int elements[] = { 50000, 100000, 200000, 400000, 800000, 1000000, 64000000 };
        int k = 10000;

        // warmup JIT
        bench(10000, 10000);

        for (int j = 0; j < elements.length; j++) {
            long time = bench(elements[j], k);
            System.out.println("Time for n = " + elements[j] + " is: " + time + " ns");
        }
    }

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;

        while (first <= last) {
            int mid = first + ((last - first) / 2);

            if (array[mid] == key) {
                return true;
            } else if (array[mid] < key) {
                first = mid + 1;
            } else {
                last = mid - 1;
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
            binary_search(array, keys[i]);
            sum++;
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
    }
}
