
import java.io.BufferedReader;
import java.io.FileReader;

public class Zipper {
    Bucket[] postnr;
    int max = 13513;

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
        Bucket next; // Hanterar kollisioner

        public Bucket(Area area) {
            this.area = area;
            this.next = null;
        }
    }

    public int hashFunction(int zip) {
        int index = zip % this.max;
        return index;
    }

    public Zipper(String file) {
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
                    Bucket current = postnr[index];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = new Bucket(new Area(code, row[1], Integer.valueOf(row[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }

    }

    public boolean lockUp(Integer zip) {
        int index = hashFunction(zip);
        Bucket current = postnr[index];
        while (current != null) {
            if (current.area.zipCode.equals(zip)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {

        Zipper zip = new Zipper("postnummer.csv");

        Integer num = 16968;

        System.out.println("");

        System.out.println("Looking up zip code: " + num);
        boolean result = zip.lockUp(num);
        System.out.println("Result: " + result);
    }
}
