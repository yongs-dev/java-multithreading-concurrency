package mark.multithreadingconcurrency.javaadv1.thread.executor.reject;

import mark.multithreadingconcurrency.javaadv1.thread.executor.RunnableTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class RejectTestV4 {

    @Test
    void rejectTest() {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new MyRejectedExecutionHandler());
        es.submit(new RunnableTask("task1"));
        es.submit(new RunnableTask("task2"));
        es.submit(new RunnableTask("task3"));
        es.submit(new RunnableTask("task4"));

        es.close();
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        static AtomicInteger count = new AtomicInteger();

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log("[경고] 거절된 누적 작업 수 : " + count.incrementAndGet());
        }
    }
}
