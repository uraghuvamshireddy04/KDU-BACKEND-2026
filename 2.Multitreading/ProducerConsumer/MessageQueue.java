import java.util.ArrayDeque;
import java.util.Deque;

public class MessageQueue {
    Deque<String> messageQueue = new ArrayDeque<>();
    public synchronized void put(String message) throws  InterruptedException {
        messageQueue.addLast(message);
        System.out.println("Producer Input - " + message);
        notifyAll();
    }
    public synchronized String take() throws InterruptedException{
        while(messageQueue.isEmpty()){
            wait();
        }
        String result = messageQueue.removeFirst();
        notifyAll();
        return result;
    }

}
