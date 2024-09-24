
import java.io.*;

public class HP35 {

    public static void main(String[] args) throws IOException {

        // Intiates our stack
        DynamicStack stack = new DynamicStack(16);

        System.out.println("HP-35 pocket calculator");

        boolean run = true;

        // Creates a new BufferedReader object
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while (run) {
            System.out.println("> ");
            String input = buffer.readLine();

            switch (input) {
                case "+":
                    int n1 = stack.pop();
                    int n2 = stack.pop();
                    int sum = n1 + n2;
                    stack.push(sum);
                    System.out.println("The result is: " + sum);
                    break;
                case "-":
                    int n3 = stack.pop();
                    int n4 = stack.pop();
                    int diff = n3 - n4;
                    stack.push(diff);
                    System.out.println("The result is: " + diff);
                    break;
                case "*":
                    int n5 = stack.pop();
                    int n6 = stack.pop();
                    int prod = n5 * n6;
                    stack.push(prod);
                    System.out.println("The result is: " + prod);
                    break;
                case "":
                    run = false;
                    break;

                default:
                    Integer nr = Integer.parseInt(input);
                    stack.push(nr);
                    break;
            }
        }
        System.out.printf("the result is: %d\n\n", stack.pop());
        // System.out.printf("I love reversed polish notation, don't you?\n");
    }
}