package mark.multithreadingconcurrency.javaadv1.thread.collection.simple;

import mark.multithreadingconcurrency.javaadv1.thread.collection.simple.list.BasicList;
import mark.multithreadingconcurrency.javaadv1.thread.collection.simple.list.SimpleList;
import org.junit.jupiter.api.Test;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class SimpleListTestV1 {

    @Test
    public void test() {
        SimpleList list = new BasicList();
        list.add("A");
        list.add("B");
        log("list: " + list);
    }
}
