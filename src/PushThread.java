class PushThread extends Thread {

    private CustomQueue customQueue;

    private final int[] currentItemIds = new int[Main.groupCount];

    PushThread(CustomQueue customQueue) {
        super("PushThread");
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            int groupId = Main.getRandomInt();
            Element element = new Element(currentItemIds[groupId]++, groupId);
            customQueue.add(element);
        }
    }
}
