package DAY14;




public class TASK01 {
    class Node{
        int data;
        Node next;
        Node (int data){
            this.data = data;
            this.next = null;
        }
    }
    Node head;
    public void add (int data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null){
            current = current.next;
        }
        current.next = newNode;

    }
    public void traverse(){
        Node current = head ;
        while(current != null){
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args){
        TASK01 List = new TASK01();
        List.add(30);
        List.add(40);
        List.add(80);
        List.traverse();
    }

}
