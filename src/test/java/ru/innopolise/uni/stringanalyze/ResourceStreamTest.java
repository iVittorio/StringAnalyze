package ru.innopolise.uni.stringanalyze;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by i.viktor on 11/11/2016.
 */
public class ResourceStreamTest {
    private Logger logger = LoggerFactory.getLogger(ResourceStreamTest.class);
    private ResourceStream resourceStream;

    @Test(expected = IOException.class)
    public void testGetInputStream() throws IOException {
        logger.info("Test get input stream");

        resourceStream = new ResourceStream("wrong url name");

        InputStream result = resourceStream.getInputStream();
    }


}
