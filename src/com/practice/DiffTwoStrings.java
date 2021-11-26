package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiffTwoStrings {
    public static void main(String[] args) {
        diffBetweenTwoStrings("ABCDEFG", "ABDFFGH");
        //["A", "B", "-C", "D", "-E", "F", "+F", "G", "+H"]
    }
    static String[] diffBetweenTwoStrings(String source, String target) {
        int length1 = target.length(), length2 = source.length();
        List<String>[][] dp = new ArrayList[length1+1][length2+1];

        int idx = 1;
        dp[0][0] = new ArrayList<>();
        while(idx <= length2){
            dp[0][idx] = new ArrayList<>(dp[0][idx-1]);
            dp[0][idx].add("-" + source.charAt(idx-1));
            idx++;
        }

        idx = 1;
        while(idx <= length1){
            dp[idx][0] = new ArrayList<>(dp[idx-1][0]);
            dp[idx][0].add("+" + target.charAt(idx-1));
            idx++;
        }

        for(int i = 1; i <= length1; i++){
            for(int j = 1; j <= length2; j++){
                List<String> res;
                if(source.charAt(j-1) != target.charAt(i-1)){
                    if(dp[i-1][j].size() < dp[i][j-1].size()){
                        res = new ArrayList<>(dp[i-1][j]);
                        res.add("+" + target.charAt(i-1));
                    }else {
                        res = new ArrayList<>(dp[i][j-1]);
                        res.add("-" + source.charAt(j-1));
                    }
                }else {
                    res = new ArrayList<>(dp[i-1][j-1]);
                    res.add("" + source.charAt(j-1));
                }
                dp[i][j] = res;
            }
        }
        List<String> ans = dp[length1][length2];
        String[] output = ans.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(output));
        return output;
    }
}
