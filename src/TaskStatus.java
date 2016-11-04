/**
 * Created by i.viktor on 04/11/2016.
 */
public class TaskStatus {
    private int completeCount = 0;
    private boolean isException = false;
    private final int countTask;

    public TaskStatus(int countTask) {
        this.countTask = countTask;
    }

    public int getCompleteCount() {
        return completeCount;
    }

    public boolean isException() {
        return isException;
    }

    public void taskIncrement() {
        completeCount++;
    }

    public void exception() {
        isException = true;
    }
    public boolean isComplete(){
        return countTask == completeCount;
    }
}
