package Task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main started");
        Thread thread1 = new Thread(new First());
        Thread thread2 = new Thread(new Second());
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        System.out.println("Main finished");

    }
}

class First implements Runnable {
    @Override
    public void run() {
        System.out.println("Start of the first program");
        for (int i = 0; i <= 30; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("End of the first program");
    }
}

class Second implements Runnable {
    @Override
    public void run() {
        System.out.println("Start of the second program");
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("5 seconds passed");
        }
        System.out.println("End of the second program");
    }
}
