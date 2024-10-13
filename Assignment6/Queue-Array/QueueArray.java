
public class QueueArray {

    Integer[] queue;
    int size; // Arraystorleken sparade i ett attribute
    int n; // Håller koll på antalet objekt i kön
    int point_first; // pekar på platsen i kön
    int point_last; // pekar på första lediga position i kön

    // Konstruktor
    public QueueArray(int size) {
        this.size = size;
        this.queue = new Integer[size];
        this.point_first = 0;
        this.point_last = 0;
        this.n = 0;
    }

    // Kontrollerar om våra pekar pekar på samma plats i kön
    public boolean empty() {
        return ((point_first == point_last) && (n == 0));
    }

    // Samma metod som ovan förtyligare enqueue metoden nedan
    public boolean full() {
        return ((point_last == point_first) && (size == n));
    }

    // Ta bort saker i listan
    public Integer dequeue() {

        // Om kön är tom dvs, first_pointer == last_pointer efter en dequeue metod.
        if (empty()) {
            System.out.println("Kön är tom");

            // Halverar storleken på kön om den är större än 10 annars behövs det inte. Då
            // nollställer vi bara pekare och element
            if (size > 10) {
                Integer[] newQueue = halfArray(queue);
                queue = newQueue;
            }
            point_first = 0;
            point_last = 0;

            return null;
        }
        // deafult case
        else {
            int x = queue[point_first];
            queue[point_first] = null;
            n--;
            point_first = (point_first + 1) % size;
            return x;
        }

    }

    public void enqueue(int value) {

        // Om kön blir full. Måste vi utöka med fler platser.(Detta funkar inte, full
        // stämmer alltid i början när vi kör en enqueue, både last,first==0)
        if (full()) {
            System.out.println("Utökar kön");

            Integer[] largerQueue = new Integer[size * 2];

            // Lägger till våra objekt från den fulla listan till den nya listan
            for (int i = 0; i < n; i++) {
                largerQueue[i] = queue[(point_first + i) % size];
            }

            // Uppdaterar nya köns egenskapertill den större.
            queue = largerQueue;
            // Uppdaterar kön storlek;
            size = largerQueue.length;
            // Sätter pekar på första objektet i kön och första lediga platsen i kön.
            point_first = 0;
            point_last = n;

        }

        // Deafult läget. Lägger till värdet i kön och ökar pekare, samt antalet element
        // i kön.
        queue[point_last] = value;
        point_last = (point_last + 1) % size;
        n++;
    }

    /* HJÄLP FUNKTIONER ATT ANVÄNDA I DEQUEUE OCH ENQUEUE METODERNA */

    // Dubblar storkleken på arrayen
    public Integer[] doubleArray(Integer[] arr) {
        Integer[] x = new Integer[arr.length * 2];
        return x;
    }

    // Halverar storleken på arrayen
    public Integer[] halfArray(Integer[] arr) {
        Integer[] y = new Integer[arr.length / 2];
        return y;
    }

    /* HÄR ÄR TEST METODER FÖR ATT UNDERLÄTTA TESTNINGEN AV VÅRAN KÖ */

    // En metod som bara printar ut en array med int
    public static void printArray(Integer[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {

        QueueArray q = new QueueArray(5);

        // Fyller kön
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        printArray(q.queue);
        System.out.println("");

        // Tömmer hela kön
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();

        printArray(q.queue);
        System.out.println("");

        // Här ifrån är kön helt tom
        // q.dequeue();
        // q.dequeue();
        // q.dequeue();
        q.dequeue();
        printArray(q.queue);
        System.out.println("");

        // Här börjar vi öka kön igen och testar utökningsfunktionen
        q.enqueue(5);
        q.enqueue(4);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(1);
        q.enqueue(0);

        printArray(q.queue);

    };

}