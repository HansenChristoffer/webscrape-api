package se.sogeti.webscraperapi;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeepAlive implements Runnable {

    private volatile boolean isRunning = true;
    private long sleepTime = 60;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Override
    public void run() {
        long i = 0;

        while (isRunning) {
            sleep(sleepTime);
            i = sleepTime * 2;
            LOGGER.info("Staying alive... [{}]", i);
        }
    }

    public void kill() {
        isRunning = false;
    }

    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
