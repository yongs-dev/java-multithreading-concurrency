package mark.multithreadingconcurrency.javaadv1.thread.executor.poolsize;

import mark.multithreadingconcurrency.javaadv1.thread.executor.RunnableTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static mark.multithreadingconcurrency.javaadv1.thread.executor.ExecutorUtils.printState;
import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

/**
 * <ul>
 *     <p>사용자 정의 풀 전략</p>
 *     <li>일반: CPU, 메모리 자원을 예칙할 수 있도록 <b>고정 크기의 스레드로</b> 서비스를 안정적으로 운영</li>
 *     <li>긴급: 사용자 요청이 증가하면 <b>긴급하게 스레드를 추가로 투입해</b> 작업을 빠르게 처리</li>
 *     <li>거절: 사용자 요청이 폭증해 긴급 대응도 어렵다면 <b>사용자의 요청을 거절</b>한다.</li>
 * </ul>
 */
public class PoolSizeTestV4 {

//    static final int TASK_SIZE = 1100; // 1. 일반 [task1100 -> [pool=100, active=100, queuedTasks=1000, completedTasks=0] ==> time: 11000
//    static final int TASK_SIZE = 1200; // 2. 긴급 [task1200 -> [pool=200, active=200, queuedTasks=1000, completedTasks=0] ==> time: 6000
    static final int TASK_SIZE = 1201; // 3. 거절 [task1200 -> [pool=200, active=200, queuedTasks=1000, completedTasks=0], [task12001 -> RejectedExecutionException]

    @Test
    void poolSizeTest() {
        // 100개의 기본 스레드 사용, 추가 긴급 대응 스레드 100개 사용(60초 생존 주기), 1000개의 작업이 큐에 대기 가능
        ExecutorService es = new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
        printState(es);

        long startMs = System.currentTimeMillis();
        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;

            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }

        }

        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));
    }
}
