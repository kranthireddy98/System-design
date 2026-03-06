package org.lldProblems.loggingframework.strategies.appender;

import org.lldProblems.loggingframework.enitties.LogMessage;
import org.lldProblems.loggingframework.strategies.formatter.LogFormatter;
import org.lldProblems.loggingframework.strategies.formatter.SimpleTextFormatter;

public class ConsoleAppender implements LogAppender{

    private LogFormatter formatter;
    public ConsoleAppender(){
        this.formatter = new SimpleTextFormatter();
    }
    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }

    @Override
    public void close() {

    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setFormater(LogFormatter formater) {
        this.formatter = formater;

    }
}
