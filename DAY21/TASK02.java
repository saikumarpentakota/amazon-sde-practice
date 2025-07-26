package DAY21;

interface Bird {
    void eat();
}


interface Flyable {
    void fly();
}


class Eagle implements Bird, Flyable {
    @Override
    public void eat() {
        System.out.println("Eagle eats");
    }

    @Override
    public void fly() {
        System.out.println("Eagles fly");
    }
}


class Ostrich implements Bird {
    @Override
    public void eat() {
        System.out.println("Ostrich eats");
    }

    public void layEggs() {
        System.out.println("Ostrich lays big eggs");
    }
}

class DriverClass {
    public static void main(String[] args) {
        Eagle eagle = new Eagle();
        Ostrich ostrich = new Ostrich();


        eagle.eat();
        ostrich.eat();


        eagle.fly();


        ostrich.layEggs();


        Bird bird1 = new Eagle();
        Bird bird2 = new Ostrich();

        bird1.eat();
        bird2.eat();
    }
}
