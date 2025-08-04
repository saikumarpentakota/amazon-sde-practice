package DAY23;

class SingletonDemo {
    private static SingletonDemo instance; // lodinids.. //pass/ pin no
    SingletonDemo() {
        System.out.println("initiating the singleton");
    }


    public static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
            System.out.println("in get instance");
        }
        return instance;
    }
    public static void doHere() {
        System.out.println("doing here some thing");
    }
}
public class SingletonDp{
    public static void main(String[] args) {
        SingletonDemo.getInstance().doHere();


       SingletonDemo obj = new SingletonDemo();
        obj.doHere();
       SingletonDemo obj2 = new SingletonDemo();
       obj2.doHere();
    }
}


