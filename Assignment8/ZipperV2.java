
import java.io.BufferedReader;
import java.io.FileReader;

public class ZipperV2 {
    Bucket[] postnr;
    int max = 20011;

    /*---------------------Intern klass som innehåller data om vårat postnummer--------------------*/
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

    public class Bucket {
        Area area;
        // Bucket next; Nu hanterar vi kollisioner i bucket arrayen istället

        public Bucket(Area area) {
            this.area = area;
        }
    }

    public int hashFunction(int zip) {
        int index = zip % this.max;
        return index;
    }

    public ZipperV2(String file) {
        this.postnr = new Bucket[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));

                // Hashar postnummret så vi får ett index
                int index = hashFunction(code);

                if (postnr[index] == null) {
                    postnr[index] = new Bucket(new Area(code, row[1], Integer.valueOf(row[2])));
                } else {
                    while (postnr[index] != null) {
                        index = (index + 1) % this.max;
                    }
                    postnr[index] = new Bucket(new Area(code, row[1], Integer.valueOf(row[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }

    }

    public boolean lockUp(Integer zip) {
        int index = hashFunction(zip);
        int counter = 0;

        if (postnr[index] != null && postnr[index].area.zipCode.equals(zip)) {
            return true;
        } else {
            while (postnr[index] != null) {
                counter++;
                if (postnr[index].area.zipCode.equals(zip)) {
                    System.out.println("Number of steps: " + counter);
                    return true;
                }
                index = (index + 1) % this.max;
            }
            return false;
        }
    }

    public static void main(String[] args) {

        ZipperV2 zip = new ZipperV2("postnummer.csv");

        Integer num = 11122;

        System.out.println("");

        System.out.println("Looking up zip code: " + num);
        boolean result = zip.lockUp(num);
        System.out.println("Result: " + result);
    }
}
