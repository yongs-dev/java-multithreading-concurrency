package mark.multithreadingconcurrency.javaadv1.thread.bounded;

import lombok.AllArgsConstructor;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

@AllArgsConstructor
public class ProducerTask implements Runnable {

    private BoundedQueue queue;
    private String request;

    @Override
    public void run() {
        log("[생산 시도] " + request + " -> " + queue);
        queue.put(request);
        log("[생산 완료] " + request + " -> " + queue);
    }
}
