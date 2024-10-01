package mark.multithreadingconcurrency.javaadv1.thread.control;

public class CheckedExceptionTest {

    // 1. 가능
    public static void main(String[] args) throws Exception {}

    // 2. 불가능. Runnable Interface의 메서드 시그니처에 Throw 없어 불가.
    // 부모 메서드가 체크 예외를 던지지 않는 경우, 재정의된 자식 메서드도 체크 예외를 던질 수 없다.
    static class CheckedRunnable implements Runnable {

         @Override
         public void run() /* throws Exception */ {
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
    }
}
