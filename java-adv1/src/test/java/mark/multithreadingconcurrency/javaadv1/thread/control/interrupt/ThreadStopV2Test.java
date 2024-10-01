package mark.multithreadingconcurrency.javaadv1.thread.control.interrupt;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class ThreadStopV2Test {

    @Test
    public void test() throws InterruptedException {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();     // work thread RUNNABLE

        sleep(4000);    // main thread TIME_WAITING
        log("작업 중단 지시 thread.interrupt()");

        // 인터럽트가 발생하면 해당 스레드에 InterruptedException이 발생한다.
        thread.interrupt();

        // Interrupt를 발생시켜 true
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            /*
             * 인터럽트를 받은 스레드는 대기 상태에서 깨어나 RUNNABLE 상태가 되고 코드를 정상 수행한다.
             * 이때 InterruptedException을 catch로 잡아 정상 흐름으로 변경하면 된다.
             */
            try {
                /*
                 * interrupt()를 호출했다고 해서 즉각 InterruptedException이 발생하지 않는다.
                 * 오직 sleep()처럼 InterruptedException을 던지는 메서드를 호출하거나 호출 중일 때 예외가 발생한다.
                 */
                while (true) {                      // -> Interrupted 체크 안 함
                    log("작업 중");            // -> Interrupted 체크 안 함
                    Thread.sleep(3000);       // -> InterruptedException 발생
                }
            } catch (InterruptedException e) {
                // Interrupt로 깨어난 이후라 false
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message = " + e.getMessage());
                // RUNNABLE 상태여야 코드가 정상 수행된다.
                log("state = " + Thread.currentThread().getState());
            }

            log("자원 정리");
            log("작업 종료");
        }
    }
}
