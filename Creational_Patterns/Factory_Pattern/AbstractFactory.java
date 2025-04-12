interface Chair{
    void sitOn();
    String getStyle();
}

interface Sofa{
    void lieOn();
    String getStyle();
}

interface CoffeeTable {
    void putCoffeeOn();
    String getStyle();
}

class ModernChair implements Chair{

    @Override
    public void sitOn() {
        System.out.println("Sitting on a modern chair");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}

class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a modern sofa");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}


class ModernCoffeeTable implements CoffeeTable {
    @Override
    public void putCoffeeOn() {
        System.out.println("Putting coffee on a modern coffee table");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}

// Concrete Products - Victorian style
class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair");
    }

    @Override
    public String getStyle() {
        return "Victorian";
    }
}

class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a Victorian sofa");
    }

    @Override
    public String getStyle() {
        return "Victorian";
    }
}

class VictorianCoffeeTable implements CoffeeTable {
    @Override
    public void putCoffeeOn() {
        System.out.println("Putting coffee on a Victorian coffee table");
    }

    @Override
    public String getStyle() {
        return "Victorian";
    }
}

// Abstract Factory
interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    CoffeeTable createCoffeeTable();
}

// Concrete Factories
class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
}

// Client code
class FurnitureStore {
    private final FurnitureFactory factory;

    public FurnitureStore(FurnitureFactory factory) {
        this.factory = factory;
    }

    public void orderFurnitureSet() {
        Chair chair = factory.createChair();
        Sofa sofa = factory.createSofa();
        CoffeeTable coffeeTable = factory.createCoffeeTable();

        System.out.println("Ordered a complete " + chair.getStyle() + " furniture set:");
        chair.sitOn();
        sofa.lieOn();
        coffeeTable.putCoffeeOn();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        // Create a modern furniture store
        FurnitureStore modernStore = new FurnitureStore(new ModernFurnitureFactory());
        modernStore.orderFurnitureSet();

        System.out.println("\n----------------------------\n");

        // Create a Victorian furniture store
        FurnitureStore victorianStore = new FurnitureStore(new VictorianFurnitureFactory());
        victorianStore.orderFurnitureSet();
    }
}
