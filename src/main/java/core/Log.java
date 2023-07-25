package core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Log {

    private static Map<String, Integer> testLastStepNumberMap = new HashMap<>();

    private static Logger getLog() {
        return LogManager.getLogger(getCallingClassName());
    }

    public static void info(final String message) {
        getLog().info(message);
    }

    public static void warn(final String message) {
        getLog().warn(message);
    }

    public static void error(final String message) {
        getLog().error(message);
    }

    public static void debug(final String message) {
        getLog().debug(message);
    }

    private static String getCallingClassName() {
        StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
        String fullClassName = stackTrace[3].getClassName();
        return fullClassName.substring(fullClassName.lastIndexOf(46) + 1);
    }

    public static void resetLastStepNumberMap() {
        testLastStepNumberMap = new HashMap<>();
    }
}
