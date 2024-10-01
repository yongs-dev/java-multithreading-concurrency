package mark.multithreadingconcurrency.javaadv1.thread.control;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class ThreadStateTest {

    @Test
    public void test() {
        Thread myThread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + myThread.getState());    // NEW
        log("myThread.start()");
        myThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        log("myThread.state3 = " + myThread.getState());    // TIMED_WAIT

        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {}

        log("myThread.state5 = " + myThread.getState());    // TERMINATED
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            log("start");
            Thread myThread = Thread.currentThread();
            log("myThread.state2 = " + myThread.getState());    // RUNNABLE
            log("sleep() start");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {}

            log("sleep() end");
            log("myThread.state4 = " + myThread.getState());    // RUNNABLE
            log("end");
        }
    }
}
