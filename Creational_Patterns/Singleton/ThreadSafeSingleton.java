public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    private int value;

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance==null){
            instance = new ThreadSafeSingleton();
        }else {
            System.out.println("Using existing instance");
        }

        return instance;
    }

    public void incrementValue(){
        value++;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args){
        // Test in single-threaded environment
        System.out.println("Single-threaded test:");
        ThreadSafeSingleton singleton1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance();

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
            ThreadSafeSingleton s = ThreadSafeSingleton.getInstance();
            s.incrementValue();
            System.out.println("Thread 1: value = " + s.getValue());
        });

        Thread thread2 = new Thread(() -> {
            ThreadSafeSingleton s = ThreadSafeSingleton.getInstance();
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
        System.out.println("Final value: " + ThreadSafeSingleton.getInstance().getValue());
    }

    }

