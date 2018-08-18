package com.montimur.processor;

import java.util.function.Consumer;
import java.util.function.Function;

public interface StringProcessorBuilder {

    StringProcessorBuilder setChar(char character);

    StringProcessorBuilder setCharCount(int charCount);

    StringProcessorBuilder setRequiredSum(int requiredSum);

    StringProcessorBuilder setMessageFunction(Function<Boolean, String> function);

    StringProcessorBuilder setResultAction(Consumer<String> action);

    StringProcessor buildProcessor();
}
