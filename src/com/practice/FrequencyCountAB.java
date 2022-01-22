package com.practice;
/*
a = "1834"
b = "2845"
Output: 1A1B

a = "4552"
b = "5525"
Output: 1A2B
*/
public class FrequencyCountAB {
    public static void main(String[] args) {
        getFrequencyCount("1834", "2845");
        getFrequencyCount("4552", "5525");
    }
    public static String getFrequencyCount(String s1, String s2) {
        int[] freqA = new int[10];
        int[] freqB = new int[10];
        int countA = 0, countB = 0;
        for (int i = 0; i < s1.length(); i++) {
            int[] digits = new int[]{s1.charAt(i) - '0', s2.charAt(i) - '0'};

            if (digits[0] == digits[1]) {
                countA++;
                continue;
            }
            if (freqA[digits[0]] != -1) freqA[digits[0]]++;
            if (freqB[digits[1]] != -1) freqB[digits[1]]++;

            for(int d : digits){
                if (freqA[d] > 0 && freqB[d] > 0) {
                    freqA[d] = -1;
                    freqB[d] = -1;
                    countB++;
                }
            }
        }
        System.out.printf("%dA%dB%n", countA, countB);
        return String.format("%dA%dB", countA, countB);
    }
}
