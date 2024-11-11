package mark.multithreadingconcurrency.javaadv1.thread.executor.poolsize;

import mark.multithreadingconcurrency.javaadv1.thread.executor.RunnableTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static mark.multithreadingconcurrency.javaadv1.thread.executor.ExecutorUtils.printState;
import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

/**
 * <ul>
 *     <p>고정 스레드풀 전략</p>
 *     <li>실행되는 <b>스레드 수가 고정</b>되어 있다.<b>(큐 사이즈는 무한이다.)</b></li>
 *     <li>큐에 10000건이 쌓여있는데, 고정 스레드 수가 10이고 각 스레드가 작업을 처리하는 데 1초가 걸린다면 모든 작업을 다 처리하는데 1000초가 걸린다.</li>
 *         -> 처리 속도보다 작업이 쌓이는 속도가 더 빠른 경우에는 문제가 된다.
 *     <li>결국 <b>서버 자원은 여유</b>가 있는데, <b>사용자만 점점 느려지는 문제가 발생</b>한 것이다.</li>
 * </ul>
 */
public class PoolSizeTestV2 {

    @Test
    void poolSizeTest() {
        ExecutorService es = Executors.newFixedThreadPool(2);
//        ExecutorService es = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("== shutdown 완료");
    }
}
