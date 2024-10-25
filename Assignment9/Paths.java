public class Paths {

    public static void main(String[] args) {
        Map map = new Map("trains.csv");

        String from = args[0];
        String to = args[1];

        long t0 = System.nanoTime();
        Paths p = new Paths();
        Integer dist = p.shortest(map.lockUp(from), map.lockUp(to));
        long t1 = System.nanoTime();

        long time = (t1 - t0) / 1_000_000;

        System.out.println("Shortest: " + dist + " min (" + time + " ms)");
    }

    City[] path;
    int sp;

    public Paths() {
        path = new City[54];
        sp = 0;
    }

    private Integer shortest(City from, City to) {

        for (int i = 0; i < sp; i++) {
            if (path[i] == from) {
                return null;
            }
        }
        // Lägger till staden i path arrayen om den inte redan finns där
        path[sp++] = from;

        if (from == to) {
            path[sp--] = null;
            return 0; // Avstånd från sig själv till sig själv är 0
        }

        Integer shrt = null; // Håller kortaste tiden

        for (int j = 0; j < from.con.size(); j++) {
            Connections neighbour = from.con.get(j);

            Integer dTime = shortest(neighbour.city, to);
            if (dTime != null) {
                dTime += neighbour.time;
                if (shrt == null || dTime < shrt) {
                    shrt = dTime;
                }
            }
        }
        path[sp--] = null;
        return shrt;
    }
}
