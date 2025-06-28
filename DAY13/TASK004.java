package DAY13;




class Node<T> {
    T data;
    Node<T> next;

    public Node(T value) {
        data = value;
        next = null;
    }
}


class LinkedList<T> {
    private Node<T> head;

    public LinkedList() {
        head = null;
    }

    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }
    public int getSize() {
        int count = 0;
        Node<T> temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;

    }


}


public class TASK004 {
    public static void main(String[] args) {

        LinkedList<Integer> intList = new LinkedList<>();
        intList.insertAtEnd(10);
        intList.insertAtEnd(20);
        intList.display(); // Output: 10 -> 20 -> NULL
        System.out.println("Size"+ intList.getSize());

        LinkedList<String> stringList = new LinkedList<>();
        stringList.insertAtEnd("Hello");
        stringList.insertAtEnd("World");
        stringList.display();


        LinkedList<Double> doubleList = new LinkedList<>();
        doubleList.insertAtEnd(3.14);
        doubleList.insertAtEnd(2.71);
        doubleList.display();
        LinkedList<Object> mixedList = new LinkedList<>();
        mixedList.insertAtEnd(100);
        mixedList.insertAtEnd("OpenAI");
        mixedList.insertAtEnd(5.5);
        mixedList.display();
        System.out.println("Size"+ intList.getSize());
    }
}


