
public class Main {

    public static void main(String[] args) {

        int[] e = { 100, 200, 400, 800, 1600, 3200 };
        int times = 1000; // Öka antalet iterationer = bättre noggrannhet
        for (int i = 0; i < e.length; i++) {
            long min = Long.MAX_VALUE;
            for (int j = 0; j < times; j++) {
                long duration = bench(100, e[i], 1000); // Kör append 100 gånger per iteration
                if (duration < min)
                    min = duration;
            }
            System.out.println("Antal element: " + e[i] + " tog " + min + " ns");
        }
    }

    public static long bench(int sizeA, int sizeB, int loops) {
        long totalDuration = 0;

        for (int i = 0; i < loops; i++) {
            LinkedList listA = new LinkedList(sizeA);
            LinkedList listB = new LinkedList(sizeB);

            long n1 = System.nanoTime();
            listA.append(listB);
            long n2 = System.nanoTime();

            totalDuration += (n2 - n1);
        }

        return totalDuration / loops;
    }
}
