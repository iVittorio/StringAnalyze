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
    private final StringUtil stringUtil = new StringUtil();
    private final ResourceStream resourceStream;
    private TaskStatus taskStatus;
    private Logger logger = LoggerFactory.getLogger(StreamThread.class);
    private Scanner scanner;


    public StreamThread(String link, Map<String, Integer> sharedMap, TaskStatus taskStatus) {
        this.link = link;
        this.sharedMap = sharedMap;
        this.taskStatus = taskStatus;
        this.resourceStream = new ResourceStream(link);
    }


    @Override
    public void run() {
        startTask();
        completeTask();
    }

    /**
     * Starts task which reads words from URL or File, if throw IOException
     * writes to the TaskStatus. If text have doesn't cyrillic symbols creates
     * new RuntimeException with message "ОШИБКА! Текст содержит иннострные слова! [file_name]"
     * and writes to the  TaskStatus
     */
    public void startTask() {

        try (Scanner scanner = new Scanner(resourceStream.getInputStream())) {
            scanner.useDelimiter(Pattern.compile(DELIMITER_REGEX));

            while (scanner.hasNext() && !taskStatus.isException() && !isInterrupted()) {
                String str = scanner.next();
                if (stringUtil.isCyrillic(str)) {
                    stringUtil.saveWords(str, sharedMap);
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

    /**
     * Method notify main thread about complete task and increment completed count in object TaskStatus
     */
    private void completeTask() {
        synchronized (sharedMap) {
            taskStatus.completeTaskIncrement();
            if (taskStatus.isComplete()) {
                sharedMap.notifyAll();
            }
        }
    }
}
