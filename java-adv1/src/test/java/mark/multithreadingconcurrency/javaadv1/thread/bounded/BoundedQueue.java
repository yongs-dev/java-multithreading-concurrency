package mark.multithreadingconcurrency.javaadv1.thread.bounded;

public interface BoundedQueue {
    void put(String data);

    String take();
}
