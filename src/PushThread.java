import java.util.Random;

class PushThread extends Thread {

    private CustomQueue customQueue;

    private final int[] currentItemIds = new int[Main.GROUP_COUNT];

    PushThread(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            Random random = new Random();
            int groupId = random.nextInt(Main.GROUP_COUNT);
            Element element = new Element(currentItemIds[groupId]++, groupId);
            customQueue.add(element);
        }
    }
}
