package mark.multithreadingconcurrency.javaadv1.thread.start;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class InnerRunnableV1Test {

    @Test
    public void test() {
        log("test() start");

        Runnable runnable = new InnerRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        log("test() end");
    }

    static class InnerRunnable implements Runnable {

        @Override
        public void run() {
            log(Thread.currentThread().getName() + " run()");
        }
    }

    @Test
    public void anonymousTest() {
        log("test() start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log(Thread.currentThread().getName() + " anonymous run()");
            }
        });
        thread.start();

        log("test() end");
    }

    @Test
    public void lambdaTest() {
        log("test() start");

        Thread thread = new Thread(() -> log(Thread.currentThread().getName() + " lambda run()"));
        thread.start();

        log("test() end");
    }
}
