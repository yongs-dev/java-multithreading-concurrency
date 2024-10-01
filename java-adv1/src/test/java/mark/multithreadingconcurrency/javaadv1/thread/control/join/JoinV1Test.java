package mark.multithreadingconcurrency.javaadv1.thread.control.join;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class JoinV1Test {
    
    @Test
    public void test() throws InterruptedException {
        log("Start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // task result 조회 시 0. => 아직 스레드 연산이 완료되기 전에 조회
        log("task1.result = " + task1.getResult());
        log("task2.result = " + task2.getResult());
        log("task1 + task2 = " + (task1.getResult() + task2.getResult()));
        log("end");
    }

    @RequiredArgsConstructor
    static class SumTask implements Runnable {
        private final Integer startValue;
        private final Integer endValue;

        @Getter
        private int result;

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            result = IntStream.rangeClosed(startValue, endValue).sum();
            log("작업 완료 result = " + result);
        }
    }
}
