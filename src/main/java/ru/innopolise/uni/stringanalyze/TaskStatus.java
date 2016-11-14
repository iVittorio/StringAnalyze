package ru.innopolise.uni.stringanalyze;

import java.util.concurrent.CountDownLatch;

/**
 * Created by i.viktor on 04/11/2016.
 */
public class TaskStatus {
    private volatile boolean isException = false;
    private volatile Exception exception;
    private CountDownLatch latch;

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

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
     * @return true if the task has an error, false otherwise;
     */
    public boolean isException() {
        return isException;
    }
}
