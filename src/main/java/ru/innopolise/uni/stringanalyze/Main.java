package ru.innopolise.uni.stringanalyze;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        Map<String, Integer> sharedMap = new ConcurrentHashMap<>();

        CountDownLatch latch = new CountDownLatch(args.length);

        TaskStatus taskStatus = new TaskStatus();

        taskStatus.setLatch(latch);

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < args.length; i++) {
            service.execute(new StreamThread(args[i], sharedMap, taskStatus));
        }

        service.shutdown();

        try {
            latch.await();
            long endTime = System.nanoTime();
            logger.info("The program was ended without error. Time of working: " + (endTime - startTime) +" ns");
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }


    }
}
