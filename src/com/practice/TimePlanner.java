package com.practice;

import java.util.Arrays;

class TimePlanner {
    static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int duration) {
        // your code goes here
        int[] output = new int[2];
        int i = 0, j = 0;
        while(i < slotsA.length && j < slotsB.length){
            if(isOverlap(slotsA[i], slotsB[j], duration)){
                output[0] = Math.max(slotsA[i][0], slotsB[j][0]);
                output[1] = output[0] + duration;
                return output;
            }
            else if(slotsA[i][1] < slotsB[j][1]) i++;
            else j++;
        }
        return new int[0];
    }

    static boolean isOverlap(int[] slotA, int[] slotB, int duration){
        int s = Math.max(slotA[0], slotB[0]);
        int e = Math.min(slotA[1], slotB[1]);
        if(e - s >= duration) return true;
        else return false;
    }

    public static void main(String[] args) {
//        input:  slotsA = [[10, 50], [60, 120], [140, 210]]
//        slotsB = [[0, 15], [60, 70]]
//        dur = 8
//        output: [60, 68]
//
//        input:  slotsA = [[10, 50], [60, 120], [140, 210]]
//        slotsB = [[0, 15], [60, 70]]
//        dur = 12
//        output: [] # since there is no common slot whose duration is 12
        int[][] slotsA = new int[][]{{10, 50}, {60, 120}, {140, 210}};
        int[][] slotsB = new int[][]{{0, 15}, {60, 70}};
        int duration = 8;
        int[] res = meetingPlanner(slotsA, slotsB, duration);
        System.out.println(Arrays.toString(res));
    }

}
