package buildermethod;

public class ClientBuildermethodDP {
    public static void main(String[] args) {

        Laptopbuilder builder = new LaptopConcreteBuilder();
        LaptopDirector director = new LaptopDirector(builder);


        Laptop basicLaptop = director.constructBasicLaptop();
        System.out.println("Basic Laptop Configuration:");
        System.out.println(basicLaptop);


        Laptop gamingLaptop = director.constructGamingLaptop();
        System.out.println("\nGaming Laptop Configuration:");
        System.out.println(gamingLaptop);


        Laptop customLaptop = new LaptopConcreteBuilder()
                .buildMemory(16)
                .buildStorage(512)
                .buildProcessor("AMD Ryzen 7")
                .buildGraphicsCard("NVIDIA RTX 3060")
                .build();

        System.out.println("\nCustom Laptop Configuration:");
        System.out.println(customLaptop);
    }
}
