package mark.multithreadingconcurrency.javaadv1.thread.control.join;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class JoinV4Test {
    
    @Test
    public void test() throws InterruptedException {
        log("Start");
        SumTask task1 = new SumTask(1, 50);

        Thread thread1 = new Thread(task1, "thread-1");

        thread1.start();

        // join(millis): 이때 스레드는 WAITING 아닌 TIMED_WAITING 상대가 된다.
        // 보통 무기한 대기하면 WAITING 상태가 되고 특정 시간 만큼만 대기하는 경우 TIMED_WAITING 상태가 된다.
        log("join(1000) - main 스레드가 thread1 종료까지 1초 대기");
        thread1.join(1000);
        log("main 스레드 대기 완료");

        log("task1 = " + task1.getResult());
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
