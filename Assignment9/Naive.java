public class Naive {

    public static void main(String[] args) {
        Map map = new Map("trains.csv");

        String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);

        long t0 = System.nanoTime();
        Integer dist = shortest(map.lockUp(from), map.lockUp(to), max);
        long t1 = System.nanoTime();

        long time = (t1 - t0) / 1_000_000;

        /*
         * Skriver ut snabbaste vägen(tidsmässigt, "time") och hur lång tid det tog att
         * mäta ut den ("dist")
         */
        System.out.println("Shortest: " + dist + " min (" + time + " ms)");

    }

    private static Integer shortest(City from, City to, Integer max) {

        // Hanterar fallet då det tar för lång tid att ta sig till destinationen
        if (max < 0)
            return null;

        // Basfallet då vi är framme vid destinationen
        if (from == to)
            return 0;

        Integer shrt = null;

        for (int i = 0; i < from.con.size(); i++) {
            Connections neighbour = from.con.get(i);

            // Rekursiva delen där vi anropar shortest igen
            Integer dTime = shortest(neighbour.city, to, max - neighbour.time);
            if (dTime != null) {
                dTime += neighbour.time;
                if (shrt == null || dTime < shrt) {
                    shrt = dTime;
                }

            }
        }
        return shrt;
    }

}
