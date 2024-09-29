package mark.multithreadingconcurrency.javaadv1.thread.start;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.thread.start.HelloRunnableTest.*;
import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class ManyThreadTest {

    @Test
    public void test() {
        log("test() start");

        HelloRunnable runnable = new HelloRunnable();
        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

        log("test() end");
    }
}
