package com.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rider {
    /*
    Imagine that we receive power updates from our riders at random
    intervals, and that we can assume that their power output is constant until
    the next update.

    For example, this [(10, 100), (36, 120), (70, 80), (160, 105)] sequence would mean:

    After 10 seconds the rider started producing 100 watts of output,
    which continues until the 36 second mark and they increased their output to 120 watts,
    which continues until the 70 second mark and they decrease their output to 80 watts,
    which continues until the 153 second mark and they increase  their output to 105 watts,
    which continues until the ride ends

    We want to be able to compare the performance of a rider to a competitor, and the
    only thing we care about is the proportion of the time that they are beating the competitor
    in power output (it doesn't matter whether they are 1 watt higher or 100 watts higher).

    I.e. we want to be able to say something like:
    "Well done Yuki, your power output was higher than Sam's for 63% of that ride!"

    Write a function "percentage_better_than_competitor" that takes:
     - an iterable of  (time, power) pairs one for a rider
     - an iterable of  (time, power) pairs one for the competitor
     - the ending time of the of the ride (in seconds)

    Return the proportion of time that the rider is better than the baseline.

    E.g. percentage_better_than_competitor(
        [(100,70), (200, 80)],
        [(1, 75)],
        300)
    )

    should return 0.333 or 33.3% (the rider is only better between 200 and 300 seconds, out
    of 300 total seconds)

    percentage_better_than_competitor(
        [(10, 100), (36, 120), (70, 80), (160, 105)],
        [(5, 105), (30, 99), (60, 110), (120, 95)],
        300,
    )
    should produce 0.6 or 60%
    */
    public static void main(String[] args) {
//        int[][] riderRecords = new int[][]{{100,70}, {200, 80}};
//        int[][] compRecords = new int[][]{{1, 75}};
        int[][] riderRecords = new int[][]{{10, 100}, {36, 120}, {70, 80}, {160, 105}};
        int[][] compRecords = new int[][]{{5, 105}, {30, 99}, {60, 110}, {120, 95}};
        int time = 300;
        test(riderRecords, compRecords, time);
    }

    // TC: O(M+N) SC: O(M+N)
    public static double test(int[][] riders, int[][] comps, int time) {
        Map<Integer, int[]> map = new HashMap<>();
        Arrays.stream(riders).forEach(arr -> map.put(arr[0], new int[]{arr[1], 0}));
        Arrays.stream(comps).forEach(arr -> {
            if (!map.containsKey(arr[0])) map.put(arr[0], new int[]{0, arr[1]});
            else map.get(arr[0])[1] = arr[1];
        });
        //Prev1, Prev2, Prev1Or2, Res
        int[] meta = new int[]{0, 0, 0, 0};
        map.keySet().stream().sorted()
                .forEach(k -> {
                    meta[3] += meta[0] > meta[1] ? k - meta[2] : 0;
                    int[] values = map.get(k);
                    meta[0] = values[0] == 0 ? meta[0] : values[0];
                    meta[1] = values[1] == 0 ? meta[1] : values[1];
                    meta[2] = k;
                });
        meta[3] += meta[0] > meta[1] ? time - meta[2] : 0;
        System.out.println("Final Result: " + meta[3] * 1.0 / time);
        return meta[3] * 1.0 / time;
    }

    // [(10, 100), (36, 120), (70, 80), (160, 105)],
    // [(5, 105), (30, 99), (60, 110), (120, 95)],
    public static double testNew(int[][] riders, int[][] comps, int time) {
        int i = 0, j = 0, p = 0, pr = 0, pc = 0, res = 0;

        while (i < riders.length && j < comps.length) {
            if (riders[i][0] < comps[j][0]) {
                res += pr > pc ? riders[i][0] - p : 0;
                System.out.println(pr + "  " + pc + " ===> " + res);
                pr = riders[i][1];
                p = riders[i][0];
                i++;
            } else {
                res += pr > pc ? comps[j][0] - p : 0;
                System.out.println(pr + "  " + pc + " **=> " + res);
                pc = comps[j][1];
                p = comps[j][0];
                j++;
            }
        }
        if (i == riders.length) {
            while (j < comps.length) {
                res += pr > pc ? comps[j][0] - p : 0;
                System.out.println(pr + "  " + pc + " $$=> " + res);
                pc = comps[j][1];
                p = comps[j][0];
                j++;
            }
        } else if (j == comps.length) {
            while (i < riders.length) {
                res += pr > pc ? riders[i][0] - p : 0;
                System.out.println(pr + "  " + pc + " &&=> " + res);
                pr = riders[i][1];
                p = riders[i][0];
                i++;
            }
        }
        res += pr > pc ? time - p : 0;
        System.out.println(pr + "  " + pc + " ##=> " + res);
        System.out.println("Final Result: " + res * 1.0 / time);
        return res * 1.0 / time;
    }

}
