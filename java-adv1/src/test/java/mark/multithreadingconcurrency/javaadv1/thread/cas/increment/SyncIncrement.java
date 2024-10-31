package mark.multithreadingconcurrency.javaadv1.thread.cas.increment;

public class SyncIncrement implements IncrementInteger {

    private int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
