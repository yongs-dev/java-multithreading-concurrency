package mark.multithreadingconcurrency.javaadv1.thread.collection.simple;

import mark.multithreadingconcurrency.javaadv1.thread.collection.simple.list.BasicList;
import mark.multithreadingconcurrency.javaadv1.thread.collection.simple.list.SimpleList;
import mark.multithreadingconcurrency.javaadv1.thread.collection.simple.list.SyncProxyList;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class SimpleListTestV2 {

    @Test
    public void test() throws InterruptedException {
//        run(new BasicList());
//        run(new SyncList());
        run(new SyncProxyList(new BasicList()));
    }

    private void run(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable addA = () -> {
            list.add("A");
            log("Thread-1: list.add(A)");
        };

        Runnable addB = () -> {
            list.add("B");
            log("Thread-2: list.add(B)");
        };

        Thread threadA = new Thread(addA, "Thread-1");
        Thread threadB = new Thread(addB, "Thread-2");

        threadA.start();
        threadB.start();
        threadB.join();
        threadA.join();

        log(list);
    }
}
