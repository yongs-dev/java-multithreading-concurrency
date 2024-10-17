package mark.multithreadingconcurrency.javaadv1.thread.bounded;

import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.Queue;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

@RequiredArgsConstructor
public class BoundedQueueV1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    @Override
    public synchronized void put(String data) {
        if (queue.size() == max) {
            log("[put] 큐가 가득 참. 버림: " + data);
            return;
        }

        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
