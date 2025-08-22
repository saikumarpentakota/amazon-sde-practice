package buildermethod;

public interface Laptopbuilder {
    Laptopbuilder buildMemory(int memory);
    Laptopbuilder buildStorage(int storage);
    Laptopbuilder buildProcessor(String processor);
    Laptopbuilder buildGraphicsCard(String graphicsCard);
    Laptop build();
}
