package com.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestTeamSize {

    public static void main(String[] args) {
        List<Integer> skills = Arrays.asList(3, 4, 3, 1, 6, 5);
        countMaxTeams(skills, 3, 2);
        // List<Integer> skills = Arrays.asList(9, 3, 5, 7, 1);
        // countMaxTeams(skills, 2, 1);
    }

    public static int countMaxTeams(List<Integer> skills, int teamSize, int maxDiff) {
        int maxTeams = 0, size = skills.size();
        skills.sort(Comparator.comparingInt(a -> a));
        int[] dp = new int[size];
        int start = teamSize - 1;

        for (int i = start; i < size; i++) {
            dp[i] = (i == start) ? 0 : dp[i - teamSize];
            if(skills.get(i) - skills.get(i - teamSize + 1) <= maxDiff){
                dp[i]++;
            }
            maxTeams = Math.max(dp[i], maxTeams);
        }
        System.out.println("Max Teams Count: " + maxTeams);
        return maxTeams;
    }
}
