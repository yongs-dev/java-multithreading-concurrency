package mark.multithreadingconcurrency.javaadv1.thread.cas;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class CasTestV2 {

    @Test
    public void singleThreadTest() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        log("start value = " + atomicInteger.get());

        // incrementAndGet 구현
        int resultValue1 = incrementAndGet(atomicInteger);
        log("resultValue1 = " + resultValue1);

        int resultValue2 = incrementAndGet(atomicInteger);
        log("resultValue2 = " + resultValue2);
    }

    private int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get();
            log("getValue = " + getValue);

            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result = " + result);
        } while (!result);

        return getValue + 1;
    }
}
