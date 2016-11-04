import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class StringUtil {

    public static boolean isRussian(String s) {
        return s.matches("[А-Яа-я0-9_]+");
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

