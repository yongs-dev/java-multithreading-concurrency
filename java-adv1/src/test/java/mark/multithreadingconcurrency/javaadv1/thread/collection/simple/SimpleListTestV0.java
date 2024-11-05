package mark.multithreadingconcurrency.javaadv1.thread.collection.simple;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class SimpleListTestV0 {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        log("list: " + list);
    }
}
