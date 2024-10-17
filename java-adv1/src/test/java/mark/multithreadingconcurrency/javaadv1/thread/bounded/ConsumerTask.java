package mark.multithreadingconcurrency.javaadv1.thread.bounded;

import lombok.AllArgsConstructor;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

@AllArgsConstructor
public class ConsumerTask implements Runnable {

    private BoundedQueue queue;

    @Override
    public void run() {
        log("[소비 시도]     ? <- " + queue);
        String data = queue.take();
        log("[소비 완료] " + data + " <- " + queue);
    }
}
