package com.practice;

public class BasicRegExParser {
    public static void main(String[] args) {
        boolean test = isMatch("abc", "a.c");
        System.out.println(test);
    }

    public static boolean isMatch(String text, String pattern) {
        return isMatchHelper(text, pattern, 0, 0);
    }

    //  Input:
    //    text - the text to check,
    //    pattern - the regular expression to be checked,
    //    textIndex - the index the text is checked from
    //    patIndex -  the index the pattern is checked from
    //  Output:
    //   true if the text from the index textIndex matches
    //   the regular expression pattern from the pattern Index.
    //   E.g. isMatchHelper(“aaabb”,”cab*”,2, 1) since the text
    //   from index 2 (“abb”) matches the pattern from index 1 (“ab*”)

    public static boolean isMatchHelper(String text, String pattern, int textIndex, int patIndex) {
        // base cases - one of the indexes reached the end of text or pattern
        if (textIndex >= text.length()) {
            if (patIndex >= pattern.length()) {
                return true;
            } else {
                if ((patIndex + 1 < pattern.length()) && (pattern.charAt(patIndex + 1) == '*')) {
                    return isMatchHelper(text, pattern, textIndex, patIndex + 2);
                } else {
                    return false;
                }
            }
        } else if ((patIndex >= pattern.length()) && (textIndex < text.length())) {
            return false;
        }
        // string matching for character followed by '*'
        else if ((patIndex + 1 < pattern.length()) && (pattern.charAt(patIndex + 1) == '*')) {
            if ((pattern.charAt(patIndex) == '.') ||
                    (text.charAt(textIndex) == pattern.charAt(patIndex))) {
                return (isMatchHelper(text, pattern, textIndex, patIndex + 2) ||
                        isMatchHelper(text, pattern, textIndex + 1, patIndex));
            } else {
                return isMatchHelper(text, pattern, textIndex, patIndex + 2);
            }
        }
        // string matching for '.' or ordinary char.
        else if ((pattern.charAt(patIndex) == '.') ||
                (pattern.charAt(patIndex) == text.charAt(textIndex))) {
            return isMatchHelper(text, pattern, textIndex + 1, patIndex + 1);
        } else {
            return false;
        }
    }
}