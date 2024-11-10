package mark.multithreadingconcurrency.javaadv1.thread.executor.quiz;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;
import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class OrderService {

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    @Test
    void orderTest() throws InterruptedException, ExecutionException {
        String orderNo = "orderNo_01";
        InventoryTask inventoryTask = new InventoryTask(orderNo);
        ShippingTask shippingTask = new ShippingTask(orderNo);
        AccountingTask accountingTask = new AccountingTask(orderNo);

        Future<Boolean> inventoryFuture = es.submit(inventoryTask);
        Future<Boolean> shippingFuture = es.submit(shippingTask);
        Future<Boolean> accountingFuture = es.submit(accountingTask);

        Boolean inventoryResult = inventoryFuture.get();
        Boolean shippingResult = shippingFuture.get();
        Boolean accountResult = accountingFuture.get();

        if (inventoryResult && shippingResult && accountResult) {
            log("모든 주문 처리가 성공적으로 완료되었습니다.");
        }

        es.close();
    }

    @RequiredArgsConstructor
    static class InventoryTask implements Callable<Boolean> {
        private final String orderNo;

        @Override
        public Boolean call() {
            log("재고 시스템 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    @RequiredArgsConstructor
    static class ShippingTask implements Callable<Boolean> {
        private final String orderNo;

        @Override
        public Boolean call() {
            log("배송 시스템 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    @RequiredArgsConstructor
    static class AccountingTask implements Callable<Boolean> {
        private final String orderNo;

        @Override
        public Boolean call() {
            log("회계 시스템 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
