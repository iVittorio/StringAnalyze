package ru.innopolise.uni.stringanalyze;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by i.viktor on 11/11/2016.
 */
public class TaskStatusTest {
    private Logger logger = LoggerFactory.getLogger(TaskStatusTest.class);
    private TaskStatus taskStatus;

    @Before
    public void before() {
        taskStatus = new TaskStatus();
    }

    @Test
    public void testException() {
        logger.info("Test method isException and getException");

        taskStatus.setException(new Exception());
        boolean result = taskStatus.isException();
        Exception resultException = taskStatus.getException();

        assertTrue("Is exception", result);
        assertNotNull("Exception sets", resultException);
    }

}
