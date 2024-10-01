package mark.multithreadingconcurrency.javaadv1.thread.control;

import mark.multithreadingconcurrency.javaadv1.thread.start.HelloRunnableTest;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

/**
 * <b>Priority</b><br>
 * 우선 순위는 1 ~ 10 까지의 값으로 설정할 수 있으며 기본 값은 5.<br>
 * 우선 순위가 높을수록 먼저 수행되지만 OS Scheduler, JVM 따라 항상 우선 수행되지 않음<br><br>
 *
 * <b>State</b>
 * <ol>
 *     <li>NEW</li>
 *     - 스레드가 생성되었으나 아직 시작되지 않은 상태<br>
 *     - Thread 객체가 생성되지만 start() 메서드가 호출되지 않은 상태<br>
 *     <li>RUNNABLE</li>
 *     - 스레드가 실행 중이거나 실행될 준비가 된 상태<br>
 *     - start() 메서드가 호출되면 스레드는 RUNNABLE 상태로 들어간다.<br>
 *     - RUNNABLE 상태에 있는 모든 스레드가 동시에 실행되는 것은 아니다. OS Scheduler가 각 스레드에 CPU 시간을 할당하여 실행하기 때문에<br>
 *       RUNNABLE 상태에 있는 스레드는 스케줄러의 실행 대기열에 포함되어 있다가 차례로 CPU에서 실행된다.<br>
 *     - 참고로 OS Scheduler의 실행 대기열에 있든, CPU에서 실제 실행되고 있든 모두 RUNNABLE 상태이다. JAVA에서 둘을 구분하여 확인할 수 없다.<br>
 *     <li>BLOCKED</li>
 *     - 스레드가 동기화 락을 기다리는 상태<br>
 *     - 예를 들어, synchronized 블록에 진입하기 위해 락을 얻어야 하는 경우 이 상태에 들어간다.
 *     <li>WAITING</li>
 *     - 스레드가 무기한으로 다른 스레드의 작업을 기다리는 상태<br>
 *     - wait(), join() 메서드가 호출될 때 이 상태가 된다.<br>
 *     - 스레드는 다른 스레드가 notify(), notifyAll() 메서드를 호출하거나 join()이 완료될 때까지 기다린다.<br>
 *     <li>TIMED_WAITING</li>
 *     - 스레드가 일정 시간 동안 다른 스레드의 작업을 기다리는 상태<br>
 *     - sleep(long millis), wait(long timeout), join(long millis) 메서드가 호출될 때 이 상태가 된다.<br>
 *     - 주어진 시간이 경과하거나 다른 스레드가 해당 스레드를 깨우면 이 상태에서 벗어난다.
 *     <li>TERMINATED</li>
 *     - 스레드의 실행이 완료된 상태<br>
 *     - 스레드가 정상적으로 종료되거나 예외가 발생하여 종료된 경우 이 상태로 들어간다.<br>
 *     - 스레드는 한 번 종료되면 다시 시작할 수 없다.
 * </ol>
 */
public class ThreadInfoTest {

    @Test
    public void test() {
        Thread currentThread = Thread.currentThread();
        log("currentThread = " + currentThread);
        log("currentThread.threadId() = " + currentThread.threadId());
        log("currentThread.getName() = " + currentThread.getName());
        log("currentThread.getPriority() = " + currentThread.getPriority());
        log("currentThread.getThreadGroup() = " + currentThread.getThreadGroup());
        log("currentThread.getState() = " + currentThread.getState());

        log("========================================================================");

        Thread helloThread = new Thread(new HelloRunnableTest.HelloRunnable());
        log("helloThread = " + helloThread);
        log("helloThread.threadId() = " + helloThread.threadId());
        log("helloThread.getName() = " + helloThread.getName());
        log("helloThread.getPriority() = " + helloThread.getPriority());
        log("helloThread.getThreadGroup() = " + helloThread.getThreadGroup());
        log("helloThread.getState() = " + helloThread.getState());
    }
}
