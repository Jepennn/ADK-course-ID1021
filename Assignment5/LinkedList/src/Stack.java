// Purpose: Implement a stack using linked list.
public class Stack {

    Node top;

    public Stack() {
        top = null;
    }

    public void push(int data) {
        top = new Node(data, top);
    }

    public int pop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return -1;
        }

        int data = top.data;
        top = top.next;
        return data;
    }

    private class Node {
        int data;
        Node next;

        public Node(int data, Node ref) {
            this.data = data;
            this.next = ref;
        }
    }

}
