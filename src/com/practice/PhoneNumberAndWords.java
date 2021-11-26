package com.practice;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberAndWords {
    int[] map = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
    public static void main(String[] args) {
        PhoneNumberAndWords ph = new PhoneNumberAndWords();
        String[] words = {"foo", "bar", "baz", "foobar", "emo", "cap", "car", "cat"};
        ph.solution("3662277", words);
    }

    public List<String> solution(String phoneNumber, String[] words) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(String word : words){
            for(char c : word.toCharArray()){
                sb.append(map[c - 'a']);
            }
            if(phoneNumber.contains(sb.toString())) res.add(word);
            System.out.println(sb.toString() + "   " + word);
            sb.setLength(0);
        }
        System.out.println(res);
        return res;
    }
}
