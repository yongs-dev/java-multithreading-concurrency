package mark.multithreadingconcurrency.javaadv1.thread.control.quiz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

@Slf4j
public class JoinTest {

    /**
     * 로그가 어떻게 출력될지 예측해 보자. 그리고 총 실행 시간이 얼마나 걸릴지 예측해 보자.<br>
     * <b>총 9초. 123 123 123 출력</b>
     */
    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
        t3.join();

        log("모든 스레드 실행 완료");
    }

    /**
     * 총 실행 시간을 3초로 앞당겨보자
     */
    @Test
    public void test2() throws InterruptedException {
        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        log("모든 스레드 실행 완료");
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}
