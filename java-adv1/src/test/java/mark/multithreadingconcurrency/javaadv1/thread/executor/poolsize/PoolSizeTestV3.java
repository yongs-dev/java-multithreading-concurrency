package mark.multithreadingconcurrency.javaadv1.thread.executor.poolsize;

import mark.multithreadingconcurrency.javaadv1.thread.executor.RunnableTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static mark.multithreadingconcurrency.javaadv1.thread.executor.ExecutorUtils.printState;
import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

/**
 * <ul>
 *     <p>캐시 스레드 풀 전략</p>
 *     <li><b>기본 스레드가 없고 대기 큐에 작업도 쌓이지 않는다.</b> 대신 작업 요청이 오면 초과 스레드로 작업을 바로 처리한다.</li>
 *     <li>초과 스레드는 60초간 생존하기 때문에 작업 수에 맞추어 적절한 수의 스레드가 재사용된다.</li>
 *     <li>작업 수에 맞추어 스레드 수가 변하기 때문에 착업의 처리 속도가 빠르고 CPU, 메모리를 매우 유연하게 사용할 수 있다.</li>
 *     <li>그러나 요청이 폭증하면 CPU 100%, 메모리 폭발</li>
 *         -> 시스템이 멈추는 장애가 발생할 수 있다.
 * </ul>
 */
public class PoolSizeTestV3 {

    @Test
    void poolSizeTest() {
//        ExecutorService es = Executors.newCachedThreadPool();
        ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS, new SynchronousQueue<>());

        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 4; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es);
        
        es.close();
        log("== shutdown 완료");
        printState(es);
    }
}
