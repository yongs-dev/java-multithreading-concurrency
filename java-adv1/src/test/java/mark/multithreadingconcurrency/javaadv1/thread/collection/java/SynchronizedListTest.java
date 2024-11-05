package mark.multithreadingconcurrency.javaadv1.thread.collection.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class SynchronizedListTest {

    @Test
    public void test() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("data1");
        list.add("data2");
        list.add("data3");
        log(list.getClass());
        log("list = " + list);
    }
}
