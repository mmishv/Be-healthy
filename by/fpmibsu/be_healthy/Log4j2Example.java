package by.fpmibsu.be_healthy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Example {

private static final Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        logger.info("Log4j2ExampleApp started.");
        logger.warn("Something to warn");
        logger.error("Something failed.");
        try {
            Files.readAllBytes(Paths.get("by/fpmibsu/resources/log4j2.xml"));
        } catch (IOException ioex) {
            logger.error("Error message", ioex);
        }
    }
}