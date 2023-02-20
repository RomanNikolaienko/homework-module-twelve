package Task2;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Task2 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newScheduledThreadPool(5);
        MultithreadingFizzBuzz fizzBuzz = new MultithreadingFizzBuzz();
        service.submit(() -> fizzBuzz.fizz());
        service.submit(() -> fizzBuzz.buzz());
        service.submit(() -> fizzBuzz.fizzBuzz());
        service.submit(() -> fizzBuzz.number());
        service.shutdown();

        QueueThread thread = new QueueThread();
        thread.run();
    }
}

class MultithreadingFizzBuzz {
    private final int n = 30;
    public static volatile AtomicInteger number = new AtomicInteger(1);
    public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public synchronized void fizz() {
        while (number.get() <= n) {
            if (number.get() % 3 == 0 && number.get() % 5 != 0) {
                queue.add("Fizz");
                number.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void buzz() {
        while (number.get() <= n) {
            if (number.get() % 3 != 0 && number.get() % 5 == 0) {
                queue.add("Buzz");
                number.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void fizzBuzz() {
        while (number.get() <= n) {
            if (number.get() % 3 == 0 && number.get() % 5 == 0) {
                queue.add("FizzBuzz");
                number.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void number() {
        while (number.get() <= n) {
            if (number.get() % 3 != 0 && number.get() % 5 != 0) {
                queue.add(String.valueOf(number.get()));
                number.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class QueueThread implements Runnable{
    @Override
    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.join(", ", MultithreadingFizzBuzz.queue));
    }
}