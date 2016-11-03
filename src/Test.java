import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class Test {

    private static Map<String, Integer> wordMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        StringUtil stringUtil = new StringUtil();


//        FileInputStream in = new FileInputStream("./superMassiveText.txt");
        InputStream in = new URL("https://getfile.dokpub.com/yandex/get/https://yadi.sk/d/7_-c0eUZy42LE").openStream();

//        FileInputStream inputStream = new FileInputStream();


        Scanner scanner = new Scanner(in);
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
    }
}
