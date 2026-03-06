package org.lldProblems.loggingframework.strategies.formatter;

import org.lldProblems.loggingframework.enitties.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
