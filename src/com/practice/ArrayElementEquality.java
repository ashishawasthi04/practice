package com.practice;

class ArrayElementEquality {
    /*
    Given a sorted array arr of distinct integers, write a function indexEqualsValueSearch that returns the
    lowest index i for which arr[i] == i. Return -1 if there is no such index. Analyze the time and space
    complexities of your solution and explain its correctness.

    Examples:
    input: arr = [-8,0,2,5]
    output: 2 # since arr[2] == 2

    input: arr = [-1,0,3,6]
    output: -1 # since no index in arr satisfies arr[i] == i.
    */
    static int indexEqualsValueSearch(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int left = 0, right = arr.length - 1;
        int ret = - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] == middle) {
                ret = arr[middle];
                right = middle - 1;
            } else if (arr[middle] < middle) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        System.out.println(ret);
        return ret;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{-6,-5,-4,-1,1,3,5,7};
        indexEqualsValueSearch(arr);
    }
}
