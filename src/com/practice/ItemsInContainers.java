package com.practice;

import java.util.ArrayList;
import java.util.List;

public class ItemsInContainers {

    public static int solution(String s, List<Integer> start, List<Integer> end){
        int sum = 0;
        for(int i=0; i<start.size(); i++){
            sum += countItems(s, start.get(i)-1, end.get(i)-1);
        }
        System.out.println("Sum is: " + sum);
        return sum;
    }

    public static int countItems(String s, int start, int end){
        int sum = 0;
        int ps = -1, pe = -1;
        for(int i=start; i<= end; i++){
            if(s.charAt(i) == 124){
                if(ps == -1) ps = i;
                else {
                    pe = i;
                    sum += pe - ps -1;
                    ps = pe;
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        String s = "|*|***|*|*|";
        List<Integer> start = new ArrayList<>();
        start.add(1);
        //start.add(1);
        List<Integer> end = new ArrayList<>();
        //end.add(5);
        end.add(9);
        solution(s, start, end);
    }
}
