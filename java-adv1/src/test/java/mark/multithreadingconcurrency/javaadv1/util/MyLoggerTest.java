package mark.multithreadingconcurrency.javaadv1.util;

import org.junit.jupiter.api.Test;

public class MyLoggerTest {

    @Test
    public void test() {
        MyLogger.log("hello thread");
        MyLogger.log(100);
    }
}
