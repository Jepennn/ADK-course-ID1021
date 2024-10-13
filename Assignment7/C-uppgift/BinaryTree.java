import java.util.NoSuchElementException;

public class BinaryTree {

    /*----------------------------------------------------Inre klassen Node-------------------------------------------------------- */
    private class Node {
        private Integer value;
        private Node left;
        private Node right;

        private Node(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        // Hjälp metod för att rekursivt
        private void addNode(Integer value) {
            // Vi tillåter inte dubbletter
            if (this.value == value) {
                return;
            }

            if (value < this.value) {
                if (this.left != null) {
                    this.left.addNode(value);
                } else {
                    this.left = new Node(value);
                }
            } else {
                if (this.right != null) {
                    this.right.addNode(value);
                } else {
                    this.right = new Node(value);
                }
            }
        }

        // Rekursiv metod för att printa ut våra noder i IN-ORDER
        public void print() {
            if (left != null)
                left.print();
            System.out.println(value);
            if (right != null)
                right.print();
        }
    }

    /*-----------------------------------------------------Inre klassen Node slutar här-------------------------------------------------------- */
    /*----------------------------------------------------Inre klass array queue -------------------------------------------------------- */
    public class QueueArray {

        Node[] queue;
        int size; // Arraystorleken sparade i ett attribute
        int n; // Håller koll på antalet objekt i kön
        int point_first; // pekar på platsen i kön
        int point_last; // pekar på första lediga position i kön

        // Konstruktor
        public QueueArray(int size) {
            this.size = size;
            this.queue = new Node[size];
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
        public Node dequeue() {

            // Om kön är tom dvs, first_pointer == last_pointer efter en dequeue metod.
            if (empty()) {
                System.out.println("Kön är tom");

                // Halverar storleken på kön om den är större än 10 annars behövs det inte. Då
                // nollställer vi bara pekare och element
                if (size > 10) {
                    Node[] newQueue = halfArray(queue);
                    queue = newQueue;
                }
                point_first = 0;
                point_last = 0;

                return null;
            }
            // deafult case
            else {
                Node x = queue[point_first];
                queue[point_first] = null;
                n--;
                point_first = (point_first + 1) % size;
                return x;
            }

        }

        public void enqueue(Node node) {

            if (full()) {
                System.out.println("Utökar kön");

                Node[] largerQueue = new Node[size * 2];

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
            queue[point_last] = node;
            point_last = (point_last + 1) % size;
            n++;
        }

        /* HJÄLP FUNKTIONER ATT ANVÄNDA I DEQUEUE OCH ENQUEUE METODERNA */

        // Dubblar storkleken på arrayen
        public Node[] doubleArray(Node[] arr) {
            Node[] x = new Node[arr.length * 2];
            return x;
        }

        // Halverar storleken på arrayen
        public Node[] halfArray(Node[] arr) {
            Node[] y = new Node[arr.length / 2];
            return y;
        }

        /* HÄR ÄR TEST METODER FÖR ATT UNDERLÄTTA TESTNINGEN AV VÅRAN KÖ */

        // En metod som bara printar ut en array med int
        public static void printArray(Node[] arr) {

            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
    }

    /*----------------------------------------------------Inre klassen array queue slutar här-------------------------------------------------------- */
    /*----------------------------------------------------Inre klassen sequence (datastruktur)-------------------------------------------------------- */
    public class Sequence {

        QueueArray queue;

        public Sequence(BinaryTree tree) {

            if (tree.root != null) {
                queue = new QueueArray(20);
                queue.enqueue(tree.root);
            } else {
                throw new NoSuchElementException("Trädet är tomt");
            }

        }

        public int next() {

            if (queue.empty()) {
                throw new NoSuchElementException("Kön är tom");
            }

            Node firstInQueue = queue.dequeue();
            // Lägger till vänster barn
            if (firstInQueue.left != null) {
                queue.enqueue(firstInQueue.left);
            }
            // Lägger till höger barn
            if (firstInQueue.right != null) {
                queue.enqueue(firstInQueue.right);
            }

            return firstInQueue.value;
        }

    }
    /*----------------------------------------------------Inre klassen sequence (datastruktur)-------------------------------------------------------- */

    // Rotnoden i vårt binäraträd
    private Node root;

    // Konstruktor
    public BinaryTree() {
        this.root = null;
    }

    /* Vanlig add metod utan rekursion */
    public void add(Integer value) {

        // Om kön är tom lägger vi till vår nod som rotnoden
        if (root == null) {
            root = new Node(value);
            return;
        }

        Node current = root;
        while (true) {
            if (value < current.value) {

                if (current.left == null) {
                    current.left = new Node(value);
                    return;
                } else {
                    current = current.left;
                }
            } else if (value > current.value) {

                if (current.right == null) {
                    current.right = new Node(value);
                    return;
                } else {
                    current = current.right;
                }

            } else {
                // Vi tillåter in dubletter i trädet så då returnera vi bara, onödig kontroll
                // men förtydligar vad vi gör
                if (value == current.value)
                    return;
            }

        }
    }

    public void recursiveAdd(Integer value) {

        if (root == null) {
            root = new Node(value);
        } else {
            root.addNode(value);
        }
    }

    public boolean lockUp(Integer value) {
        Node current = root;
        while (current != null) {
            if (current.value == value) {
                return true;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public void printTree() {
        root.print();
    }

    public void breathFirst() {

        // Om trädet är tomt returnar vi bara
        if (root == null) {
            return;
        }

        QueueArray queue = new QueueArray(20);

        Node cur = root;
        queue.enqueue(cur);
        queue.n++;

        while (!queue.empty()) {
            cur = queue.dequeue();
            queue.n--;
            System.out.println(cur.value);

            if (cur.left != null) {
                queue.enqueue(cur.left);
                queue.n++;
            }
            if (cur.right != null) {
                queue.enqueue(cur.right);
                queue.n++;
            }
        }
    }

    public Sequence sequence() {
        return new Sequence(this);
    }
}