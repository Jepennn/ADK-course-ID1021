public class Main {

    public static void main(String[] args) {

        Map map = new Map("trains.csv");

        City start = map.lockUp("Stockholm");

        for (Connections c : start.con) {
            System.out.println(c.city.name + " " + c.time);
        }
    }

}
