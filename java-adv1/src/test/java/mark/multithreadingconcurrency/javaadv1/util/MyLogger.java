package mark.multithreadingconcurrency.javaadv1.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class MyLogger {

    private static final DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object object) {
        String time = LocalTime.now().format(fomatter);
        String threadName = String.format("%9s", Thread.currentThread().getName());
        System.out.printf("%s [%s] %s \n", time, threadName, object);
    }
}
