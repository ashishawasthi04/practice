package com.practice;
public class Subsequences {
    public static void main(String[] args) {
        Subsequences ss = new Subsequences();
        ss.numDistinct("babgbag", "bag");
    }

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;
        if (s.length() == t.length()) {
            if (s.equals(t)) return 1;
            return 0;
        }
        Integer[][] dp = new Integer[s.length()][t.length()];
        return dis(s, 0, t, 0, dp);
    }

    private int dis(String s, int ss, String t, int ts, Integer[][] dp) {
        if (ts >= t.length()) return 1;
        if (ss >= s.length()) return 0;

        if (dp[ss][ts] != null) return dp[ss][ts];
        char c = t.charAt(ts);
        int res = 0;
        for (int i = ss; i < s.length(); i++) {
            if (s.charAt(i) != c) continue;
            res += dis(s, i+1, t, ts+1, dp);
        }

        dp[ss][ts] = res;
        return res;
    }
}