import java.util.*;

class CustomQueue {

    private Map<Integer, Queue<Element>> queues = new HashMap<>(Main.groupCount);
    private int currentElementCount = 0;

    synchronized void add(Element element) {
        while(currentElementCount == Main.queueElementsCount){
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
        currentElementCount++;
        notifyAll();
    }

    synchronized Element get() {
        while(currentElementCount == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int groupId;
        do{
            groupId = Main.getRandomInt();
        } while(!queues.containsKey(groupId) || queues.get(groupId).size() == 0);
        currentElementCount--;
        notifyAll();
        return queues.get(groupId).poll();
    }
}