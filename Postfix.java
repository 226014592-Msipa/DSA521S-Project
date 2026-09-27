import java.util.Scanner;

public class Postfix {

   
    int[] stack;
    int top;
    int size;

    
    Postfix(int size) {
        this.size = size;
        stack = new int[size];
        top = -1;
    }

   
    boolean isEmpty() {
        return (top == -1);
    }

    
    boolean isFull() {
        return (top == size - 1);
    }

   
    void push(int num) {
        if (isFull()) {
            System.out.println("Stack is full!");
        } else {
            top++;
            stack[top] = num;
        }
    }

    
    int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return 0;
        } else {
            int popped = stack[top];
            top--;
            return popped;
        }
    }

    
    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return 0;
        }
        return stack[top];
    }

    
    void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.print("Stack =: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }

  
    int evaluatePostfix(String expression) {
        String[] signs = expression.split(" ");

        for (int i = 0; i < signs.length; i++) {
            String sign = signs[i];

            switch (sign) {
                case "+":
                case "-":
                case "x":
                case "/":
                    int right = pop();
                    int left = pop();
                    int result = 0;

                    switch (sign) {
                        case "+": result = left + right; break;
                        case "-": result = left - right; break;
                        case "x": result = left * right; break;
                        case "/": result = left / right; break;
                    }

                    push(result);
                    System.out.println("Apply " + sign + " = " + result);
                    displayStack();
                    break;

                default:
                    push(Integer.parseInt(sign));
                    System.out.println("Push " + sign);
                    displayStack();
                    break;
            }
        }
 
        return pop();
    }

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Operators supported: + - x /");
        System.out.println("Enter numbers and operators separated by spaces.");
        System.out.println("eg. 5 3 + 2 x ");
        

        int again = 1;

        while (again == 1) {
            System.out.println("Enter postfix expression: ");
            String expression = input.nextLine();

            Postfix eval = new Postfix(50);

            System.out.println();
            System.out.println("Evaluating: " + expression);
            System.out.println();

            int result = eval.evaluatePostfix(expression);

            System.out.println();
            System.out.println("Final result: " + result);
            System.out.println();

            System.out.println("Evaluate another expression? (1 = yes, 0 = no): ");
            again = input.nextInt();
            input.nextLine();
            System.out.println();
        }

        System.out.println("end");
        input.close();
    }
}