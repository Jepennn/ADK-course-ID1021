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
    /*----------------------------------------------------Inre klassen dynamic stack -------------------------------------------------------- */
    public class dynamicStack {

        Node[] stack;
        int top = 0;
        int size;

        public dynamicStack(int size) {
            this.size = size;
            stack = new Node[this.size];
        }

        public void push(Node node) {
            if (top == size) {
                resize(size * 2);
            }
            stack[top++] = node;
        }

        public Node pop() {

            if (top == 0) {
                throw new IllegalStateException("Stack is empty, cant pop that many items");
            }

            Node node = stack[--top];
            // Lägg till stack[--top] = null??
            if (size > 4 && top > 0 && top == size / 4) {
                resize(stack.length / 2);
            }
            return node;
        }

        // Updating the size of the stack
        private void resize(int newSize) {
            Node[] newStack = new Node[newSize];
            for (int i = 0; i < top; i++) {
                newStack[i] = stack[i];
            }
            stack = newStack;
            size = newSize; // Updating the size value of the stack
            System.out.println(size);
        }

        public boolean isEmpty() {
            return top == 0;
        }
    }

    /*----------------------------------------------------Inre klassen dynamic stack slutar här-------------------------------------------------------- */
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

    public void stackPrintTree() {

        dynamicStack stack = new dynamicStack(20);
        Node current = root;

        while (current.left != null) {
            stack.push(current);
            current = current.left;
        }

        while (current != null) {
            System.out.println(current.value);

            if (current.right != null) {
                current = current.right;
                while (current.left != null) {
                    stack.push(current);
                    current = current.left;
                }
            } else {
                if (stack.isEmpty())
                    return;
                current = stack.pop();
            }
        }

    }
}