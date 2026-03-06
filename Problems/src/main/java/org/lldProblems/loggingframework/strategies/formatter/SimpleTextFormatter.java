package org.lldProblems.loggingframework.strategies.formatter;

import org.lldProblems.loggingframework.enitties.LogMessage;

import java.time.format.DateTimeFormatter;

public class SimpleTextFormatter implements LogFormatter{
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public String format(LogMessage logMessage){
        return String.format("%s [%s] %s - %s: %s\n",
                logMessage.getTimeStamp().format(DATE_TIME_FORMATTER),
                logMessage.getThreadName(),
                logMessage.getLoglevel(),
                logMessage.getLoggerName(),
                logMessage.getMessage());
    }
}
