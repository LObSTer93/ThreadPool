import java.util.*;

class CustomQueue {

    private Map<Integer, Queue<Element>> queues = new HashMap<>(Main.GROUP_COUNT);
    private int currentElementCount = 0;

    synchronized void add(Element element) {
        while(currentElementCount == Main.QUEUE_ELEMENTS_COUNT){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int groupId = element.getGroupId();
        if(!queues.containsKey(groupId)){
            queues.put(groupId, new LinkedList<>());
        }
        queues.get(groupId).add(element);
    }
}