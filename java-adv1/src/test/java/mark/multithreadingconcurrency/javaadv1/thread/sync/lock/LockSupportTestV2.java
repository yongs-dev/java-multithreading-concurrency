package mark.multithreadingconcurrency.javaadv1.thread.sync.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.LockSupport;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class LockSupportTestV2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        // 잠시 대기하여 Thread-1이 park 상태에 빠질 시간을 준다.
        sleep(1000);
        log("Thread-1 state: " + thread1.getState());
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.parkNanos(2000_000000);
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }

    @Test
    void test1() {
        Thread thread = new Thread(new CounterRunner(), "Thread-1");
        thread.start();

        log("start");

        sleep(1000);
        thread.interrupt();

        log("end");

    }

    static class CounterRunner implements Runnable {

        @Override
        public void run() {
            int count = 1;
            while (true) {
                count++;
                if (count % 100_000_000 == 0) {
                    log(count);
                }
            }
        }
    }
}
