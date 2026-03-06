package org.lldProblems.loggingframework;

import org.lldProblems.loggingframework.enums.LogLevel;
import org.lldProblems.loggingframework.strategies.appender.ConsoleAppender;

public class LoggerApp {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getInstance();

        Logger rootLogger = logManager.getRootLogger();

        rootLogger.setLevel(LogLevel.INFO);

        rootLogger.addAppender(new ConsoleAppender());

        System.out.println("--- Initial Logging Demo ---");

        Logger mainLogger = logManager.getLogger("com.example.Main");
        mainLogger.info("Application starting up.");
        mainLogger.debug("This is a debug message, it should NOT appear.");
        mainLogger.warn("This is warning message.");

        System.out.println("--- Logger Hierarchy Demo");
        Logger dbLogger = logManager.getLogger("Com.example.db");

        dbLogger.info("Database connection pool initializing.");

        Logger servioeLogger = logManager.getLogger("com.example.service.UserService");
        servioeLogger.setLevel(LogLevel.DEBUG);
        servioeLogger.info("User service starting.");
        servioeLogger.debug("This debug message SHOULD now appear for the service logger.");

        System.out.println("--- Dynamic Configuration Demo ---");
        System.out.println("Changing root log level to DEBUG...");
        rootLogger.setLevel(LogLevel.DEBUG);
        mainLogger.debug("This debug message should now be visible");

        try {
            Thread.sleep(500);
            logManager.shutdown();
        }catch (Exception e){
            System.out.println("Caught exception");
        }
    }
}
