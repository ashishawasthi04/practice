package com.practice;

public class PermutationAlt {
    long counter = 0;
    public static void main(String[] args) {
        var obj = new PermutationAlt();
        obj.perm("1234567890AB", "");
        System.out.println("Counter: " + obj.counter);
    }

    public void perm(String src, String pref) {
        int size = src.length();
        if (size == 0) counter++;
        for (int i = 0; i < size; i++) {
            String remaining = new StringBuilder(src).deleteCharAt(i).toString();
            var curChar = src.charAt(i);
            perm(remaining, pref + curChar);
        }
    }
}