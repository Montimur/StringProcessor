package com.montimur.processor;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

final class CharSumProcessor implements StringProcessor {

    private static final Pattern GROUPING_PATTERN = Pattern.compile("(-?\\d+[\\D]+-?\\d+)");

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^(-?\\d+)\\D+?(-?\\d+)$");



    private static final Predicate<String> NOT_EMPTY = ((Predicate<String>) String::isEmpty).negate();

    private final int sum, charCount;

    private final char character;

    private final Function<Boolean, String> messageFunction;

    private final Consumer<String> resultAction;

    private String input;

    CharSumProcessor(char character, int charCount, int requiredSum, Function<Boolean, String> messageFunction, Consumer<String> resultAction) {
        this.character = character;
        this.sum = requiredSum;
        this.charCount = charCount;
        this.messageFunction = messageFunction;
        this.resultAction = resultAction;
    }

    @Override
    public void process(String string) {
        this.input = Objects.requireNonNull(string);
        determineTrueOrFalse(GROUPING_PATTERN.matcher(input));
    }

    private void determineTrueOrFalse(Matcher matcher) {
        var index = new AtomicInteger(0);
        var result = Stream.generate(() -> supplyGroups(matcher, index))
                .takeWhile(NOT_EMPTY)
                .filter(group -> validateGroup(INTEGER_PATTERN.matcher(group)))
                .allMatch(this::hasRequiredCharCount);
        resultAction.accept(input + messageFunction.apply(result));
    }

    private String supplyGroups(Matcher matcher, AtomicInteger index) {
        if (matcher.find(index.get())) {
            var result = matcher.group();
            index.set(matcher.start() + calculateOffset(result));
            return result;
        } else {
            return "";
        }
    }

    private int calculateOffset(String result) {
        int offset = result.length();
        for (int i = offset - 1; i > 0; i--) {
            if (Character.isDigit(result.charAt(i))) {
                offset -= result.charAt(i - 1) == '-' ? 2 : 1;
            } else {
                break;
            }
        }
        return offset;
    }

    private boolean validateGroup(Matcher groupMatcher) {
        return groupMatcher.matches() && hasRequiredSum(groupMatcher.group(1), groupMatcher.group(2));
    }

    private boolean hasRequiredSum(String num0, String num1) {
        return Integer.parseInt(num0) + Integer.parseInt(num1) == sum;
    }

    private boolean hasRequiredCharCount(String group) {
        int count = 0;
        for (char c : group.toCharArray()) {
            if (c == character)
                count++;
        }
        return count == charCount;
    }

}