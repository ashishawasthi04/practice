package com.practice;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 Today is a busy day at Vancouver Airport. There are a lot of airplanes requesting to land but unfortunately,
 there is only 1 available runway for landing.
 As a result, a policy has been set to manage the landing aircraft:
 Landing requests are processed in the order they are received.
 A landing request is accepted if the runway is free, and the landing process can start immediately.
 Once a landing request is accepted, the flight takes some time to finish landing.
 A landing request is temporarily rejected if there is no free runaway available. The requesting aircraft is asked to
 request to land again after 10 minutes.
 If two airplanes request to land at the same time, the request from the one with the smaller flight number will be processed first.

 Your task is:
 Write a program that, given a list of the flight number + landing request timestamp + landing duration time, simulates
 the above process and generates a chronological report of when each flight is ACCEPTED, POSTPONED or has LANDED.

 Sample Input (flight number, landing request timestamp, landing duration time)
 377 45 10
 367 45 5
 357 50 5
 347 51 15

 Sample Output
 45 367 ACCEPTED
 45 377 POSTPONED
 50 367 LANDED
 50 357 ACCEPTED
 51 347 POSTPONED
 55 357 LANDED
 55 377 ACCEPTED
 61 347 POSTPONED
 65 377 LANDED
 71 347 ACCEPTED
 86 347 LANDED
 **/
class AirportLanding {
    public static void main(String[] args) {
        int[][] input = new int[4][3];
        int[] arr0 = {377, 45, 10};
        int[] arr1 = {367, 45, 5};
        int[] arr2 = {357, 50, 5};
        int[] arr3 = {347, 51, 15};
        input[0] = arr0; input[1] = arr1; input[2] = arr2; input[3] = arr3;
        airTrafficControl(input);
    }
    public static void airTrafficControl(int[][] input) {
        Comparator<int[]> comparator = Comparator.comparing(arr -> arr[1]);
        comparator = comparator.thenComparing(arr -> arr[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        for(int[] arr : input) pq.offer(arr);
        int[] inProcess = null;

        while(!pq.isEmpty()){
            int[] cur = pq.peek();
            if(inProcess == null){
                System.out.println(cur[1] + " " + cur[0] + " ACCEPTED");
                inProcess = pq.poll();
                continue;
            }
            if(inProcess[1] + inProcess[2] > cur[1]){
                System.out.println(cur[1] + " " + cur[0] + " POSTPONED");
                pq.poll();
                pq.offer(new int[]{cur[0], 10 + cur[1], cur[2]});
            }
            System.out.println((inProcess[1] + inProcess[2]) + " " + inProcess[0] + " LANDED");
            inProcess = null;
        }
        if(inProcess != null) {
            System.out.println((inProcess[1] + inProcess[2]) + " " + inProcess[0] + " LANDED");
        }
    }
}
