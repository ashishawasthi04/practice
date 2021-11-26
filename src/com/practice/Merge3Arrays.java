package com.practice;

import java.util.ArrayList;
import java.util.List;

public class Merge3Arrays {
    /*
    A: [-100, -10, -1, -1, 0, 0, 0, 1, 1, 5]
    B: [ -90, -2, 0, 0, 0, 3, 5, 5]
    C: [-20, -1, 0, 0, 1, 4, 4]

    Output: [-100, -90, -20, -10, -2, -1, 0, 1, 3, 4, 5]
    */
    public static void main(String[] args) {
        int[] a = {-100, -10, -1, -1, 0, 0, 0, 1, 1, 5};
        int[] b = { -90, -2, 0, 0, 0, 3, 5, 5};
        int[] c = {-20, -1, 0, 0, 1, 4, 4};
        merge(a, b, c);
    }

    private static List<Integer> merge(int[] a, int[] b, int[] c){
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0, k = 0, min = Integer.MAX_VALUE;
        while(i < a.length || j < b.length || k < c.length) {
            if(i < a.length) min = a[i];
            if(j < b.length) min = Math.min(min, b[j]);
            if(k < c.length) min = Math.min(min, c[k]);
            res.add(min);
            while (i < a.length && a[i] == min) i++;
            while (j < b.length && b[j] == min) j++;
            while (k < c.length && c[k] == min) k++;
        }
        System.out.println(res);
        return res;
    }
}
