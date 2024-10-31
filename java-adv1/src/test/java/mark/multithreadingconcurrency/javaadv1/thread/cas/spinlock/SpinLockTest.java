package mark.multithreadingconcurrency.javaadv1.thread.cas.spinlock;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class SpinLockTest {

    @Test
    public void test() throws InterruptedException {
//        SpinLockBad spinLock = new SpinLockBad();
        SpinLock spinLock = new SpinLock();

        Runnable runnable = () -> {
            spinLock.lock();
            try {
                // critical section
                log("비즈니스 로직 실행");

                // 락을 기다리는 스레드가 BLOCKED, WAITING 상태로 빠지지 않고 RUNNABLE 상태로 락을 획득할 때 까지 while 문을 반복하는 문제가 있다.
                // 락을 기다리는 스레드가 CPU 자원을 계속 사용하며 대기하는 것이다.
                sleep(100);
            } finally {
                spinLock.unlock();
            }
        };

        Thread thread1 = new Thread(runnable, "Thread-1");
        Thread thread2 = new Thread(runnable, "Thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
