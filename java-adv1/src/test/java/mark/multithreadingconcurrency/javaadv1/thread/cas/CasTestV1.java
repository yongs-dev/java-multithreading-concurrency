package mark.multithreadingconcurrency.javaadv1.thread.cas;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class CasTestV1 {

    @Test
    public void test() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        log("start value = " + atomicInteger.get());

        boolean result1 = atomicInteger.compareAndSet(0, 1);
        log("result1 = " + result1 + ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1);
        log("result2 = " + result2 + ", value = " + atomicInteger.get());
    }
}
