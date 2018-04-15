public class Main {

    final static int GROUP_COUNT = 10;
    private final static int THREAD_COUNT = 5;
    final static int QUEUE_ELEMENTS_COUNT = 1000;

    public static void main(String[] args) {
        CustomQueue customQueue = new CustomQueue();

        Thread pushThread = new PushThread(customQueue);
        pushThread.start();


    }
}