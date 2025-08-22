package DAY24;

public class Mobilestore {
    private Mobilestore() {
        System.out.println("Hello welcome to the world of Mobile");
    }

    public static MOBILE getMobile(String brand, String model) {
        if(brand.equals("Apple")) {
            System.out.println("Here are your Apple Models");
            return APPLE.getMobile(model);
        }
        else if (brand.equals("Samsung")) {
            System.out.println("Here are your Samsung Models");
            return Samsung.getMobile(model);
        }
        else if (brand.equals("OnePlus")) {
            System.out.println("Here are your OnePlus Models");
            return Oneplus.getMobile(model);
        }
        return new NoMobile();
    }
}
