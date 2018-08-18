package com.montimur;

import com.montimur.processor.StringProcessor;

import java.util.function.Function;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Function<Boolean, String> MESSAGE_FUNC = bool -> String.format("\t=> %s", String.valueOf(bool));

    public static void main(String...args) {

        if (args.length <= 0 || args[0] == null || args[0].isEmpty()) {
            System.out.println("No string provided.");
        } else {
            StringProcessor.getBuilder()
                    .setMessageFunction(MESSAGE_FUNC)
                    .buildProcessor()
                    .process(args[0]);
        }
    }
}
