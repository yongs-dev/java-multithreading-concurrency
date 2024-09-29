package mark.multithreadingconcurrency.javaadv1.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public abstract class MyLogger {

    private static final DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object object) {
        String time = LocalTime.now().format(fomatter);
        String threadName = String.format("%9s", Thread.currentThread().getName());
        log.info("{} [{}] {}", time, threadName, object);
    }
}
