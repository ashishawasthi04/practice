package com.practice;

public class CaesarCipher {

    public static String rotate(String input, int shift) {
        shift = shift >= 0 ? shift%26 : (26 + shift%26);
        StringBuilder sb = new StringBuilder();
        char[] charArr = input.toCharArray();
        for(char c : charArr){
            int start = Character.isUpperCase(c) ? 65 : 97;
            char e = (char) (start + (c + shift - start) % 26);
            sb.append(e);
        }
        System.out.println(input + " => " + sb.toString());
        return sb.toString();
    }
    public static void main(String[] args) {
        rotate("XYZ", 1);
        rotate("YZA", -1);
    }
}
