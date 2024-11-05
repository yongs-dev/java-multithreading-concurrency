package mark.multithreadingconcurrency.javaadv1.thread.collection.java;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class ListTest {

    @Test
    void test() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        log("list = " + list);
    }
}
