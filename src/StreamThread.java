import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static regex.RegexConst.*;

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

        MyStream myStream = new MyStream(link);

        try (Scanner scanner = new Scanner(myStream.getInputStream())) {
            scanner.useDelimiter(Pattern.compile(DELIMITER_REGEX));

            while (scanner.hasNext() && !taskStatus.isException() && !isInterrupted()) {
                String str = scanner.next();
                if (StringUtil.isRussian(str)) {
                    StringUtil.saveWords(str, sharedMap);
                    StringUtil.printStatus(str, sharedMap);
                } else {
                    System.out.println("ОШИБКА! Текст содержит иннострные слова!");
                    taskStatus.exception();
                }
            }
        } catch (IOException e) {
            System.out.println("Проблема с файлом");
        }
        notifyMain();
    }

    private void notifyMain() {
        synchronized (sharedMap) {
            taskStatus.taskIncrement();
            if (taskStatus.isComplete()) {
                sharedMap.notifyAll();
            }
        }
    }
}
