/**
 * Created by i.viktor on 04/11/2016.
 */
public class TaskStatus {
    private volatile int completeCount = 0;
    private volatile boolean isException = false;
    private final int countTask;
    private volatile Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.isException = true;
        this.exception = exception;
    }

    public TaskStatus(int countTask) {
        this.countTask = countTask;
    }

    public boolean isException() {
        return isException;
    }

    public synchronized void taskIncrement() {
        completeCount++;
    }

    public synchronized boolean isComplete() {
        return countTask == completeCount;
    }
}
