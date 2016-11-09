package ru.innopolise.uni.stringanalyze;

/**
 * Created by i.viktor on 04/11/2016.
 */
public class TaskStatus {
    private volatile int completeCount = 0;
    private volatile boolean isException = false;
    private final int countTask;
    private volatile Exception exception;

    /**
     * @return exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * @param exception returns from thread exception and sets parameter isException = true
     */
    public void setException(Exception exception) {
        this.isException = true;
        this.exception = exception;
    }

    /**
     * Constructs a new ru.innopolise.uni.stringanalyze.TaskStatus with number tasks
     *
     * @param countTask numbers of parameters(files/sources) at the start program
     */
    public TaskStatus(int countTask) {
        this.countTask = countTask;
    }

    /**
     * @return true if the task has an error, false otherwise;
     */
    public boolean isException() {
        return isException;
    }

    /**
     * If the task is finished, increment completeCount parameter.
     */
    public synchronized void taskIncrement() {
        completeCount++;
    }

    /**
     * @return true if parameter countTask equals completeCount, false otherwise
     */
    public synchronized boolean isComplete() {
        return countTask == completeCount;
    }
}
