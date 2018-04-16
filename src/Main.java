import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    final static int GROUP_COUNT = 10;
    private final static int THREAD_COUNT = 5;
    final static int QUEUE_ELEMENTS_COUNT = 1000;

    final static Random RANDOM = new Random();
    static int getRandomInt(){
        return RANDOM.nextInt(Main.GROUP_COUNT);
    }

    public static void main(String[] args) {
        CustomQueue customQueue = new CustomQueue();

        Thread pushThread = new PushThread(customQueue);
        pushThread.start();

        Processor processor = new Processor();
        IntStream.range(0, THREAD_COUNT).forEach(i -> {
            QueueHandlerThread queueHandlerThread = new QueueHandlerThread(customQueue, processor, i);
            queueHandlerThread.start();
        });
    }
}