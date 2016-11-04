import java.io.*;
import java.util.*;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class Test {

    private static Map<String, Integer> sharedMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        TaskStatus taskStatus = new TaskStatus(5);

        Thread streamThread1 = new StreamThread("./superMassiveText.txt", sharedMap, taskStatus);

        Thread streamThread2 = new StreamThread("https://getfile.dokpub.com/yandex/get/https://yadi.sk/d/7_-c0eUZy42LE", sharedMap, taskStatus);

        Thread streamThread3 = new StreamThread("./superMassiveText.txt", sharedMap, taskStatus);

        Thread streamThread4 = new StreamThread("./superMassiveText.txt", sharedMap, taskStatus);

        Thread streamThread5 = new StreamThread("./superMassiveText.txt", sharedMap, taskStatus);


        streamThread1.start();
        streamThread2.start();
        streamThread3.start();
        streamThread4.start();
        streamThread5.start();


        try {
            while (!(taskStatus.getCompleteCount() == 5)) {
                synchronized (sharedMap) {
                    sharedMap.wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for (Map.Entry<String, Integer> pair : sharedMap.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }

/*        StringUtil stringUtil = new StringUtil();

        Date date = new Date();


        String url = "https://getfile.dokpub.com/yandex/get/https://yadi.sk/d/7_-c0eUZy42LE";
        String s = "./superMassiveText.txt";

        MyStream myStream = new MyStream(s);

        Scanner scanner = new Scanner(myStream.getInputStream());
        scanner.useDelimiter(Pattern.compile("[^А-Яа-яa-zA-Z0-9_]+"));

        while (scanner.hasNext()) {
            String str = scanner.next();
            if (stringUtil.isRussian(str)) {
                stringUtil.saveWords(str, wordMap);
                stringUtil.printStatus(str, wordMap);
            } else System.out.println(str + " - не русское слово");
        }
        scanner.close();

        for (Map.Entry<String, Integer> pair : wordMap.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }

        Date date1 = new Date();

        System.out.println(date1.getTime() - date.getTime());*/
    }
}
