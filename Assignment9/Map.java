import java.io.BufferedReader;
import java.io.FileReader;

public class Map {

    private Bucket[] map;
    private int mod = 100;

    public Map(String file) {

        map = new Bucket[mod];
        // Läser in filen och skapar en hashtabell med städer
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                // Läser in en rad i csv-filen och splittar den på ,
                String[] row = line.split(",");
                City one = lockUp(row[0]);
                City two = lockUp(row[1]);
                int distance = Integer.parseInt(row[2]);

                one.addConnection(two, distance);
                two.addConnection(one, distance);
            }
        } catch (Exception e) {
            System.out.println("file" + file + " not found or corrupt");
        }
    }

    // LockUp-funktion som returnerar en stad om den finns i hashtabellen
    public City lockUp(String name) {
        int index = hash(name, this.mod);
        Bucket curBucket = this.map[index];

        // Om det inte finns någon stad på indexet. Lägger vi till vår nys staden vi får
        // som input
        if (curBucket == null) {
            City city = new City(name);
            Bucket newBucket = new Bucket(city);
            this.map[index] = newBucket;
            return city;
        }

        while (curBucket != null) {
            if (curBucket.city.name.equals(name)) {
                return curBucket.city;
            }
            curBucket = curBucket.next;
        }

        City city = new City(name);
        Bucket newBucket = new Bucket(city);

        // Lägger till staden i högst upp i länkade listan
        newBucket.next = this.map[index];
        this.map[index] = newBucket;
        return city;
    }

    // Hashfunktion som returnerar ett index i hashtabellen
    private static Integer hash(String name, int mod) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 + name.charAt(i)) % mod;
        }
        return hash;
    }

    // Inre klass som används för att hantera kollisioner i hashtabellen den håller
    // en city och en next bucket om det finns kollisioner
    public class Bucket {
        City city;
        Bucket next; // Kollisionshantering med länkad lista

        public Bucket(City city) {
            this.city = city;
            this.next = null;
        }
    }
}