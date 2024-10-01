package mark.multithreadingconcurrency.javaadv1.thread.control.interrupt;

import lombok.Setter;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class ThreadStopV1Test {

    @Test
    public void test() throws InterruptedException {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();     // work thread RUNNABLE

        sleep(4000);    // main thread TIME_WAITING
        log("작업 중단 지시 runFlag = false");
        task.setRunFlag(false);
    }

    static class MyTask implements Runnable {

        // 변수를 사용하여 작업 종료를 지시하면 즉각적으로 중단되지 않는다. (loop sleep 2000)
        @Setter
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업 중");
                sleep(3000);
            }

            log("자원 정리");
            log("작업 종료");
        }
    }
}
