import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    static int groupCount = 10;
    private static int threadCount = 5;
    static int queueElementsCount = 1000;

    private static Thread pushThread;
    private static Thread[] queueHandlerThreads = new Thread[threadCount];

    private final static Random RANDOM = new Random();
    static int getRandomInt(){
        return RANDOM.nextInt(Main.groupCount);
    }

    public static void main(String[] args) {
        initParams(args);

        CustomQueue customQueue = new CustomQueue();

        pushThread = new PushThread(customQueue);
        pushThread.start();

        Processor processor = new Processor();
        IntStream.range(0, threadCount).forEach(i -> {
            QueueHandlerThread queueHandlerThread = new QueueHandlerThread(customQueue, processor, i);
            queueHandlerThreads[i] = queueHandlerThread;
            queueHandlerThread.start();
        });
    }

    private static void initParams(String[] args){
        for (String arg : args) {
            String[] str = arg.split("=");
            try {
                switch (str[0]) {
                    case "groupCount": {
                        groupCount = Integer.parseInt(str[1]);
                        break;
                    }
                    case "threadCount": {
                        threadCount = Integer.parseInt(str[1]);
                        break;
                    }
                    case "queueElementsCount": {
                        queueElementsCount = Integer.parseInt(str[1]);
                        break;
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("Incorrect value for param: " + str[0]);
                throw e;
            }
        }
    }

    static void interruptAllThreads(){
        pushThread.interrupt();
        IntStream.range(0, threadCount).forEach(i -> {
            queueHandlerThreads[i].interrupt();
        });
    }
}