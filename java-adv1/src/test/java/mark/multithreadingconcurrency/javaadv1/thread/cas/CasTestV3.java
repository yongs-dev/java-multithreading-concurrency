package mark.multithreadingconcurrency.javaadv1.thread.cas;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class CasTestV3 {

    private static final int THREAD_COUNT = 100;

    @Test
    public void multiThreadTest() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        log("start value = " + atomicInteger.get());

        Runnable runnable = () -> incrementAndGet(atomicInteger);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        log(atomicInteger.getClass().getSimpleName() + " value = " + result);
    }

    private int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get();
            log("getValue = " + getValue);
            
            // 스레드 동시 실행을 위한 대기
            sleep(10);

            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result = " + result);
        } while (!result);

        return getValue + 1;
    }
}
