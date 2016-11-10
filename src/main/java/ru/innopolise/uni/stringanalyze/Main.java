package ru.innopolise.uni.stringanalyze;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

import static ru.innopolise.uni.stringanalyze.constants.Const.*;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        Map<String, Integer> sharedMap = new HashMap<>();

        TaskStatus taskStatus = new TaskStatus(args.length);

        for (int i = 0; i < args.length; i++) {
            new StreamThread(args[i], sharedMap, taskStatus).start();
        }


        try {
            while (!taskStatus.isComplete() && !taskStatus.isException()) {
                synchronized (sharedMap) {
                    sharedMap.wait();
                }
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
