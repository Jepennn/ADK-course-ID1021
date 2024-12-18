import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
    Area[] postnr;
    int max = 10000;

    // Intern klass som innehåller data om vårat postnummer
    public class Area {
        String zipCode;
        String name;
        Integer popu;

        public Area(String zip, String name, Integer population) {
            this.zipCode = zip;
            this.name = name;
            this.popu = population;
        }
    }

    public Zip(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                postnr[i++] = new Area(row[0], row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    // Linjär sökningsmetoden
    public boolean lockUp(String zip) {

        for (int i = 0; i < postnr.length; i++) {
            if (postnr[i] != null && postnr[i].zipCode.equals(zip)) {
                return true;
            }
        }
        return false;
    }

    // Binär sökning i våran array av postnummer, där zipkoden är en sträng
    public boolean binarySearch(String zip) {

        int first = 0;
        int last = 9675 - 1; // Totala antalet postnummer - 1, dä index startar på noll,

        // Gör om postnummret vi söker till en Int
        int zipNum = Integer.parseInt(zip.replace(" ", ""));

        while (first <= last) {
            int mid = first + ((last - first) / 2);

            Integer zCode = Integer.parseInt(postnr[mid].zipCode.replace(" ", ""));

            if (postnr[mid] != null && postnr[mid].zipCode.equals(zip)) {
                return true;

            } else if (postnr[mid] != null && zCode < zipNum) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
            ;
        }
        return false;
    }

    // En liten enkel benchmark för att jämföra linjär och binär sökning
    public void benchmark(String zip) {
        long startTime = System.nanoTime();
        lockUp(zip);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Linjär sökning tog: " + duration + " nanosekunder");

        long startTime2 = System.nanoTime();
        binarySearch(zip);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);
        System.out.println("Binär sökning tog: " + duration2 + " nanosekunder");
    }

    public static void main(String[] args) {

        Zip z = new Zip("postnummer.csv");

        String nr = "169 68";

        System.out.println(z.lockUp(nr));
        System.out.println(z.binarySearch(nr));
        z.benchmark(nr);

    }
}