
public class LinkedList {

    Cell first;

    // Konstruktor för att skapa en ny länkad lista av storlek n
    public LinkedList(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
    }

    // Våra cell objekt som våran lista består av
    private class Cell {
        int head;
        Cell tail;

        // Konstruktor för att skapa en ny cell
        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }
    }

    public void add(int item) {
        first = new Cell(item, first);
    }

    public int length() {

        // Kontrollerar om våran lista är tom isf retunerar vi noll
        if (first == null) {
            return 0;
        }

        // Om first inte lika med noll innehåller listan minst 1 cell objekt
        int counter = 1;
        Cell current = first;
        while (current.tail != null) {
            counter++;
            current = current.tail;
        }
        return counter;
    }

    public boolean find(int item) {
        // Skapar en pekar som vi kan flytta mellan våra noder
        Cell current = first;

        // Går igenom hela vår lista
        while (current != null) {
            if (current.head == item) {
                return true;
            }
            current = current.tail;
        }
        // Når vi hit vet vi att värdet inte existerar
        return false;
    }

    public void remove(int item) {

        // Kontrollerar om vi har flera celler med samma värde i början av listan
        while (first != null && first.head == item) {
            first = first.tail;
        }

        // Om listan är tom så finns det inget att ta bort
        if (first == null) {
            return;
        }

        Cell current = first;
        while (current.tail != null) {
            if (current.tail.head == item) {
                current.tail = current.tail.tail;
            } else {
                current = current.tail;
            }
        }
    }

    // Method to add list to the end
    public void append(LinkedList b) {

        // Hanterar om list A är tom
        if (this.first == null) {
            this.first = b.first;
            b.first = null;
            return;
        }

        Cell current = this.first;
        while (current.tail != null) {
            current = current.tail;
        }
        current.tail = b.first;
        b.first = null;
    }
}

// We can now add methods to for example add another integer to the
// beginning of the list or finding the n’th integer in the list etc. Implement
// the following methods:

// ###void add(int item) : add the item as the first cell in the list.

// ###int length() : return the length of the list.

// ###boolean find(int item) : return true or false depending on if the

// item can be found in the list.
//  void remove(int item) : remove the item if it exists in the list.

// ArrayList, arrays