package mark.multithreadingconcurrency.javaadv1.thread.collection.java;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class MapTest {

    @Test
    void test() {
        Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(3, "data3");
        concurrentHashMap.put(2, "data2");
        concurrentHashMap.put(1, "data1");
        log("concurrentHashMap = " + concurrentHashMap);

        Map<Integer, String> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        concurrentSkipListMap.put(2, "data2");
        concurrentSkipListMap.put(3, "data3");
        concurrentSkipListMap.put(1, "data1");
        log("concurrentSkipListMap = " + concurrentSkipListMap);
    }
}
