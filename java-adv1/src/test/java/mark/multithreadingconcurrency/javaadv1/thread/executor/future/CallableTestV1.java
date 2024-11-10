package mark.multithreadingconcurrency.javaadv1.thread.executor.future;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class CallableTestV1 {

    @Test
    void callableTest() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable());

        Integer result = future.get();
        log("result value = " + result);

        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            log("Callable 시작");
            sleep(2000);

            int value = new Random().nextInt(10);
            log("create value = " + value);

            log("Callable 완료");
            return value;
        }
    }
}
