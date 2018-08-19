package com.montimur;

import com.montimur.processor.StringProcessor;

import java.util.Optional;
import java.util.function.Function;

public class App 
{
    private static final Function<Boolean, String> MESSAGE_FUNC = bool -> String.format("\t=> %s", String.valueOf(bool));

    public static void main(String...args) {
        var input = Optional.ofNullable(args)
                .filter(arr -> arr.length >= 1)
                .map(arr -> arr[0]);

        if (input.isPresent()) {
            StringProcessor.getBuilder()
                    .setMessageFunction(MESSAGE_FUNC)
                    .buildProcessor()
                    .process(input.get());
        } else {
            System.out.println(
                    "Invalid Parameter: A non-empty string must be provided!"
            );
        }
    }
}
