package DAY14;

import java.util.Stack;

public class TASK06 {
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        Integer searchInt = 10;
        System.out.println("stack: " + stack);
        int position = stack.search(searchInt);
        if (position != -1){
            System.out.println("found at position  " + position);}
        else {
            System.out.println("not found in the stack");
        }
        int peekedElement = stack.peek();
        System.out.println("top element:" + peekedElement);

        int poppedElement = stack.pop();
        System.out.println("popped element: " + poppedElement);
        System.out.println("stack after pop:" + stack);

        if (stack.isEmpty()){
            System.out.println("stack is empty");}
        else {
            System.out.println("stack is not empty");
        }

    }
}
