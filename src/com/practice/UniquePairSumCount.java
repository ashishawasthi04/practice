package com.practice;

import java.util.HashMap;
import java.util.Map;

public class UniquePairSumCount {
    public static int pairCount(int[] input, int target){
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int num : input){
            int complement = target - num;
            if(map.containsKey(complement)){
                if((num == complement) && map.get(num) == 2) continue;
                if((num != complement) && map.containsKey(num)) continue;
                map.put(num, map.getOrDefault(num, 0)+1);
                System.out.println(complement + " => " + num);
                count++;
            }else {
                map.put(num, map.getOrDefault(num, 0)+1);
            }
        }
        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 6, 3, 0, 7, 6, 8};
        pairCount(input, 12);
    }
}
