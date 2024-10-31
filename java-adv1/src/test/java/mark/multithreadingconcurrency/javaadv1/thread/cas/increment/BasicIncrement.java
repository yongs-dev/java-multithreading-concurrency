package mark.multithreadingconcurrency.javaadv1.thread.cas.increment;

public class BasicIncrement implements IncrementInteger {

    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
