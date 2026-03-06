package org.lldProblems.loggingframework.enitties;

import org.lldProblems.loggingframework.enums.LogLevel;

import java.time.LocalDateTime;

public final class LogMessage {

    private final LocalDateTime timeStamp;
    private final LogLevel loglevel;
    private final String loggerName;
    private final String threadName;
    private final String message;


    public LogMessage(LogLevel loglevel, String loggerName,String message) {
        this.threadName = Thread.currentThread().getName();
        this.timeStamp = LocalDateTime.now();
        this.loglevel = loglevel;
        this.loggerName = loggerName;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public LogLevel getLoglevel() {
        return loglevel;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getMessage() {
        return message;
    }
}
