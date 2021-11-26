package com.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MergeTimeSlots {
    static LinkedList<int[]> result = new LinkedList<>();
    public static void main(String[] args) {
        int[][] times = new int[][]{
                {2, 5},
                {1, 3},
                {6, 8},
                {11, 15},
                {12, 14},
                {13, 16}
        };
        merge(times);
    }

    public static void merge(int[][] times){
        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));
        Arrays.stream(times).forEach(a -> System.out.println(Arrays.toString(a)));

        result.add(times[0]);
        Arrays.stream(times).skip(1).forEach(MergeTimeSlots::overlap);
        System.out.println("Merged times are: ");
        result.forEach(item -> System.out.println(Arrays.toString(item)));
    }

    private static void overlap(int[] cur) {
        int[] prev = result.getLast();
        if(cur[0] <= prev[1]){
            prev[1] = Math.max(prev[1], cur[1]);
        }else {
            result.add(cur);
        }
    }
}
