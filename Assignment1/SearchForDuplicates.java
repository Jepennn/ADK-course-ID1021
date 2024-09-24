package Assignment_1;

import java.util.Random;

public class SearchForDuplicates {

    public static void main(String[] args) {

        int[] sizes = { 100, 200, 400, 800, 1600, 3200 };

        // Loopar genom varje storlek och kör duplicates-funktionen
        for (int n : sizes) {
            long time = duplicates(n);
            System.out.println("Arraystorlek: " + n + ", Tid: " + time + " ns");
        }

    }

    private static long duplicates(int n) {

        Random rnd = new Random();
        Random rnd2 = new Random();

        int[] array_a = new int[n];
        int[] array_b = new int[n];
        for (int i = 0; i < n; i++) {
            array_a[i] = rnd.nextInt(n * 2);
            array_b[i] = rnd2.nextInt(n * 2);
        }

        int sum = 0;

        long t0 = System.nanoTime();

        for (int i = 0; i < n; i++) {
            int key = array_a[i];
            for (int j = 0; j < n; j++) {
                if (key == array_b[j]) {
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime();
        return t1 - t0;
    }

}
