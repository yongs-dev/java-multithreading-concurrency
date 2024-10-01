package mark.multithreadingconcurrency.javaadv1.thread.start;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 스레드를 사용할 떄는 Thread 클래스를 상속하기 보단 Runnable 인터페이스를 구현하자<br>
 * Java는 다중 상속이 안되고 인터페이스를 사용하는 방법에 비해 유연성이 떨어진다.<br>
 */
@Slf4j
public class HelloRunnableTest {

    @Test
    public void test() {
        log.info("{} test() start", Thread.currentThread().getName());

        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        log.info("{} test() end", Thread.currentThread().getName());
    }

    public static class HelloRunnable implements Runnable {

        @Override
        public void run() {
            log.info("{} runnable run()", Thread.currentThread().getName());
        }
    }
}
