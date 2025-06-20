class OuterClass1 {
    int x = 10;
    static class InnerClass1 {
        int y = 5;
    }
}

public class TASK00011 {
    public static void main(String[] args) {
        OuterClass1 myOuter1 = new OuterClass1();
        OuterClass1.InnerClass1 myInner1 = new OuterClass1.InnerClass1();
        System.out.println(myInner1.y + myOuter1.x);
    }
}


