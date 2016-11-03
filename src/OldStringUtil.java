import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class OldStringUtil {


    private static Map<String, Integer> wordMap = new HashMap<>();

    public static void main(String[] args) {
        String line = "Однажды был случай в далеком Макао:" +
                "Макака коалу в какао макала," +
                "Коала какао лениво лакала," +
                "Макака макала, коала икала.";
        wordCounter(line);
    }

    public static void wordCounter(String str){
        String line = str.replaceAll("[\\pP]", " ").replaceAll("[\\s]{2,}", " "); //уточнить у куратора
        List<String> list = Arrays.asList(line.split(" "));

        System.out.println(line);

        for(String s : list){
            if(wordMap.containsKey(s))
                wordMap.put(s,wordMap.get(s)+1);
            else wordMap.put(s,1);
        }

        for (Map.Entry<String, Integer> pair:wordMap.entrySet()){
            System.out.println(pair.getKey() + " ||| " + pair.getValue());
        }
        System.out.println(line);
    }
}
