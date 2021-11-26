package com.practice;

import java.util.*;
import java.util.stream.Collectors;

/*
Implement a document scanning function wordCountEngine, which receives a string document and returns a list of all
unique words in it and their number of occurrences, sorted by the number of occurrences in a descending order.
If two or more words have the same count, they should be sorted according to their order in the original sentence.
Assume that all letters are in english alphabet. You function should be case-insensitive, so for instance,
the words “Perfect” and “perfect” should be considered the same word.

The engine should strip out punctuation (even in the middle of a word) and use whitespaces to separate words.
 */
public class WordCountEngine {
    static List<String[]> wordCountEngine(String document) {
        // your code goes here
        Map<String, Integer> map = new LinkedHashMap<>();
        Arrays.stream(document.split(" ")).map(WordCountEngine::removeSpecialChars)
                .filter(word -> !word.isEmpty())
                .forEach(word -> map.merge(word, 1, Integer::sum));

        Comparator<Map.Entry<String, Integer>> comp = Comparator.comparingInt(entry -> -entry.getValue());
        // comp = Map.Entry.comparingByValue(Comparator.reverseOrder());
        List<String[]> output = map.entrySet().stream().sorted(comp)
                .map(entry -> new String[]{entry.getKey(), String.valueOf(entry.getValue())})
                .collect(Collectors.toList());

        output.forEach(item -> System.out.print(Arrays.toString(item) + " "));
        return output;
    }

    static String removeSpecialChars(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) sb.append(c);
        }
        return sb.toString().toLowerCase();
    }

    public static void main(String[] args) {
        String document = "Practice makes perfect. you'll only get Perfect by practice. !!!! just practice!";
        List<String[]> wordCount = wordCountEngine(document);
    }
}
