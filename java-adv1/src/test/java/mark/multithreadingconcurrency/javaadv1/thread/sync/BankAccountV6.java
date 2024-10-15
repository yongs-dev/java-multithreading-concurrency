package mark.multithreadingconcurrency.javaadv1.thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {

    private final Lock lock = new ReentrantLock();

    private int balance;

    public BankAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[진입 실패] 이미 처리 중인 작업이 있습니다.");
                return false;
            }
        } catch (InterruptedException ignored) {}

        try {
            log("[검증 시작] 총 금액: " + amount + ", 잔액: " + balance);
            // 잔고가 출금액 보다 적으면, 진행 불가
            if (balance < amount) {
                log("[검증 실패] 총 금액: " + amount + ", 잔액: " + balance);
                return false;
            }

            // 잔고가 출금액 보다 많으면, 진행
            log("[검증 완료] 총 금액: " + amount + ", 잔액: " + balance);
            sleep(1000);
            balance -= amount;
            log("[출금 완료] 총 금액: " + amount + ", 잔액: " + balance);
        } finally {
            // lock 후엔 반드시 finally를 이용해 unlock
            lock.unlock();
        }


        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
