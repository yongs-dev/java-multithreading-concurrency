package mark.multithreadingconcurrency.javaadv1.thread.bounded;

import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.Queue;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

@RequiredArgsConstructor
public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐가 가득 참, 생산자 대기");

            try {
                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[put] 생산자 깨어남");
            } catch (InterruptedException ignored) {}
        }

        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");
        notify();   // 대기 스레드, WAITING -> BLOCKED
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            try {
                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[take] 소비자 깨어남");
            } catch (InterruptedException ignored) {}
        }

        String data = queue.poll();
        log("[take] 소비자 데이터 획득, notify() 호출");
        notify();   // 대기 스레드, WAITING -> BLOCKED

        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
