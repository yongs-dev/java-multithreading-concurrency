package mark.multithreadingconcurrency.javaadv1.thread.collection.java;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public class SetTest {

    @Test
    void test() {
        Set<Integer> copySet = new CopyOnWriteArraySet<>();
        copySet.add(1);
        copySet.add(2);
        copySet.add(3);
        log("copySet = " + copySet);

        Set<Integer> skipSet = new ConcurrentSkipListSet<>();
        skipSet.add(1);
        skipSet.add(3);
        skipSet.add(2);
        log("skipSet = " + skipSet);
    }
}
