public class EagerInitialization {
    // Instance is created at class loading time
    private static final EagerInitialization instance = new EagerInitialization();

    // Instance variable to track state
    private int value;

    // Private constructor prevents instantiation from other classes
    private EagerInitialization(){
        value = 0;
        System.out.println("EagerInitialization instance created during class loading");
    }

    // Public method to access the singleton instance
    public static EagerInitialization getInstance(){
        System.out.println("Returning pre-created instance");
        return instance;
    }

    // Method to modify state
    public void incrementValue() {
        value++;
    }

    // Method to access state
    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.out.println("Starting main method");

        // Test in single-threaded environment
        System.out.println("\nSingle-threaded test:");
        EagerInitialization singleton1 = EagerInitialization.getInstance();
        EagerInitialization singleton2 = EagerInitialization.getInstance();

        // Verify both references point to the same object
        System.out.println("singleton1 == singleton2: " + (singleton1 == singleton2));

        // Modify state through one reference and check it in the other
        singleton1.incrementValue();
        System.out.println("singleton1 value: " + singleton1.getValue());
        System.out.println("singleton2 value: " + singleton2.getValue());

        // Test in multi-threaded environment
        System.out.println("\nMulti-threaded test:");

        // Create multiple threads that try to get the singleton instance
        Thread thread1 = new Thread(() -> {
            EagerInitialization s = EagerInitialization.getInstance();
            s.incrementValue();
            System.out.println("Thread 1: value = " + s.getValue());
        });

        Thread thread2 = new Thread(() -> {
            EagerInitialization s = EagerInitialization.getInstance();
            s.incrementValue();
            System.out.println("Thread 2: value = " + s.getValue());
        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check final value
        System.out.println("Final value: " + EagerInitialization.getInstance().getValue());

        // Performance test
        System.out.println("\nPerformance test with multiple threads:");
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                EagerInitialization.getInstance();
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for 10 threads: " + (endTime - startTime) + "ms");
    }
}

