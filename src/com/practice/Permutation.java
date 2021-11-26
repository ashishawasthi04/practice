package com.practice;

import java.util.*;
import java.util.stream.IntStream;

public class Permutation {
    Map<String, List<String>> cache = new HashMap<>();
    int hitCounter = 0;
    public static void main(String[] args) {
        Permutation obj = new Permutation();
        List<String> res = obj.perm(new StringBuilder("123456890"));
        res.forEach(System.out::println);
        System.out.println("Count: " + res.size());
        System.out.println("Total Hits: " + obj.hitCounter);
    }

    public List<String> perm(StringBuilder src){
        List<String> result = new ArrayList<>();
        int size = src.length();
        if(size ==1 ){
            result.add(src.toString());
            return result;
        }
        IntStream.range(0, size).forEach(i -> {
            StringBuilder remaining = new StringBuilder(src).deleteCharAt(i);
            List<String> tempList;
            String sortedRemStr = sortString(remaining.toString());
            if(!cache.containsKey(sortedRemStr)){
                tempList = perm(remaining);
                cache.put(sortedRemStr, tempList);
            }else {
                hitCounter++;
                tempList = cache.get(sortedRemStr);
            }
            result.addAll(append(src.charAt(i), new ArrayList<>(tempList)));
        });
        return result;
    }

    public List<String> append(char c, List<String> res){
        res.replaceAll(s -> c + s);
        return res;
    }

    public String sortString(String str){
        char[] sortedArr = str.toString().toCharArray();
        Arrays.sort(sortedArr);
        String sortedStr = new String(sortedArr);
        return sortedStr;
    }
}
