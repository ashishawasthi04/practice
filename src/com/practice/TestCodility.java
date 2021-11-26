package com.practice;

import java.util.*;

public class TestCodility {
    public static void main(String[] args) {
        // 51, 71, 17, 42
        // 51, 32, 43
        int[] arr = {51, 32, 43};
        solution(arr);
    }

    public static int solution(int[] arr){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int num : arr){
            int digitSum = 0;
            int temp = num;
            while(temp > 0){
                digitSum += temp %10;
                temp /= 10;
            }
            if(!map.containsKey(digitSum)){
                map.put(digitSum, new ArrayList<>());
            }
            map.get(digitSum).add(num);
        }
        int[] maxSum = {-1};
        map.forEach((key, value) -> {
            if(value.size() >= 2){
                value.sort(Comparator.comparingInt(a -> -a));
                maxSum[0] = Math.max(maxSum[0], value.get(0) + value.get(1));
            }
        });
        System.out.println(maxSum[0]);
        return maxSum[0];
    }
}
