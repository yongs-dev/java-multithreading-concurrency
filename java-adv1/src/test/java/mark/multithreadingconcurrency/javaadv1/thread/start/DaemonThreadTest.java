package mark.multithreadingconcurrency.javaadv1.thread.start;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 스레드는 사용자(user) 스레드와 데몬(daemon) 스레도 2가지 종류로 구분할 수 있다.<br><br>
 * 사용자 스레드(non-daemon 스레드)
 * <ul>
 * <li>프로그램의 주요 작업을 수행한다.</li>
 * <li>작업이 완료될 때까지 실행된다.</li>
 * <li>모든 user 스레드가 종료되면 JVM도 종료된다.</li>
 * </ul><br>
 *
 * 데몬 스레드(daemon 스레드)
 * <ul>
 * <li>백그라운드에서 보조적인 작업을 수행한다.</li>
 * <li>모든 user 스레드가 종료되면 데몬 스레드는 자동으로 종료된다.</li>
 * </ul>
 *
 */
@Slf4j
public class DaemonThreadTest {

    @Test
    public void test() {
        log.info("{} test() start", Thread.currentThread().getName());
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        log.info("{} test() end", Thread.currentThread().getName());
    }

    static class DaemonThread extends Thread {

        @Override
        public void run() {
            log.info("{} run start", Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}

            // 사용자 스레드가 종료되면 실행되지 않고 JVM 종료
            log.info("{} run end", Thread.currentThread().getName());
        }
    }
}
