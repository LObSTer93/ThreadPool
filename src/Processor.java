class Processor {

    private final int[] nextItemIds = new int[Main.GROUP_COUNT];

    synchronized void processing(Element element) {
        while(nextItemIds[element.getGroupId()] != element.getItemId()){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Processing element with groupId=" + element.getGroupId() + ", itemId=" + element.getItemId()
            + " by process " + Thread.currentThread().getName());
        nextItemIds[element.getGroupId()]++;
        notifyAll();
    }
}