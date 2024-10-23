package mark.multithreadingconcurrency.javaadv1.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedQueueV6_1 implements BoundedQueue {

    private final BlockingQueue<String> queue;

    public BoundedQueueV6_1(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        try {
            queue.put(data);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public String take() {
        try {
            return queue.take();
        } catch (InterruptedException ignored) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
