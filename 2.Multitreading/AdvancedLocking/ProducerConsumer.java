public class ProducerConsumerExecutors {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        ExecutorService senderPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverPool = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 3; i++) {
            senderPool.submit(new MessageSender(queue, "Sender-"+i));
        }


        for(int i = 0; i < 3; i++) {
            receiverPool.submit(new MessageSender(queue, "Sender-"+i));
        }

        senderPool.shutdown();
        receiverPool.shutdown();

        senderPool.awaitTermination(1, TimeUnit.MINUTES);
        receiverPool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("All tasks completed.");
    }
}