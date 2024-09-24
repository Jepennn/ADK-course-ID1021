package Assignment_1;

import java.util.Random;

public class KodSnutt4 {

    public static void main(String[] args) {

        /* Kodsnutt 6 */
        int n = 1000;
        int loop = 1000;
        int k = 10;

        long min = Long.MAX_VALUE;
        long max = 0;

        bench(n, 1000000);

        long total = 0;
        for (int i = 0; i < k; i++) {
            long t = bench(n, loop);
            if (t > max)
                max = t;
            if (t < min)
                min = t;
            total += t;
        }

        System.out.println(" avg: " + ((double) total) / loop / k);
        System.out.println(" min: " + ((double) min) / loop);
        System.out.println(" max: " + ((double) max) / loop);

        /* Kodsnutt 5 */
        // for (int i = 0; i < 10; i++) {
        // long t = bench(1000, 1000);
        // System.out.println(" access: " + t + " ns");
        // }

        /* Kodsnutt 4 */
        // int n = 1000;
        // int loop = 1000;

        // long time = bench(n, loop);
        // time = time / loop;

        // System.out.println("Time: " + time + " ns");
    }

    public static int run(int[] array, int[] indx) {
        int sum = 0;
        for (int i = 0; i < indx.length; i++) {
            sum = sum + array[indx[i]];
        }
        return sum;
    }

    public static long bench(int n, int loop) {

        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = i;

        Random rnd = new Random();

        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++)
            indx[i] = rnd.nextInt(n);

        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }
}
