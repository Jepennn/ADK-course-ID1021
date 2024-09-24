
public class DynamicStack {

    int[] stack;
    int top = 0;
    int size;

    public DynamicStack(int size) {
        this.size = size;
        stack = new int[this.size];
    }

    public void push(int num) {
        if (top == size) {
            resize(size * 2);
        }
        stack[top++] = num;
    }

    public int pop() {

        if (top == 0) {
            throw new IllegalStateException("Stack is empty, cant pop that many items");
        }

        int data = stack[--top];
        if (size > 4 && top > 0 && top == size / 4) {
            resize(stack.length / 2);
        }
        return data;
    }

    // Updating the size of the stack
    private void resize(int newSize) {
        int[] newStack = new int[newSize];
        for (int i = 0; i < top; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
        size = newSize; // Updating the size value of the stack
        System.out.println(size);
    }

    public static void main(String[] args) {
        // Initiera stacken med en specifik storlek
        DynamicStack s = new DynamicStack(0);

        s.push(32);
        s.push(33);
        s.push(34);

        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
    }
}