interface Product{
    void operation();
}

class ConcreteProductA implements Product{

    @java.lang.Override
    public void operation() {
        System.out.println("Operation from Concrete Product A");
    }
}

class ConcreteProductB implements Product{

    @java.lang.Override
    public void operation() {
        System.out.println("Operation from Concrete Product B");
    }
}
// Creator - abstract class with the factory method
abstract class Creator {
    public abstract Product createProduct();

    // Some operation that uses the factory method
    public void someOperation() {
        // Create a product
        Product product = createProduct();
        // Use the product
        product.operation();
    }
}

// Concrete Creators
class ConcreteCreatorA extends Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

class ConcreteCreatorB extends Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}

// Client code
public class FactoryMethodDemo {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        creatorA.someOperation(); // Output: Operation from ConcreteProductA

        Creator creatorB = new ConcreteCreatorB();
        creatorB.someOperation(); // Output: Operation from ConcreteProductB
    }
}