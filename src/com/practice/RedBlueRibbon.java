package com.practice;

import java.util.Arrays;

public class RedBlueRibbon {
    public static void main(String[] args) {
        //System.out.println(minRibbon("rrbbrbrrrbbrrbr", 3, 3, 0));
        System.out.println(minRibbonAlt("rrbbrbrrrbbrrbr", 3, 3));
    }

    public static int minRibbonAlt(String ribbon, int bands, int bandLength){
        int[] redSum = new int[ribbon.length() + 1];
        //Precalculate the RedSum
        for(int i = 1; i < redSum.length; i++){
            redSum[i] = redSum[i-1] + (ribbon.charAt(i-1) == 'r' ? 1 : 0);
        }

        int counter = 0;
        while(counter++ < bands){
            int maxIdx = 0, winDowRedSum;
            //Find the candidate index with max Reds
            for(int i = bandLength; i <= ribbon.length(); i++){
                winDowRedSum = redSum[i] - redSum[i - bands];
                if(redSum[maxIdx] < winDowRedSum) maxIdx = i;
            }
            //Find the Reds in band using maxIdx
            int max = redSum[maxIdx] - redSum[maxIdx - bands];
            //Set the maxIndex band with value same as last RedSum element right before this band
            Arrays.fill(redSum, maxIdx - bands + 1, maxIdx + 1, redSum[maxIdx - bands]);
            //Decrease all the RedSum element values by max to adjust the new RedSum array.
            while(++maxIdx < redSum.length) redSum[maxIdx] -= max;
        }
        //Last element in RedSum array is the total Reds left.
        return redSum[redSum.length - 1];
    }


    public static int minRibbon(String ribbon, int bands, int k, int prevRCount){
        if(bands == 0){
            return prevRCount + ribbon.replace("b", "").length();
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < ribbon.length() - k; i++){
            int temp = ribbon.substring(0, i).replace("b", "").length();
            ans = Math.min(ans, minRibbon(ribbon.substring(i + k), bands - 1, k, prevRCount + temp));
        }
        return ans;
    }
}
