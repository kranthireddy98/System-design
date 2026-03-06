package org.lldProblems.loggingframework.strategies.appender;

import org.lldProblems.loggingframework.enitties.LogMessage;
import org.lldProblems.loggingframework.strategies.formatter.LogFormatter;
import org.lldProblems.loggingframework.strategies.formatter.SimpleTextFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender{

    private FileWriter writer;
    private LogFormatter formatter;

    public FileAppender(String filePath){
        this.formatter = new SimpleTextFormatter();
        try {
            this.writer = new FileWriter(filePath,true);
        }catch (Exception e){
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
        }
    }
    @Override
    public void append(LogMessage logMessage) {
        try {
            writer.write(formatter.format(logMessage) + "\n");
        }catch (IOException e){
            System.out.println("Failed to write logs to file, exception: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        }catch (IOException e){
            System.out.println("Failed to close logs file, exception: "+ e.getMessage());
        }
    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setFormater(LogFormatter formater) {
        this.formatter=formater;
    }
}
