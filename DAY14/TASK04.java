package DAY14;


import java.util.Stack;

public class TASK04 {
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("stack: " + stack);
        int poppedElement = stack.pop();
        System.out.println("popped element: " + poppedElement);
        System.out.println("stack after pop:" + stack);

    }
}
