package mark.multithreadingconcurrency.javaadv1.thread.cas.increment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class IncrementThreadTest {

    public static final int THREAD_COUNT = 1000;

    @Test
    public void test() throws InterruptedException {
        increment(new BasicIncrement());
        increment(new VolatileIncrement());
        increment(new SyncIncrement());
        increment(new MyAtomicIncrement());
    }

    private static void increment(IncrementInteger incrementInteger) throws InterruptedException {

        Runnable runnable = () -> {
            sleep(10);
            incrementInteger.increment();
        };

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        log(incrementInteger.getClass().getSimpleName() + ": " + result);
    }
}
