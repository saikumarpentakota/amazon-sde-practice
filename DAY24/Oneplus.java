package DAY24;

public class Oneplus{
    private Oneplus() {}

    public static MOBILE getMobile(String model) {
        if(model.equals("12")) {
            return new MOBILE("Here is your OnePlus 12");
        }
        else if (model.equals("12R")) {
            return new MOBILE("Here is your OnePlus 12R");
        }
        return new NoMobile();
    }
}
