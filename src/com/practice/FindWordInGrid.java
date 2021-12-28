package com.practice;

import java.util.*;

public class FindWordInGrid {
    public static void main(String[] args) {
        char[][] grid = {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'c', 'a', 't', 'n', 'i'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'c', 's', 'i', 'p', 't'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'o', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'c', 'k', 'i'}
        };
        String word = "catnip";
        int[][] ans = matchingWord(grid, word);
        if(ans != null){
            for(int[] point : ans) System.out.println(Arrays.toString(point));
        }


//        String[] words = new String[] { "cat", "baby", "dog", "bird", "car", "ax"};
//        String input = "tcabnihjs";
//        List<String> ans = findWords(words, input);
//        for(String w : ans) System.out.println(w);
    }

    //First question
    public static int[][] matchingWord(char[][] grid, String word) {
        int[][] ans = new int[word.length()][2];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++) {
                if (word.charAt(0) == grid[i][j]) {
                    ans[0] = new int[]{i, j};
                    if (rec(grid, word, 1, ans)) return ans;
                }
            }
        }
        return null;
    }

    public static boolean rec(char[][] grid, String word, int idx, int[][] ans){
        if(idx == word.length()) return true;
        int[] pos = ans[idx-1];
        if(pos[0] + 1 < grid.length && grid[pos[0] + 1][pos[1]] == word.charAt(idx)){
            ans[idx] = new int[]{pos[0] + 1, pos[1]};
            return rec(grid, word, idx + 1, ans);
        }
        if(pos[1] + 1 < grid[0].length && grid[pos[0]][pos[1] + 1] == word.charAt(idx)){
            ans[idx] = new int[]{pos[0], pos[1] + 1};
            return rec(grid, word, idx + 1, ans);
        }
        return false;
    }



    //Second Question
    public static List<String> findWords(String[] words, String input){
        List<String> ans = new ArrayList<>();
        int[] freq = new int[26];
        for(char c : input.toCharArray()) ++freq[c - 'a'];

        for(String word : words){
            if(rec(freq, word, 0)) ans.add(word);
        }
        return ans;
    }

    public static boolean rec(int[] freq, String word, int idx){
        if(idx == word.length()) return true;
        char c = word.charAt(idx);
        if(--freq[c - 'a'] < 0){
            ++freq[c - 'a'];
            return false;
        }
        if(!rec(freq, word, idx + 1)){
            ++freq[c - 'a'];
            return false;
        }
        return true;
    }
}
