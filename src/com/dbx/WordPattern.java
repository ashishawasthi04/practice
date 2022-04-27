package com.dbx;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://leetcode.com/problems/word-pattern/
public class WordPattern {
/*
    Complexity Analysis:
    Time complexity : O(N) where N represents the number of words in the s or the number of characters in the pattern.
    Space complexity : O(M) where M is the number of unique characters in pattern and words in s.
*/
    public boolean wordPattern(String pattern, String s) {
        Map<String, Integer> indexMap = new HashMap<>();
        String[] words = s.split("\\s");

        if (words.length != pattern.length())
            return false;

        for (int i = 0; i < words.length; i++) {
            String c = pattern.substring(i, i + 1);
            String w = words[i];

            if (!indexMap.containsKey(c))
                indexMap.put(c, i);

            if (!indexMap.containsKey(w))
                indexMap.put(w, i);

            if (Objects.equals(indexMap.get(c), indexMap.get(w)))
                return false;
        }
        return true;
    }
}
