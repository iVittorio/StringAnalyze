
import java.util.Map;

import static constants.Const.*;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class StringUtil {

    public static boolean isRussian(String s) {
        return s.matches(RUSSIAN_WORD_REGEX);
    }

    public static void saveWords(String s, Map<String, Integer> map) {
        synchronized (map) {
            String lowerCase = s.toLowerCase();
            if (map.containsKey(lowerCase))
                map.put(lowerCase, map.get(lowerCase) + 1);
            else map.put(lowerCase, 1);
        }

    }

    public static void printStatus(String s, Map<String, Integer> map) {
        synchronized (map) {
            System.out.println("\"" + s.toLowerCase() + "\", количество вхождений: " + map.get(s.toLowerCase()));
        }
    }


}

