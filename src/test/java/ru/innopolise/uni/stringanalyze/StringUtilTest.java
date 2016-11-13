package ru.innopolise.uni.stringanalyze;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by i.viktor on 11/11/2016.
 */
public class StringUtilTest {
    private static Logger logger = LoggerFactory.getLogger(StringUtilTest.class);
    private StringUtil stringUtil = new StringUtil();

    @Test
    public void testByForeignIsCyrillic() {
        logger.info("Test by foreign language");
        boolean result = stringUtil.isCyrillic("Hello");
        assertFalse("It is not cyrillic", result);
    }

    @Test
    public void testByRussianIsCyrillic() {
        logger.info("Test by russian word");
        boolean result = stringUtil.isCyrillic("Привет");
        assertTrue("It is cyrillic", result);
    }

    @Test
    public void testSaveWord() {
        logger.info("Test method saveWord");
        Map<String, Integer> map = new HashMap<>();
        map.put("first", 5);

        stringUtil.saveWords("first", map);
        stringUtil.saveWords("second", map);

        assertTrue("Check added word", map.get("first") == 6);
        assertTrue("Check added word", map.get("second") == 1);
    }
}
