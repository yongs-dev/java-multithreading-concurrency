package mark.multithreadingconcurrency.javaadv1.thread.cas.increment;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class IncrementPerformanceTest {

    public static final long COUNT = 100_000_000;

    @Test
    public void test() {
        perform(new BasicIncrement());
        perform(new VolatileIncrement());
        perform(new SyncIncrement());
        perform(new MyAtomicIncrement());
    }

    private void perform(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();

        log(incrementInteger.getClass().getSimpleName() + ": ms" + (endMs - startMs));
    }
}
