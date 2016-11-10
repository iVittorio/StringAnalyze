package ru.innopolise.uni.stringanalyze;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static ru.innopolise.uni.stringanalyze.constants.Const.*;

/**
 * Created by i.viktor on 04/11/2016.
 */
public class StreamThread extends Thread {
    private final String link;
    private final Map<String, Integer> sharedMap;
    private TaskStatus taskStatus;
    private Logger logger = LoggerFactory.getLogger(StreamThread.class);

    public StreamThread(String link, Map<String, Integer> sharedMap, TaskStatus taskStatus) {
        this.link = link;
        this.sharedMap = sharedMap;
        this.taskStatus = taskStatus;
    }


    @Override
    public void run() {
        startTask(link);
        completeTask();
    }

    public void startTask(String link) {
        ResourceStream resourceStream = new ResourceStream(link);

        try (Scanner scanner = new Scanner(resourceStream.getInputStream())) {
            scanner.useDelimiter(Pattern.compile(DELIMITER_REGEX));

            while (scanner.hasNext() && !taskStatus.isException() && !isInterrupted()) {
                String str = scanner.next();
                if (StringUtil.isCyrillic(str)) {
                    StringUtil.saveWords(str, sharedMap);
                } else {
                    taskStatus.setException(new RuntimeException(FIND_FOREIGN_LANG_MESSAGE + link));
                    logger.error(FIND_FOREIGN_LANG_MESSAGE + link);
                    completeTask();
                }
            }
        } catch (IOException e) {
            taskStatus.setException(new RuntimeException(NO_SUCH_FILE_MESSAGE + link, e));
            logger.error(NO_SUCH_FILE_MESSAGE + link, e);
        }

    }

    private void completeTask() {
        synchronized (sharedMap) {
            taskStatus.taskIncrement();
            if (taskStatus.isComplete()) {
                sharedMap.notifyAll();
            }
        }
    }
}
