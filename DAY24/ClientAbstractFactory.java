package DAY24;

public class ClientAbstractFactory {
    public static void main(String[] args) {

        MOBILE mObj1 = Mobilestore.getMobile("Apple", "iphone16");
        mObj1.getDesc();


        MOBILE mObj2 = Mobilestore.getMobile("Samsung", "s25");
        mObj2.getDesc();


        MOBILE mObj3 = Mobilestore.getMobile("OnePlus", "12");
        mObj3.getDesc();


        MOBILE mObj4 = Mobilestore.getMobile("Invalid", "model");
        mObj4.getDesc();

        System.out.println("...");
    }
}
