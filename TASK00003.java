class RunnableDemo implements Runnable{
    private Thread t;
    private String threadName;
    RunnableDemo(String name){
        threadName = name;
        System.out.println("creating" + threadName);
    }
    public void run(){

        System.out.println("Running" + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("thread" + threadName + "," + i);
                Thread.sleep(5000);
            }
        }
            catch (InterruptedException e){
                System.out.println("thread" + threadName + "interrupted");
            }
            System.out.println("thread" + threadName + "exiting");
        }
        public void start(){
            System.out.println("starting" + threadName);
            if (t == null){
                t = new Thread(this , threadName);
                t.start();
            }
        }


    }
    public class TASK00003{
        public static void main(String args[]){
            RunnableDemo R1 = new RunnableDemo("thread-1");
            R1.start();
            RunnableDemo R2 = new RunnableDemo("thread-2");
            R2.start();
        }
    }



