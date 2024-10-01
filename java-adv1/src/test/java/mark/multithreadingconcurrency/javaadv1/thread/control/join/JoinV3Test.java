package mark.multithreadingconcurrency.javaadv1.thread.control.join;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class JoinV3Test {
    
    @Test
    public void test() throws InterruptedException {
        log("Start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // join()을 호출하는 스레드는 대상 스레드가 종료(TERMINATED) 상태가 될 때 까지 대기한다. 대상 스레드가 TERMINATED 상태가 되면 호출 스레드는 다시 RUNNABLE 상태가 되면서 다음 코드를 수행한다.
        // join()은 다른 스레드가 완료될 때 까지 무기한 대기하는 단점이 있다.
        log("join() - main 스레드가 thread1, thread2 종료까지 대기");
        thread1.join();
        thread2.join();
        log("main 스레드 대기 완료");

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
