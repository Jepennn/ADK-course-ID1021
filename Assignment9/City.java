
import java.util.ArrayList;

// Inre klass som representarar en stad och dess förbindelser
public class City {
    String name;
    ArrayList<Connections> con;

    public City(String name) {
        this.name = name;
        con = new ArrayList<Connections>();
    }

    public void addConnection(City city, int time) {
        con.add(new Connections(city, time));
    }
}