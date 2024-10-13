package mark.multithreadingconcurrency.javaadv1.thread.control.yield;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

/**
 * <b>Yield</b>
 * <p>자바의 스레드가 RUNNABLE 상태일 때 운영체제의 스케줄링은 다음과 같은 상태들을 가질 수 있다.</p>
 * <ul>
 *     <li>실행 상태(Running): 스레드가 CPU에서 실제로 실행 중이다.</li>
 *     <li>실행 대기 상태(Read): 스레드가 실행될 준비가 되었지만 CPU가 바빠 스케줄링 큐에서 대기 중이다.</li>
 * </ul>
 * <p>참고로 자바에서는 두 상태를 구분할 수 없다.</p>
 */
@Slf4j
public class YieldTest {

    static final int THREAD_COUNT = 1000;

    @Test
    public void test() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                log.info("{} - {}", Thread.currentThread().getName(), i);
            }

            /*
             * 1. Empty : 특정 스레드가 쭉 수행된 다음에 다른 스레드가 수행되는 것을 확인할 수 있다.
             */

            /*
             * 2. sleep : 스레드의 상태를 sleep을 통해 RUNNABLE -> TIMED_WAITING 변경해 CPU 자원을 사용하지 않고 스케줄링에서 잠시 제외된다.
             * 하지만 이 방식은 RUNNABLE -> TIMED_WAITING -> RUNNABLE 변경되는 복잡한 과정을 거치고 또 특정 시간만큼 스레드가 실행되지 않는 단점이 있다.
             * 예를 들어 양보할 스레드가 없다면 차라리 나의 스레드를 더 실행하는 것이 나은 선택일 수 있다.
             * 이 방법은 나머지 스레드가 모두 대기 상태로 쉬고 있어도 내 스레드까지 잠낀 실행되지 않는 것이다.
             * sleep(1);
             */

            /*
             * 3. yield : 현재 실행 중인 스레드가 자발적으로 CPU를 양보하여 다른 스레드가 실행될 수 있도록 한다.
             * yield() 메서드를 호출한 스레드는 RUNNABLE 상태를 유지하면서 CPU를 양보한다.
             * 즉 이 스레드는 다시 스케줄링 큐에 들어가면서 다른 스레드에게 CPU 사용 기회를 넘긴다.
             * Thread.yield();
             */

        }
    }
}
