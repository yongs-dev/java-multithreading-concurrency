package mark.multithreadingconcurrency.javaadv1.thread.sync;

import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

/**
 * <b>임계 영역(Critical Section)</b>
 * <ul>
 *     <li>여러 스레드가 동시에 접근하면 데이터 불일치나 예상치 못한 동작이 발생할 수 있는 위험하고 또 중요한 코드 부분을 뜻한다.</li>
 *     <li>여러 스레드가 동시에 접근해서는 안 되는 공유 자원을 접근하거나 수정하는 부분을 의미한다.</li>
 *     <li>참고로 Volatile 사용하지 않아도 Synchronized 안에서 접근하는 부분은 변수의 메모리 가시성 문제가 해결된다.</li>
 * </ul>
 *
 * <p>모든 객체(인스턴스)는 내부에 자신만의 락(Lock)을 가지고 있다.</p>
 * <ul>
 *     <li>모니터 락(Monitor Lock)이라고도 부른다.</li>
 *     <li>객체 내부에 있고 우리가 확인하기는 어렵다.</li>
 * </ul>
 *
 * <p>스레드가 Synchronized에 진입하려면 반드시 해당 인스턴스의 락이 있어야 한다.</p>
 * <ul>
 *     <li>락을 획득하는 순서는 보장되지 않는다.</li>
 * </ul>
 */
public class BankTest {

    @Test
    public void test() throws InterruptedException {
        BankAccount account = new BankAccountV1(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        sleep(500); // 검증 완료까지 잠시 대기
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());

        t1.join();
        t2.join();

        log("최종 잔액: " + account.getBalance());
    }

    @Test
    public void test_using_synchronized_method() throws InterruptedException {
        BankAccount account = new BankAccountV2(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        sleep(500); // 검증 완료까지 잠시 대기
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());

        t1.join();
        t2.join();

        log("최종 잔액: " + account.getBalance());
    }

    @Test
    public void test_using_synchronized_block() throws InterruptedException {
        BankAccount account = new BankAccountV3(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        sleep(500); // 검증 완료까지 잠시 대기
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());

        t1.join();
        t2.join();

        log("최종 잔액: " + account.getBalance());
    }
}
