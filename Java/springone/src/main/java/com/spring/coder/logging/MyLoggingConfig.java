package com.spring.coder.logging;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLoggingConfig {
    private String rootLoggerLevel;
    private String printedLoggerLevel;

    public void setRootLoggerLevel(String rootLoggerLevel) {
        this.rootLoggerLevel = rootLoggerLevel;
    }

    public void setPrintedLoggerLevel(String printedLoggerLevel) {
        this.printedLoggerLevel = printedLoggerLevel;
    }

    public void initLogger() {
        // parse levels
        Level rootLevel = Level.parse(this.rootLoggerLevel);
        Level printedLevel = Level.parse(this.printedLoggerLevel);

        Logger applicationContextLogger = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());
        Logger loggerParent = applicationContextLogger.getParent();
        loggerParent.setLevel(rootLevel);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(printedLevel);
        consoleHandler.setFormatter(new SimpleFormatter());
        loggerParent.addHandler(consoleHandler);

    }
}
