package mark.multithreadingconcurrency.javaadv1.thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class LockSupportTestV1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        // 잠시 대기하여 Thread-1이 park 상태에 빠질 시간을 준다.
        sleep(1000);
        log("Thread-1 state: " + thread1.getState());

        // 1. unpark 사용
        log("main -> unpart(Thread-1)");
        LockSupport.unpark(thread1);

        // 2. interrupt() 사용
//        log("main -> thread1.interrupt()");
//        thread1.interrupt();
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
