package com.practice;

import java.util.*;
import java.util.stream.Collectors;


public class WordSort {
    public static void main(String[] argv) {
        String[] counts = {
                "POINT,333858038",
                "NOT,4522732626",
                "INTO,1144226142",
                "ON,4594521081",
                "FOR,6545282031",
                "NOW,679337516",
                "ONE,2148983086",
                "BEHAVIOR,104177552",
                "WAITS,2911079",
                "PEOPLE,658716166",
                "HI,15453893",
                "FORM,352032932",
                "OF,30966074232",
                "THROUGH,647091198",
                "BETWEEN,744064796",
                "FOUR,262968583",
                "LEFT,306802162",
                "OFF,302535533",
                "FROM,3469207674",
                "NO,1400645478",
                "FORMS,136468034",
                "A,45916054218"
        };

        /* your function here */
        List<String[]> result = getList(counts, 15, 5);
        result.forEach(wordArr -> System.out.println(Arrays.toString(wordArr)));

        //find words one step away
        another(counts).forEach((k, v) -> {
            System.out.print(k + " => ");
            v.forEach(target -> System.out.print( " " + target));
            System.out.println();
        });
    }

    public static List<String[]> getList(String[] counts, int numberOfWords, int wordSize){
        List<String[]> result = Arrays.stream(counts).map(count -> count.split(","))
                .filter(wordArr -> (wordArr[0].length() <= wordSize && wordArr[0].length() >= 2))
                .sorted(Comparator.comparingLong(wordArr -> -Long.parseLong(wordArr[1])))
                .limit(numberOfWords)
                .collect(Collectors.toList());
        return result;
    }

    public static Map<String, List<String>> another(String[] counts){
        Map<String, List<String>> res = new HashMap<>();
        Map<Integer, List<String>> map = new HashMap<>();
        Arrays.stream(counts).map(record -> record.split(",")[0])
                .forEach(word -> {
                    if(!map.containsKey(word.length())){
                        map.put(word.length(), new ArrayList<>());
                    }
                    map.get(word.length()).add(word);
                });
        map.forEach((key, val) -> {
            val.forEach(source -> {
                res.put(source, new ArrayList<>());
                if(map.containsKey(key+1)){
                    map.get(key+1).forEach(target -> {
                        if(isStep(source, target)){
                            res.get(source).add(target);
                        }
                    });
                }
            });
        });
        return res;
    }

    public static boolean isStep(String source, String target){
        int[] cache = new int[26];
        for(int i = 0; i < source.length(); i++) cache[source.charAt(i) - 'A']++;
        for(int i = 0; i < target.length(); i++) cache[target.charAt(i) - 'A']--;
        boolean negativeOneFound = false;
        for(int val : cache){
            if(val > 0 || val < -1 || (negativeOneFound && val == -1)) return false;
            if(!negativeOneFound && val == -1) negativeOneFound = true;
        }
        return true;
    }
}