public class DoubleCheckedSingleton {
    // The volatile keyword is crucial here to prevent partially constructed object issues
    private static volatile DoubleCheckedSingleton instance;

    // Instance variable to demonstrate state
    private int value;

    // Private constructor prevents instantiation from other classes
    private DoubleCheckedSingleton() {
        value = 0;
        System.out.println("New instance created");
    }

    // Proper double-checked locking pattern
    public static DoubleCheckedSingleton getInstance() {
        // First check (not synchronized)
        if (instance == null) {
            // Only synchronize if instance might be null
            synchronized (DoubleCheckedSingleton.class) {
                // Second check (synchronized)
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                } else {
                    System.out.println("Using existing instance (inside sync block)");
                }
            }
        } else {
            System.out.println("Using existing instance (outside sync block)");
        }
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
        // Test in single-threaded environment
        System.out.println("Single-threaded test:");
        DoubleCheckedSingleton singleton1 = DoubleCheckedSingleton.getInstance();
        DoubleCheckedSingleton singleton2 = DoubleCheckedSingleton.getInstance();

        // Verify both references point to the same object
        System.out.println("singleton1 == singleton2: " + (singleton1 == singleton2));

        // Modify state through one reference and check it in the other
        singleton1.incrementValue();
        System.out.println("singleton1 value: " + singleton1.getValue());
        System.out.println("singleton2 value: " + singleton2.getValue());

        // Test in multithreaded environment
        System.out.println("\nMulti-threaded test:");

        // Create multiple threads that try to get the singleton instance
        Thread thread1 = new Thread(() -> {
            DoubleCheckedSingleton s = DoubleCheckedSingleton.getInstance();
            s.incrementValue();
            System.out.println("Thread 1: value = " + s.getValue());
        });

        Thread thread2 = new Thread(() -> {
            DoubleCheckedSingleton s = DoubleCheckedSingleton.getInstance();
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
        System.out.println("Final value: " + DoubleCheckedSingleton.getInstance().getValue());

        // Demonstrate thread performance with multiple threads
        System.out.println("\nPerformance test with multiple threads:");

        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                DoubleCheckedSingleton.getInstance();
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