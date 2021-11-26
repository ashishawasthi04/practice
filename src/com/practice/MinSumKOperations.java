package com.practice;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinSumKOperations {

    public static int minSum(List<Integer> num, int k) {
        //Removing biggest number and divind that by 2 would minimize the final sum
        //So PriorityQueue should store numbers in reverse order (Big -> Small)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(num);

        while (k > 0) {
            int temp = (int) Math.ceil((double) pq.poll() / 2);
            pq.add(temp);
            k--;
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

}
