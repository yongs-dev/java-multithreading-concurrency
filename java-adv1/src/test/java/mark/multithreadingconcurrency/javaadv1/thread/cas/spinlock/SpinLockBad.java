package mark.multithreadingconcurrency.javaadv1.thread.cas.spinlock;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class SpinLockBad {

    volatile private boolean lock = false;

    // NOT CAS. 원자적이지 않음. 락 사용 여부 확인과 락의 값 변경 동시 사용
    public void lock() {
        log("락 획득 시도");

        while (true) {
            if (!lock) {            // 1. 락 사용 여부 확인
                sleep(100);   // 문제 상황 확인 용도. 스레드 대기
                lock = true;        // 2. 락의 값 변경(락 획득)
                break;
            } else {
                // 락을 획윽할 때 까지 스핀 대기(바쁜 대기) 한다.
                log("락 획득 실패 - 스핀 대기");
            }
        }
        
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false;   // 원자적연산. 여러 스레드가 동시에 실행해도 문제가 발생하지 않는다.
        log("락 반납 완료");
    }
}
