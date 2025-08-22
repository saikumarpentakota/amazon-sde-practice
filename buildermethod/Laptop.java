package buildermethod;

public class Laptop {
    private int memory;
    private int storage;
    private String processor;
    private String graphicsCard;


    public Laptop() {}


    public void setMemory(int memory) {
        this.memory = memory;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }


    public int getMemory() {
        return memory;
    }

    public int getStorage() {
        return storage;
    }

    public String getProcessor() {
        return processor;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    @Override
    public String toString() {
        return "Laptop [Memory=" + memory + "GB, Storage=" + storage +
                "GB, Processor=" + processor + ", Graphics Card=" + graphicsCard + "]";
    }
}
