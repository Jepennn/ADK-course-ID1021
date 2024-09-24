class LinkedList {

    Cell first;

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

    public void Add(int item) {
        // If Linked list is emty we dont need a temp variabel
        if (first == null) {
            first = new Cell(item, null);
        } else {
            Cell temp = first;
            first = new Cell(item, temp);
        }
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

        // Vi antar att värdet inte finns
        boolean exists = false;

        // Skapar en copy av first så vi inte manipulerar våran "riktiga linked list"
        Cell current = first;
        // Kör igenom våra lista och kollar om någon cell är lika med värdet vi söker
        while (current != null && !exists) {

            if (current.head == item) {
                exists = true;
            }
            current = current.tail;
        }
        return exists;
    }

    public void remove(int item) {

        // Om listan är tom så finns det inget att ta bort
        if (first == null) {
            return;
        }

        // Om första cellen är den vi vill ta bort
        if (first.head == item) {
            first = first.tail;
            return;
        }

        // Skapar en copy av first så vi inte manipulerar våran "riktiga linked list"
        Cell current = first;
        // Kollar om nästa cell är den vi vill ta bort
        while (current.tail != null) {
            if (current.tail.head == item) {
                current.tail = current.tail.tail;
                return;
            }
            current = current.tail;
        }
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