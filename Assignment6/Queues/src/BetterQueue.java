import java.util.NoSuchElementException;

//Detta är en förbättring av våran vanliga queue class. 
//Better queue innehåller en pointer till första och sista elementet i kön
public class BetterQueue {

    Node head;
    Node last;

    // Skapar en tom lista med en Node som inte pekar på något
    public BetterQueue() {
        this.head = null;
        this.last = null;
    }

    // Metod för att gå objekt
    public void enqueue(Integer item) {
        // Om kön är tom
        if (head == null) {
            head = new Node(item, null);
            last = head;
        } else {
            Node ref = last;
            ref.next = new Node(item, null);
            last = ref.next;
        }
    }

    public Integer dequeue() {

        if (head == null) {
            throw new NoSuchElementException("Kön är tom");
        } else {
            Integer num = head.item;
            head = head.next;
            return num;
        }

    }

    public boolean isEmpty() {
        return head == null;
    }

    // NOD KLASSEN SOM ÄR OBJEKTEN I VÅRAN QUEUE
    private class Node {
        Integer item;
        Node next;

        // KONSTRUKTUR FÖR VÅRA NODER
        private Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

}