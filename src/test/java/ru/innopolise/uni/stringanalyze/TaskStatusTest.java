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

/*
    @Before
    public void before() {
        taskStatus = new TaskStatus(5);
    }*/

/*    @Test
    public void testGetCountTask() {
        logger.info("Test method getCountTask");
        int result = taskStatus.getCountTask();
        assertTrue("Count tasks", result == 5);
    }*/

    @Test
    public void testException() {
        logger.info("Test method isException and getException");

        taskStatus.setException(new Exception());
        boolean result = taskStatus.isException();
        Exception resultException = taskStatus.getException();

        assertTrue("Is exception", result);
        assertNotNull("Exception sets", resultException);
    }
/*
    @Test
    public void testCompleteTask() {
        logger.info("Test method completeTaskIncrement and getCompleteCount");

        taskStatus.completeTaskIncrement();
        int result = taskStatus.getCompleteCount();
        assertTrue("Complete tasks after incrementing", result == 1);
    }*/
}
