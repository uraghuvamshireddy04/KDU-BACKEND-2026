import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class MessageQueue {
    private final Deque<String> queue = new ArrayDeque<>();

    private final Lock lock = new ReentrantLock();

    public void put(String message) throws InterruptedException {
        lock.lock();
        try {
            queue.addLast(message);
            System.out.println("Produced: " + message);
            notEmpty.signalAll(); // Notify consumers
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            String msg = queue.removeFirst();
            System.out.println("Consumed: " + msg);
            notFull.signalAll(); // Notify producers
            return msg;
        } finally {
            lock.unlock();
        }
    }
}