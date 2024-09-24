
public class StaticStack {

    int[] stack;
    int top = 0;

    // Constructor
    public StaticStack(int size) {
        stack = new int[size];
    }

    public void push(int num) {
        if (top == stack.length) {
            throw new StackOverflowError("Stack is full, can't push that many items");
        } else {
            stack[top++] = num;
        }
    }

    public int pop() {

        if (top == 0) {
            throw new IllegalStateException("Stack is empty, can't pop that many items");
        }
        return stack[--top];
    }

    public static void main(String[] args) {

        StaticStack s = new StaticStack(4);

        s.push(32);
        s.push(33);
        s.push(34);
        s.push(31);

        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
    }
}