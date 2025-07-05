package DAY14;

public class TASK09 {
    private int front,rear,capacity;
    private int[] queue;
    public TASK09(int size){
        capacity = size;
        front = 0;
        rear = -1;
        queue = new int[capacity];

    }
    public boolean isEmpty(){
        return front > rear ;
    }
    public boolean isFull(){
        return rear == capacity - 1;
    }
    public void enqueue(int data){
        if (isFull()) {
            System.out.println("queue is full cannot enqueue.");
        }
        else {
            rear ++;
            queue[rear]= data;
            System.out.println("enqueued; " + data);
        }
    }
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("QUEUE IS EMPTY cannot dequeue");
        } else {
            System.out.println("dequeued: " + queue[front]);
            front++;
        }
    }

    public void peek(){
            if(isEmpty()) {
                System.out.println("queue is empty nothing to peek");
            }
            else {
                System.out.println("front iteam  " + queue[front]);

            }
        }
        public void display(){
        if (isEmpty()){
            System.out.println("queue is empty");
        }
        else{
            System.out.println("queue contents: ");
            for (int i = front;i <=rear; i++){
                System.out.println(queue[i] + " ");
            }
            System.out.println();

        }
        }
    public static void main(String[] args){
        TASK09 q = new TASK09(5);
        q.enqueue(10);
        q.enqueue(30);
        q.enqueue(50);
        q.display();
        q.peek();
        q.display();
        q.dequeue();
    }


    }
