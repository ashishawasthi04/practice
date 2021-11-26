package com.practice;


import java.util.*;

public class TopKFrequent {
    //Input: nums = [1,1,1,2,2,3], k = 2
    //Output: [1,2]
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        topKFrequent(nums, 2);
    }

    private static int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(i -> {
            map.put(i, map.getOrDefault(i, 0) + 1);
        });
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.getValue()));
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            pq.offer(entry);
            if(pq.size() > k) pq.poll();
        }
        int i = 0;
        while(!pq.isEmpty()) res[i++] = pq.poll().getKey();
        System.out.println(Arrays.toString(res));
        return res;
    }
}


