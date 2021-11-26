package com.practice;

import java.util.Arrays;

public class MissingNumber {

    public static void main(String[] args) {
        //Find first missing non-negative number in the array
        int[] nums = {1, -3, 5, 2};
        int res = findMissingNumber(nums);
        System.out.println(res);
    }

    public static int findMissingNumber(int[] nums){
        if(nums == null || nums.length == 0) return 1;

        int i = 0;
        //Sort the array
        while(i < nums.length){
            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]){
                int num = nums[i];
                nums[i] = nums[num-1];
                nums[num-1] = num;
            }else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));

        i = 0;
        while(i < nums.length){
            if(nums[i] != i+1) return i+1;
            i++;
        }
        return i;
    }
}
