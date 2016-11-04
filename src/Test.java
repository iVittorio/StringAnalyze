import java.io.*;
import java.util.*;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class Test {

    private static final Map<String, Integer> sharedMap = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        TaskStatus taskStatus = new TaskStatus(args.length);

        for (int i = 0; i < args.length; i++) {
            new StreamThread(args[i], sharedMap, taskStatus).start();
        }


        try {
            while (!taskStatus.isComplete() && !taskStatus.isException()) {
                synchronized (sharedMap) {
                    sharedMap.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!taskStatus.isException())
            printResult();

    }

    private static void printResult() {
        System.out.println("Результат работы программы:\n");

        for (Map.Entry<String, Integer> pair : sharedMap.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
    }
}
