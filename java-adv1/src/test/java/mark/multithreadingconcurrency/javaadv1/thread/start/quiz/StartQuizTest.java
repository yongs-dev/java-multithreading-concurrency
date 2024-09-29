package mark.multithreadingconcurrency.javaadv1.thread.start.quiz;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class StartQuizTest {

    /**
     * Thread 클래스를 상속받는 클래스를 생성한 후 1부터 5까지의 숫자를 1초 간격으로 출력
     */
    @Test
    public void test1() throws InterruptedException {
        Thread startQuizOne = new CounterThread();
        startQuizOne.start();
        startQuizOne.join();
    }

    static class CounterThread extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    log("value: " + i);
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    /**
     * Runnable 클래스를 구현하는 클래스를 생성한 후 1부터 5까지의 숫자를 1초 간격으로 출력
     */
    @Test
    public void test2() throws InterruptedException {
//        Thread counterThread = new Thread(() -> {
//            for (int i = 1; i <= 5; i++) {
//                try {
//                    log("value: " + i);
//                    Thread.sleep(1000);
//                } catch (InterruptedException ignored) {}
//            }
//        }, "counter");

        Runnable counterRunnable = new CounterRunnable();
        Thread counterThread = new Thread(counterRunnable, "counter");
        counterThread.start();
        counterThread.join();
    }

    static class CounterRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    log("value: " + i);
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    /**
     * 1초마다 "A" 출력<br>
     * 0.5초마다 "B" 출력<br>
     */
    @Test
    public void test3() throws InterruptedException {
        Thread threadA = new Thread(new PrintRunnable("A", 1000), "Thread-A");
        Thread threadB = new Thread(new PrintRunnable("B", 500), "Thread-B");

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }

    @AllArgsConstructor
    static class PrintRunnable implements Runnable {
        private final String content;
        private final int sleep;

        @Override
        public void run() {
            while (true) {
                try {
                    log(content);
                    Thread.sleep(sleep);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}
