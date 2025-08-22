package StrategyDesign;

import java.util.ArrayList;
import java.util.List;

public class DManager {

    private static DManager instance;


    private List<String> itemList;


    private DManager() {

        itemList = new ArrayList<>();
    }


    public static synchronized DManager getInstance() {
        if (instance == null) {
            instance = new DManager();
        }
        return instance;
    }


    public static DManager createInstance() {
        if (instance != null) {
            throw new IllegalStateException("Instance already exists!");
        }
        instance = new DManager();
        return instance;
    }


    public synchronized void addItem(String item) {
        itemList.add(item);
        System.out.println("Item added: " + item);
    }


    public synchronized boolean removeItem(String item) {
        boolean removed = itemList.remove(item);
        if (removed) {
            System.out.println("Item removed: " + item);
        }
        return removed;
    }


    public synchronized List<String> retrieveList() {
        return new ArrayList<>(itemList); // Return a copy to maintain encapsulation
    }
}


class Singleton {
    public static void main(String[] args) {

        DManager manager = DManager.getInstance();


        manager.addItem("Item 1");
        manager.addItem("Item 2");
        manager.addItem("Item 3");

        // Retrieve and print list
        System.out.println("Current list: " + manager.retrieveList());


        manager.removeItem("Item 2");


        System.out.println("Updated list: " + manager.retrieveList());


        try {
            DManager.createInstance();
        } catch (IllegalStateException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
