package mark.multithreadingconcurrency.javaadv1.thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicIncrement implements IncrementInteger {

    volatile private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }
}
