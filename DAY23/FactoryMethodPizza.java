package DAY23;

interface Pizza {
    void preparation();
    void baking();
    void cutting();
    void boxing();
}


class PepperoniPizza implements Pizza {
    public void preparation() {
        System.out.println("Preparing Pepperoni Pizza with dough, sauce, and pepperoni.");
    }

    public void baking() {
        System.out.println("Baking Pepperoni Pizza at 350 degrees.");
    }

    public void cutting() {
        System.out.println("Cutting Pepperoni Pizza into 8 slices.");
    }

    public void boxing() {
        System.out.println("Boxing Pepperoni Pizza in official PizzaStore box.");
    }
}


abstract class PizzaFactory {
    public abstract Pizza createPizza();
}


class PepperoniPizzaFactory extends PizzaFactory {
    public Pizza createPizza() {
        return new PepperoniPizza();
    }
}


public class FactoryMethodPizza {
    public static void main(String[] args) {
        PizzaFactory pfobj = new PepperoniPizzaFactory();
        Pizza pobj = pfobj.createPizza();

        pobj.preparation();
        pobj.baking();
        pobj.cutting();
        pobj.boxing();
    }
}
