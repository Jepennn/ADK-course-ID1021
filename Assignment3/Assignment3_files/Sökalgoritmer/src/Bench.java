import java.util.Random;

/*Detta är filen hjälpfunktionerna från benchmarking från assignment 1 */

public class Bench {

    // Metoder för att köra mätningarna i main
    public static int run(int[] array, int[] indx) {
        int sum = 0;
        for (int i = 0; i < indx.length; i++) {
            sum = sum + array[indx[i]];
        }
        return sum;
    }

    /*
     * Loop kan man tänka som nycklar och n som storleken på arrayen vi ska loopa
     * igenom
     */
    public static long bench(int n, int loop) {

        Random rnd = new Random();

        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = rnd.nextInt(n * 4);

        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++)
            indx[i] = rnd.nextInt(n * 4);

        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

}
