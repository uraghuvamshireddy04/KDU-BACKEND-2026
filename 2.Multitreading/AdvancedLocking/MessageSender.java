public class MessageSender implements Runnable{
    private MessageQueue queue;
    private String name;
    public MessageSender(MessageQueue queue,String name){
        this.queue=queue;
        this.name=name;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            String message = ""+System.currentTimeMillis();
            try {
                queue.put(message);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
