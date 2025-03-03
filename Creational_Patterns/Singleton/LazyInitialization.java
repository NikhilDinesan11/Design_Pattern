public class LazyInitialization {
    private static LazyInitialization instance;

    private LazyInitialization() {
        // Private constructor
    }

    public static LazyInitialization getInstance() {
        if (instance == null) {
            instance = new LazyInitialization();
            System.out.println("A new instance has been created.. " + instance);
        } else {
            System.out.println("Using existing instance.. " + instance);
        }
        return instance;
    }

    // Sample method to demonstrate the singleton works
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Get the first instance
        System.out.println("Getting first instance:");
        LazyInitialization firstInstance = LazyInitialization.getInstance();
        firstInstance.showMessage();

        // Get the second instance (should be the same object)
        System.out.println("\nGetting second instance:");
        LazyInitialization secondInstance = LazyInitialization.getInstance();
        secondInstance.showMessage();

        // Check if both instances are the same
        boolean isSameInstance = (firstInstance == secondInstance);
        System.out.println("\nBoth references point to the same instance: " + isSameInstance);
    }
}