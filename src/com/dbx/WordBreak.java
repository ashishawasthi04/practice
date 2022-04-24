package com.dbx;

import java.util.*;
//https://leetcode.com/problems/word-break/
public class WordBreak {
    Map<String, Boolean> map = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        if(map.containsKey(s)) return map.get(s);
        if(s.isEmpty()) return true;
        for(String word : wordDict){
            if(s.startsWith(word) && wordBreak(s.substring(word.length()), wordDict)){
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }
/*
Complexity Analysis - N is the length of the input string.
    - Time complexity : O(n^3)
    For every starting index, the search can continue till the end of the given string.
    - Space complexity : O(n). Queue of at most nn size is needed.
*/
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start]) {
                continue;
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDictSet.contains(s.substring(start, end))) {
                    queue.add(end);
                    if (end == s.length()) {
                        return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }
}
