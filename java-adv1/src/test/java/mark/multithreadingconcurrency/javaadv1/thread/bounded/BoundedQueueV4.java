package mark.multithreadingconcurrency.javaadv1.thread.bounded;

import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

@RequiredArgsConstructor
public class BoundedQueueV4 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();              // synchronized에서 사용하는 객체 내부의 모니터 락이 아닌 ReentrantLock 락을 의미
    private final Condition condition = lock.newCondition();    // 스레드 대기 공간

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    @Override
    public void put(String data) {
        lock.lock();

        try {
            while (queue.size() == max) {
                log("[put] 큐가 가득 참, 생산자 대기");

                try {
                    condition.await();  // RUNNABLE -> WAITING, 락 반납
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException ignored) {}
            }

            queue.offer(data);
            log("[put] 생산자 데이터 저장, signal() 호출");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();

        try {
            while (queue.isEmpty()) {
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    condition.await(); // RUNNABLE -> WAITING, 락 반납
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException ignored) {}
            }

            String data = queue.poll();
            log("[take] 소비자 데이터 획득, signal() 호출");
            condition.signal();

            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
