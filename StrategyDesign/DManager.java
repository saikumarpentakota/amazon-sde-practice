package StrategyDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DManager {

    private static  volatile DManager instance;


    private final List<String> itemList;


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
        synchronized (DManager.class) {
            if (instance != null) {
                throw new IllegalStateException("Instance already exists!");
            }
            instance = new DManager();
            return instance;
        }
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
        return new ArrayList<>(itemList);
    }


}


class Singleton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DManager manager = DManager.getInstance();


        System.out.println("enter items(type 'Done' to finish):");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Done")) {
                break;
            }
            manager.addItem(input);
        }

        System.out.println("item to be removed: ");
        String itemToRemove = scanner.nextLine();
        manager.removeItem(itemToRemove);

        for (String item : manager.retrieveList()) {
            System.out.println(item);

        }
        scanner.close();

        System.out.println("Updated list: " + manager.retrieveList());

    }
}
