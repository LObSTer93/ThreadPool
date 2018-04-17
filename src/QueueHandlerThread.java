public class QueueHandlerThread extends Thread{

    private CustomQueue customQueue;
    private Processor processor;

    QueueHandlerThread(CustomQueue customQueue, Processor processor, int i) {
        super("QueueHandlerThread" + i);
        this.customQueue = customQueue;
        this.processor = processor;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            Element element = customQueue.get();
            processor.processing(element);
        }
        Main.interruptAllThreads();
    }
}