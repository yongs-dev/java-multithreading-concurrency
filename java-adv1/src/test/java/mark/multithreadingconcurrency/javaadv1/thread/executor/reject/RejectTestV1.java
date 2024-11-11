package mark.multithreadingconcurrency.javaadv1.thread.executor.reject;

import mark.multithreadingconcurrency.javaadv1.thread.executor.RunnableTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class RejectTestV1 {

    @Test
    void rejectTest() {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        es.submit(new RunnableTask("task1"));

        try {
            es.submit(new RunnableTask("task2"));
        } catch (RejectedExecutionException e) {
            log("요청 초과. " + e);
        }

        es.close();
    }
}
