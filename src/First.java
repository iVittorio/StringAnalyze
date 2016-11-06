import java.io.*;
import java.util.*;

import static constants.Const.*;

/**
 * Created by i.viktor on 02/11/2016.
 */
public class First {

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
        else System.out.println(taskStatus.getException().getMessage());

    }

    private static void printResult() {
        System.out.println("\nРЕЗУЛЬТАТ РАБОТЫ ПРОГРАММЫ:\n");

        for (Map.Entry<String, Integer> pair : sharedMap.entrySet()) {
            System.out.printf(RESULT_TEMPLATE, pair.getKey(), pair.getValue());
        }
    }
}
