public class Main {
    public static void main(String[] args) {

        int elements[] = { 100, 200, 400, 800, 1600, 3200, };

        // Warm up JIT
        for (int i = 0; i < 100; i++) {
            bench(1000);
        }

        for (int i = 0; i < elements.length; i++) {

            long minTime = Long.MAX_VALUE;
            for (int j = 0; j < 1000; j++) {
                long time = bench(elements[i]);

                if (time < minTime) {
                    minTime = time;
                }
            }
            System.out.println("Time taken for " + elements[i] + " elements: " + minTime
                    + " ns");
        }
    }

    public static long bench(int elements) {

        // Fyller upp våran kö vi ska tömma och mäta
        Queue q = new Queue();
        for (int j = 0; j < elements; j++) {
            q.enqueue(j);
        }

        int sum = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < elements; i++) {
            q.dequeue();
            sum++;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / elements;
    };

    /*
     * KOD FÖR ATT TESTA VÅRA ENQEUE METODERNA FÖR BÅDE BETTERQUEUE OCH QUEUE. BYT
     * BARA UT BETTERQUEUE MOT QUEUE
     * I BENCH METODEN
     */
    // int elements[] = { 100, 200, 400, 800, 1600, 3200, };

    // // Warm up JIT
    // for (int i = 0; i < 100; i++) {
    // bench(1000);
    // }

    // for (int i = 0; i < elements.length; i++) {

    // long minTime = Long.MAX_VALUE;
    // for (int j = 0; j < 1000; j++) {
    // long time = bench(elements[i]);

    // if (time < minTime) {
    // minTime = time;
    // }
    // }
    // System.out.println("Time taken for " + elements[i] + " elements: " + minTime
    // + " ns");
    // }

    // }

    // public static long bench(int elements) {
    // BetterQueue q1 = new BetterQueue();
    // long startTime = System.nanoTime();
    // for (int i = 0; i < elements; i++) {
    // q1.enqueue(i);
    // }
    // long endTime = System.nanoTime();
    // return (endTime - startTime) / elements;
    // };
}