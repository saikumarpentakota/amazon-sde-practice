class Test extends Thread{
    public void run(){
        System.out.println("thread started.");
    }
}

public class TASK000018 {
    public static void main(String args[]){

        Test t1 = new Test();
        Thread th1 = new Thread();
        th1.run();
        t1.run();
    }
}

