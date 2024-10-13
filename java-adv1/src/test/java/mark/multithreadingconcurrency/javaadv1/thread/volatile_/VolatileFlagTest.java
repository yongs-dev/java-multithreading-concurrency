package mark.multithreadingconcurrency.javaadv1.thread.volatile_;

import mark.multithreadingconcurrency.javaadv1.util.ThreadUtils;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

/**
 * <b>메모리 가시성(Memory Visibility)</b>
 * <p>
 *     멀티스레드 환경에서 한 스레드가 변경한 값이 다른 스레드에서 언제 보이는지에 대한 것을 메모리 가시성(Memory Visibility)이라 한다.
 * </p>
 * <p>
 *     스레드는 자신만의 CPU 캐시를 사용하여 메모리에 접근하고 변경할 수 있다. <br>
 *     그러나 각 스레드가 수정한 메모리 값이 메인 메모리에 즉시 반영되지 않고, 다른 스레드에서 그 변경 사항을 보지 못할 수 있다. <br>
 *     이를 "가시성 문제"라고 부르며, 특정 스레드가 다른 스레드가 수정한 메모리 값을 제대로 읽지 못하는 상황을 초래한다.
 * </p>
 * <p>
 *    캐시 메모리를 메인 메모리에 반영하거나, 메인 메모리의 변경 내역을 캐시 메모리에 다시 불러오는 것은 <br>
 *    CPU 설계 방식과 실행 환경에 따라 다를 수 있다. 즉시 반영될 수도 있고 몇 초 후에 될 수도 있고 평생 반영되지 않을 수도 있다. <br>
 *    주로 컨텍스트 스위칭이 될 때, 캐시 메모리도 함께 갱신되는데 이 부분도 환경에 따라 달라질 수 있다. <br>
 *    Thread.sleep(), 콘솔 출력 등을 할 때 스레드가 잠시 쉬는데 이럴 때 컨텍스트 스위칭이 되면서 주로 갱신된다. 하지만 이것이 갱신을 보장하진 않는다.
 * </p>
 */
public class VolatileFlagTest {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();

        ThreadUtils.sleep(1000);

        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");

        // Thread 종료 안 됨. MyTask loop 동작 중
    }

    static class MyTask implements Runnable {

        /**
         * 메모리 가시성 문제 해소<br>
         * 캐시 메모리를 사용하지 않고 항상 메인 메모리에 엑세스
         */
        volatile boolean runFlag = true;
//        boolean runFlag = true;   // 메모리 가시성 문제 발생


        @Override
        public void run() {
            log("task 시작");

            while (runFlag) {

            }

            log("task 종료");
        }
    }
}
