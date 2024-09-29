package mark.multithreadingconcurrency.javaadv1.thread.start;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class HelloThreadTest {

    @Test
    public void test() {
        log.info("{} : test() start", HelloThread.currentThread().getName());

        HelloThread helloThread = new HelloThread();
        log.info("before start");
        // run 실행 시 별도 스레드에서 실행되는 게 아닌 현재 스레드에서 실행
        // helloThread.run();
        // 스레드는 항상 start로 실행
        helloThread.start();
        log.info("after start");

        log.info("{} : test() end", HelloThread.currentThread().getName());
    }

    static class HelloThread extends Thread {

        @Override
        public void run() {
            log.info("{} is running", Thread.currentThread().getName());
        }
    }
}
