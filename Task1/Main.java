package Task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Program started");
        Thread thread1 = new Thread(new First());
        Thread thread2 = new Thread(new Second());
        thread1.run();
        thread2.run();
        thread1.join();
        thread2.join();
        System.out.println("Program finished");

    }
}

class First implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 30; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Second implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("5 seconds passed");
        }
    }
}
