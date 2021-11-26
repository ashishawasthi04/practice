package com.practice;

public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        longestPalindrome("babad");
    }

    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i++){
            int len1 = expandAround(s, i, i);
            int len2 = expandAround(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > end - start + 1){
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }
        String res = s.substring(start, end+1);
        System.out.println(res);
        return res;
    }
    // A B B A
    // 1 2 3 4

    public static int expandAround(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right- left - 1;
    }

}
