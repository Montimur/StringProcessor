package com.montimur;

import com.montimur.processor.StringProcessor;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testBuilder()
    {
        var builder = StringProcessor.getBuilder();
        assertNotNull(builder);
    }

    @Test(expected = NullPointerException.class)
    public void nullTest() {
        StringProcessor.getBuilder()
                .buildProcessor()
                .process(null);
    }

    @Test
    public void testTrueOne() {
        var result = new AtomicReference<String>();

        StringProcessor.getBuilder()
                .setResultAction(result::set)
                .buildProcessor()
                .process(("sdf!erdcx5ksdj!kjdsf!dkjfkd!10kjkdjs!d"));

        assertTrue(result.get().contains("true"));
    }
}
