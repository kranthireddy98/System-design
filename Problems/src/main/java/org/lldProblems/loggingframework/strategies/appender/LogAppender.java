package org.lldProblems.loggingframework.strategies.appender;

import org.lldProblems.loggingframework.enitties.LogMessage;
import org.lldProblems.loggingframework.strategies.formatter.LogFormatter;

public interface LogAppender {

    void append(LogMessage logMessage);

    void close();
    LogFormatter getFormatter();
    void setFormater(LogFormatter formater);
}
