package mark.multithreadingconcurrency.javaadv1.util;

import static mark.multithreadingconcurrency.javaadv1.util.MyLogger.log;

public abstract class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("Occurred Interrupt" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
