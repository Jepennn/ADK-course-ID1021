import java.util.NoSuchElementException;

public class Queue {

    Node head;

    // Skapar en tom lista med en Node som inte pekar på något
    public Queue() {
        this.head = null;
    }

    // Metod för att gå objekt
    public void enqueue(Integer item) {
        // Om kön är tom
        if (head == null) {
            head = new Node(item, null);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(item, null);
        }
    }

    public Integer dequeue() {

        if (head == null) {
            return null;
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
    public class Node {
        Integer item;
        Node next;

        // KONSTRUKTUR FÖR VÅRA NODER
        private Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

}

/*---------------------------Frågeställning 1--------------------------*/
// We can return a null value if we try to dequeue an item from an empty
// queue. This is ok for now but of course has the drawback that one would
// have to check for a returned null value.
// What is the drawback of this implementation? What is the cost of
// removing the next element? What is the cost of adding a new element?
// Can we do better?
