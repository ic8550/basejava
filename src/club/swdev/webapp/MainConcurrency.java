package club.swdev.webapp;

public class MainConcurrency {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static final Thread thread1 = new Thread(() -> {
        synchronized (lock1) {
            System.out.println("Thread1:    Holding lock1, waiting for lock2...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("Thread1:    Holding lock1 and lock2, executing normally...");
            }
        }
    });

    private static final Thread thread2 = new Thread(() -> {
        synchronized (lock2) {
            System.out.println("Thread2:    Holding lock2, waiting for lock1...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.println("Thread2:    Holding lock2 and lock1, executing normally...");
            }
        }
    });
}
