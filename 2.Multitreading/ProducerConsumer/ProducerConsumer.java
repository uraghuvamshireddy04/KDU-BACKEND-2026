

public class ProducerConsumer {
    static void main() {
        MessageQueue queue = new MessageQueue();
        for(int i = 1; i <= 3; i++){
            Thread t = new Thread(new MessageSender(queue, "P"+i));
            t.start();
        }
        for(int i = 1; i <= 3; i++){
            Thread t = new Thread(new MessageReceiver(queue, "C"+i));
            t.start();
        }

    }
}
