
import java.util.Map;

import static constants.Const.*;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class StringUtil {

    /**
     * Checks if the string contains Cyrillic
     * @param s The string to check
     * @return true if the text letters of the Cyrillic, false if not
     */
    public static boolean isCyrillic(String s) {
        return s.matches(CYRILLIC_CHAR_REGEX);
    }

    /**
     * Save the string in the map. If the string already exist, it increases the count of occurrences by 1.
     * If the string doesn't exist, put the string and count equals 1 in the map. Method is synchronized by the map.
     * @param s the string for saving, converted in lowercase
     * @param map the map to save a string and count these lines
     */
    public static void saveWords(String s, Map<String, Integer> map) {
        synchronized (map) {
            String lowerCase = s.toLowerCase();
            if (map.containsKey(lowerCase))
                map.put(lowerCase, map.get(lowerCase) + 1);
            else map.put(lowerCase, 1);

            printStatus(lowerCase, map);
        }

    }

    private static void printStatus(String s, Map<String, Integer> map) {
        synchronized (map) {
            System.out.printf(STATUS_TEMPLATE, s.toLowerCase(), map.get(s.toLowerCase()));
        }
    }


}

