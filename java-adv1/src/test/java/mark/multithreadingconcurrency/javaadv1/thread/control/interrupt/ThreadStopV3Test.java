package mark.multithreadingconcurrency.javaadv1.thread.control.interrupt;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class ThreadStopV3Test {

    @Test
    public void test() throws InterruptedException {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();     // work thread RUNNABLE

        sleep(10);    // main thread TIME_WAITING
        log("작업 중단 지시 thread.interrupt()");

        // 인터럽트가 발생하면 해당 스레드에 InterruptedException이 발생한다.
        thread.interrupt();

        // Interrupt를 발생시켜 true
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {   // Interrupt 상태 변경 없음
                log("작업 중");
            }

            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");

                // Interrupt 상태 체크 -> throw 발생 -> catch 블록
                Thread.sleep(1000);
                log("자원 정리 완료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 방생");
                // Interrupt 상태 변경
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }

            log("작업 종료");

        }
    }
}
