package com.practice;

import java.util.Arrays;

public class SumDistinctValues {
    /*
    Add distinct values:
    Given array [7, 98, 22, 7, 22, 98, 22, 16, 45]
    Output: 188
    */
    public static void main(String[] args) {
        int[] arr = {7, 98, 22, 7, 22, 98, 22, 16, 45};
        System.out.println(sumDistinct(arr));
    }

    private static int sumDistinct(int[] arr){
        return Arrays.stream(arr).distinct().sum();
    }
}
