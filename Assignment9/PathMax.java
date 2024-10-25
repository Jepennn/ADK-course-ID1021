public class PathMax {

    public static void main(String[] args) {
        Map map = new Map("trains.csv");

        String from = args[0];
        String to = args[1];

        long t0 = System.nanoTime();
        PathMax p = new PathMax();
        Integer dist = p.shortest(map.lockUp(from), map.lockUp(to), null); // Startar utan maxvärde
        long t1 = System.nanoTime();

        long time = (t1 - t0) / 1_000_000;

        System.out.println("Shortest: " + dist + " min (" + time + " ms)");
    }

    City[] path;
    int sp;

    public PathMax() {
        path = new City[54];
        sp = 0;
    }

    private Integer shortest(City from, City to, Integer max) {
        // Kontrollera om staden redan är besökt i denna sökväg
        for (int i = 0; i < sp; i++) {
            if (path[i] == from) {
                return null;
            }
        }

        // Lägger till staden i path-arrayen om den inte redan finns där
        path[sp++] = from;

        // Basfall: om vi når destinationen, returnera 0
        if (from == to) {
            path[sp--] = null;
            return 0; // Avstånd från sig själv till sig själv är 0
        }

        Integer shrt = null; // Håller kortaste tiden

        for (Connections neighbour : from.con) {
            // Beräkna återstående tid baserat på maxvärdet
            Integer remainingTime = (max == null) ? null : max - neighbour.time;

            // Hoppa över denna väg om tiden överstiger maxvärdet
            if (remainingTime != null && remainingTime < 0) {
                continue;
            }

            // Rekursivt anrop och uppdatering av dynamiskt maxvärde
            Integer dTime = shortest(neighbour.city, to, remainingTime);
            if (dTime != null) {
                dTime += neighbour.time; // Lägg till tiden för denna väg

                // Uppdatera kortaste väg och dynamiskt maxvärde om en kortare väg hittas
                if (shrt == null || dTime < shrt) {
                    shrt = dTime;

                    // Uppdatera maxvärdet för kommande vägar
                    max = (max == null) ? shrt : Math.min(max, shrt);
                }
            }
        }

        // Ta bort staden från path-arrayen innan vi återgår
        path[sp--] = null;
        return shrt;
    }
}