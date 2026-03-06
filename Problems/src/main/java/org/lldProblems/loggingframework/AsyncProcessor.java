package org.lldProblems.loggingframework;

import org.lldProblems.loggingframework.enitties.LogMessage;
import org.lldProblems.loggingframework.strategies.appender.LogAppender;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class AsyncProcessor {
    private final ExecutorService executorService;


    public AsyncProcessor() {
        this.executorService = Executors.newSingleThreadExecutor(
                runnable -> {
                    Thread thread = new Thread(runnable, "AsyncLogProcessor");
                    thread.setDaemon(true);
                    return thread;
                }
        );
    }

    public void process(LogMessage message, List<LogAppender> appenders){
        if(executorService.isShutdown()){
            System.out.println("Logger is shut down. Cannot process log message.");
            return;
        }

        executorService.submit(() ->{
            for(LogAppender appender : appenders){
                appender.append(message);
            }
        });
    }

    public void stop(){
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                System.err.println("Logger executor did not terminate in the specified time.");
                // Forcibly shut down any still-running tasks.
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
