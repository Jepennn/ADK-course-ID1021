public class TestStacks {

    public static void main(String[] args) {

        DynamicStack stack = new DynamicStack(16);

        for (Integer i = 0; i < 32; i++) {
            stack.push(i);
        }

        try {
            Integer j = stack.pop();
            while (true) {
                System.out.printf(" pop: %d\n", j);
                j = stack.pop();
            }
        } catch (IllegalStateException e) {
            System.out.println("Stack is empty, no more items to pop.");
        }

    }

}