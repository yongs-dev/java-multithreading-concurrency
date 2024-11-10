package mark.multithreadingconcurrency.javaadv1.thread.executor.future;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class RunnableTest {

    @Test
    void runnableTest() throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();

        int result = task.value;
        log("result value = " + result);
    }

    static class MyRunnable implements Runnable {
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);

            value = new Random().nextInt(10);
            log("create value = " + value);
            log("Runnable 종료");
        }
    }
}
