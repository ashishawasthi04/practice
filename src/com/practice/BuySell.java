package com.practice;

import java.util.ArrayList;
import java.util.List;

//General purpose solution for K transactions
public class BuySell {
    public static void main(String[] args) {
        BuySell bs = new BuySell();
        bs.maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0});
    }

    public int maxProfit(int[] prices) {
        return maxProfit(prices, 2);
    }

    private int maxProfit(int[] prices, int transactions) {
        //buys and sells are money left while buying or selling stock
        int[] buys = new int[transactions + 1];
        int[] sells = new int[transactions + 1];

        for (int i = 1; i <= transactions; i++) buys[i] = Integer.MIN_VALUE;

        for (int price : prices) {
            for (int j = 1; j <= transactions; j++) {
                buys[j] = Math.max(buys[j], sells[j-1]-price);
                sells[j] = Math.max(sells[j], buys[j]+price);
            }
        }
        System.out.println(sells[transactions]);
        return sells[transactions];
    }
}
