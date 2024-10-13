package mark.multithreadingconcurrency.javaadv1.thread.volatile_;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

/**
 * 결국 메모리 가시성 문제를 확실하게 해결하려면 <b>volatile</b> 키워드를 사용해야 한다. <br>
 * 실행 결과를 보면 2가지 사실을 확인할 수 있다. <br>
 * <ul>
 *     <li>main 스레드가 flag를 변경하는 시점에 work 스레드도 flag의 변경 값을 정확하게 확인할 수 있다.</li>
 *     <li>volatile을 적용하면 캐시 메모리가 아닌 메인 메모리에 항상 직접 접근하기 때문에 성능이 상대적으로 떨어진다.</li>
 *     - volatile 없는 경우 : 996713076. 약 10억 <br>
 *     - volatile 있는 경우 : 121222366. 약 1.2억 <br>
 *     - 둘을 비교하면 물리적으로 약 9배 성능 차이를 확인할 수 있다.
 * </ul>
 */
public class VolatileCountTest {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(1000);

        task.flag = false;
        log("flag = " + task.flag + ", count = " + task.count + " in main");
    }

    static class MyTask implements Runnable {
        boolean flag = true;
        long count;
//        volatile boolean flag = true;
//        volatile long count;

        @Override
        public void run() {
            while (flag) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count);
                }
            }

            log("flag = " + flag + ", count = " + count + ". 종료");
        }
    }
}
