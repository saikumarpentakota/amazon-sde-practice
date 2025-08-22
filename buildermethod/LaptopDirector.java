package buildermethod;

public class LaptopDirector {
    private Laptopbuilder laptopbuilder;

    public LaptopDirector(Laptopbuilder laptopBuilder) {
        this.laptopbuilder = laptopBuilder;
    }


    public Laptop constructBasicLaptop() {
        return laptopbuilder
                .buildMemory(8)
                .buildStorage(256)
                .buildProcessor("Intel i5")
                .buildGraphicsCard("Integrated")
                .build();
    }


    public Laptop constructGamingLaptop() {
        return laptopbuilder
                .buildMemory(32)
                .buildStorage(1000)
                .buildProcessor("Intel i9")
                .buildGraphicsCard("NVIDIA RTX 3080")
                .build();
    }
}
