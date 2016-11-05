import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static constants.Const.*;

/**
 * Created by i.viktor on 04/11/2016.
 */
public class StreamThread extends Thread {
    private final String link;
    private final Map<String, Integer> sharedMap;
    private TaskStatus taskStatus;

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
                if (StringUtil.isRussian(str)) {
                    StringUtil.saveWords(str, sharedMap);
                    StringUtil.printStatus(str, sharedMap);
                } else {
                    taskStatus.setException(new RuntimeException(FIND_FOREIGN_LANG_MESSAGE));
                }
            }
        } catch (IOException e) {
            taskStatus.setException(new RuntimeException(NO_SUCH_FILE_MESSAGE, e));
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
