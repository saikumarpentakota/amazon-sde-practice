package DAY21;
interface ICalcShapesArea {
    double calcArea();
    double calcVolume();
}

class Circle implements ICalcShapesArea {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        double area = Math.PI * radius * radius;
        System.out.println("Circle area: " + area);
        return area;
    }

    @Override
    public double calcVolume() {
        System.out.println("Circle is a 2D shape - no volume");
        return 0;
    }
}

class Sphere implements ICalcShapesArea {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        double surfaceArea = 4 * Math.PI * radius * radius;
        System.out.println("Sphere surface area: " + surfaceArea);
        return surfaceArea;
    }

    @Override
    public double calcVolume() {
        double volume = (4.0/3.0) * Math.PI * Math.pow(radius, 3);
        System.out.println("Sphere volume: " + volume);
        return volume;
    }
}

public class TASK01 {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Sphere sphere = new Sphere(5);


        circle.calcArea();
        sphere.calcArea();


        circle.calcVolume();
        sphere.calcVolume();
    }
}

