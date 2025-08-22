package buildermethod;

public class LaptopConcreteBuilder implements Laptopbuilder {
    private Laptop laptop;

    public LaptopConcreteBuilder() {
        this.laptop = new Laptop();
    }

    @Override
    public Laptopbuilder buildMemory(int memory) {
        laptop.setMemory(memory);
        return this;
    }

    @Override
    public Laptopbuilder buildStorage(int storage) {
        laptop.setStorage(storage);
        return this;
    }

    @Override
    public Laptopbuilder buildProcessor(String processor) {
        laptop.setProcessor(processor);
        return this;
    }

    @Override
    public Laptopbuilder buildGraphicsCard(String graphicsCard) {
        laptop.setGraphicsCard(graphicsCard);
        return this;
    }

    @Override
    public Laptop build() {
        return laptop;
    }
}
