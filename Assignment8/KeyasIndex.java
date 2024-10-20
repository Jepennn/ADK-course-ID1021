
import java.io.BufferedReader;
import java.io.FileReader;

public class KeyasIndex {
    Area[] postnr;
    int max = 100000;

    // Intern klass som innehåller data om vårat postnummer
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

    public KeyasIndex(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                postnr[code] = new Area(code, row[1], Integer.valueOf(row[2]));
                i++;
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println("File " + file + " not found");
        }
    }

    // Linjär sökningsmetoden
    public boolean lockUp(Integer zip) {

        for (int i = 0; i < postnr.length; i++) {
            if (postnr[i] != null && postnr[i].zipCode.equals(zip)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        KeyasIndex zip = new KeyasIndex("postnummer.csv");

        Integer num = 16968;

        System.out.println(zip.lockUp(num));

    }
}