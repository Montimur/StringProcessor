package com.montimur.processor;

public interface StringProcessor {

    void process(String input);

    static StringProcessorBuilder getBuilder() {
        return new CharSumProcessorBuilder();
    }
}
