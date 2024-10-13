package mark.multithreadingconcurrency.javaadv1.thread.sync.quiz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class QuizTest {

    /**
     * <b>공유 자원</b><br>
     * <p>
     *     다음 코드의 결과는 20000이 되어야 한다. <br>
     *     Counter 클래스 내부만 수정해야 한다. <br>
     * </p>
     * <p>Solution: increment() 메서드에 synchronized 키워드 추가</p>
     */
    @Test
    public void test1() throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        log("test1 결과: " + counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    /**
     * <b>지역 변수의 공유</b><br>
     * <p>
     *     다음 코드에서 MyTask의 run() 메서드는 두 스레드에서 동시에 실행된다. <br>
     *     다음 코드의 실행 결과를 예측하고 localValue 지역 변수에 동시성 문제가 발생 여부를 추측하자. <br>
     * </p>
     * <p>Solution: 지역 변수는 스레드의 독립적인 공간인 스택 메모리 영역에 할당되므로 동시성 문제가 발생하지 않는다.</p>
     */
    @Test
    public void test2() throws InterruptedException {
        MyCounter myCounter = new MyCounter();
        Runnable task = myCounter::count;

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    static class MyCounter {

        public void count() {
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue++;
            }
            log(" 결과: " + localValue);
        }
    }

    /**
     * <b>final 필드</b><br>
     * <p>다음에서 value 필드(멤버 변수)는 공유되는 값이다. 멀티 스레드 상황에서 문제가 될 수 있을까?</p>
     * <p>
     *     Solution: 여러 스레드가 공유 자원에 접근하는 것 자체는 사실 문제가 되지 않는다. <br>
     *     진짜 문제는 공유 자원을 사용하는 중간에 다른 스레드가 공유 자원의 값을 변경하기 때문에 발생한다. <br>
     * </p>
     */
    @Getter
    @RequiredArgsConstructor
    static class Immutable {
        private final int value;
    }
}
