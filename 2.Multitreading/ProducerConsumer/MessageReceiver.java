public class MessageReceiver implements Runnable {
    private MessageQueue queue;
    private String name;
    public MessageReceiver(MessageQueue queue,String name){
        this.queue=queue;
        this.name=name;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            try {
                String message = queue.take();
                System.out.println("Consumer Output - "+ message + name);
                Thread.sleep(90);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


