package DAY24;



public class APPLE {
    private APPLE() {}

    public static MOBILE getMobile(String model) {
        if(model.equals("iphone16")) {
            return new MOBILE("Here is your iphone 16");
        }
        else if (model.equals("iphone16MaxPro")) {
            return new MOBILE("Here is your iphone 16 Max Pro");
        }
        return new NoMobile();
    }
}
