package com.example.backend.common.util;

import jakarta.validation.constraints.NotNull;

public class StringUtil {

    private StringUtil() {
        // Private constructor to prevent instantiation
    }

    public static String replaceText(
            @NotNull String text,
            @NotNull String textToReplace,
            @NotNull String replacement) {
        return text.replace(textToReplace, replacement);
    }
}