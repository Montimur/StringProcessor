package com.montimur;

import com.montimur.processor.StringProcessor;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final Supplier<AtomicReference<String>> resultSupplier = AtomicReference::new;

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
        var result = resultSupplier.get();

        StringProcessor.getBuilder()
                .setResultAction(result::set)
                .buildProcessor()
                .process(("sdf!erdcx5ksdj!kjdsf!dkjfkd!10kjkdjs!d"));

        assertTrue(result.get().contains("true"));
    }

    @Test
    public void testTrueTwo() {
        var result = resultSupplier.get();

        StringProcessor.getBuilder()
                .setMessageFunction(bool -> bool ? "Yes" : "No")
                .setResultAction(result::set)
                .setChar('#')
                .setCharCount(5)
                .setRequiredSum(42)
                .buildProcessor()
                .process("skdjflKJkWOH44kjsd#lkjd#LKJKJ#skdjrf#lskdjf#--2skdjfkjs");
        assertTrue(result.get().contains("Yes"));
    }

    @Test
    public void mainTest() {
        App.main("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void mainExceptionTest() {
        App.main("");
    }
}
