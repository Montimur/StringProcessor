package com.montimur.processor;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.Function;

   class CharSumProcessorBuilder implements StringProcessorBuilder {

    private static final char DEFAULT_CHAR = '!';

    private static final int DEFAULT_CHAR_COUNT = 3, DEFAULT_SUM = 15;

    private static final Function<Boolean, String> DEFAULT_MESSAGE_FUNCTION = String::valueOf;

    private static final Consumer<String> DEFAULT_ACTION = System.out::println;

    private int charCount, requiredSum;
    private char character;
    private Function<Boolean, String> messageFunction;
    private Consumer<String> resultAction;

    public StringProcessorBuilder setChar(char character) {
        this.character = character;
        return this;
    }

    public StringProcessorBuilder setCharCount(int charCount) {
        this.charCount = charCount;
        return this;
    }

    public StringProcessorBuilder setRequiredSum(int requiredSum) {
        this.requiredSum = requiredSum;
        return this;
    }

    public StringProcessorBuilder setMessageFunction(Function<Boolean, String> function) {
        this.messageFunction = function;
        return this;
    }

    public StringProcessorBuilder setResultAction(Consumer<String> action) {
        this.resultAction = action;
        return this;
    }

    @Override
    public CharSumProcessor buildProcessor() {
        return new CharSumProcessor(getChar(character),
                getCount(charCount).orElse(DEFAULT_CHAR_COUNT),
                getCount(requiredSum).orElse(DEFAULT_SUM),
                Optional.ofNullable(messageFunction).orElse(DEFAULT_MESSAGE_FUNCTION),
                Optional.ofNullable(resultAction).orElse(DEFAULT_ACTION));
    }

    private static char getChar(char c) {
        return c == '\0' ? DEFAULT_CHAR : c;
    }

    private static OptionalInt getCount(int count) {
        if (count <= 0)
            return OptionalInt.empty();
        else
            return OptionalInt.of(count);
    }
}
