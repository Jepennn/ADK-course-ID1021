
import java.io.BufferedReader;
import java.io.FileReader;

public class ZipV2 {
    Area[] postnr;
    int max = 10000;

    /* Intern klass som innehåller data om vårat postnummer */
    public class Area {
        Integer zipCode;
        String name;
        Integer popu;

        public Area(Integer zip, String name, Integer population) {
            this.zipCode = zip;
            this.name = name;
            this.popu = population;
        }
    }
    /*----------------------------------------------------------------------------------*/

    public ZipV2(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                postnr[i++] = new Area(code, row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    /*------------------------------Våra två sökmetoder-------------------------------------------- */
    // Linjär sökningsmetoden
    public boolean lockUp(Integer zip) {

        for (int i = 0; i < postnr.length; i++) {
            if (postnr[i] != null && postnr[i].zipCode.equals(zip)) {
                return true;
            }
        }
        return false;
    }

    // Binär sökning i våran array av postnummer, där zipkoden är en sträng
    public boolean binarySearch(Integer zip) {

        int first = 0;
        int last = this.max;

        while (first <= last) {
            int mid = first + ((last - first) / 2);

            if (postnr[mid] != null && postnr[mid].zipCode.equals(zip)) {
                return true;

            } else if (postnr[mid] != null && postnr[mid].zipCode < zip) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
            ;
        }
        return false;
    }
    /*---------------------------------------------------------------------------------------- */

    public void collisions(int mod) {

        int mx = 20;
        int[] data = new int[mod];
        int[] cols = new int[mx];

        // postnr[] are the zip codes
        for (int i = 0; i < max; i++) {
            Integer index = postnr[i].zipCode % mod;
            data[index]++;
        }
        for (int i = 0; i < mod; i++) {
            if (data[i] < mx) {
                cols[data[i]]++;
            }
        }
        System.out.print(mod + ": ");
        for (int i = 1; i < mx; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public void benchmark(Integer zip) {
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

        ZipV2 zip = new ZipV2("postnummer.csv");

        Integer num = 16968;

        System.out.println(zip.lockUp(num));
        System.out.println(zip.binarySearch(num));
        zip.benchmark(num);

        // zip.collisions(13513);
        // zip.collisions(13600);
        // zip.collisions(14000);
        // zip.collisions(20011);

    }
}