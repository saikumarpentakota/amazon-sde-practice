package DAY14;

public class TASK03 {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;
    Node tail = null;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }

    }

    public void traverse() {
        if (head == null){
            System.out.print("list is empty");
            return;

        }
        Node current = head;
        do {
            System.out.print(current.data + "->");
            current = current.next;
        }
        while(current != head);
        System.out.println("(back to head)");

        }

    public static void main(String[] args) {
        TASK03 List = new TASK03();
        List.add(30);
        List.add(40);
        List.add(80);
        List.add(77);
        List.traverse();
    }
}

