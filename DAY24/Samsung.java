package DAY24;

public class Samsung {
    private Samsung() {}

    public static MOBILE getMobile(String model) {
        if(model.equals("s25")) {
            return new MOBILE("Here is your Samsung S25");
        }
        else if (model.equals("s25Ultra")) {
            return new MOBILE("Here is your Samsung S25 Ultra");
        }
        return new NoMobile();
    }
}
