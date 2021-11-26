package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelSum {
    /*
    formula:
    sum(A1)
    sum(A3:A7)
    sum(A5:I22, A10 :A17, A1)
    */
    public static void main(String[] args) {
        List<List<Integer>> data = new ArrayList<>();
        data.add(Arrays.asList(1, 2, 3, 4, 5));
        data.add(Arrays.asList(6, 7, 8, 9, 10));
        data.add(Arrays.asList(11, 12, 13, 14, 15));
        data.add(Arrays.asList(16, 17, 18, 19, 20));
        data.add(Arrays.asList(21, 22, 23, 24, 25));


        System.out.println(convertCellToNum("BA"));

        System.out.println(excelSum(data, "A1:B4"));
    }

    static int excelSum(List<List<Integer>> data, String formula){
        String[] tokens = formula.split(",");
        int[] sum = new int[1];
        Arrays.stream(tokens)
                .map(token -> token.trim().split(":"))
                .forEach(ranges -> {
                    if(ranges.length == 2){
                        sum[0] += sumBetweenRange(getPos(ranges[0]), getPos(ranges[1]), data);
                    }else {
                        int[] pos = getPos(ranges[0]);
                        sum[0] += sumBetweenRange(pos, pos, data);
                    }
                });
        return sum[0];
    }
    // data has only 5 rows
    //But range is A1: A10
    static int sumBetweenRange(int[] pos1, int[] pos2, List<List<Integer>> data){
        int row1 = pos1[0], col1 = pos1[1], row2 = pos2[0], col2 = pos2[1];
        int sum = 0;
        for(int rIdx = row1 - 1; rIdx <= row2 - 1; rIdx++){
            if(rIdx >= data.size()) break;
            for(int cIdx = col1 - 1; cIdx <= col2 - 1; cIdx++){
                if(data.get(rIdx) == null || cIdx >= data.get(rIdx).size()) break;
                Integer val = data.get(rIdx).get(cIdx);
                sum += val == null ? 0 : val;
            }
        }
        return sum;
    }

    static int[] getPos(String range){
        System.out.println("Range is: " + range);
        range = range.toUpperCase().trim();
        int i = range.length()-1;
        while(i >= 0){
            char c = range.charAt(i);
            if(c >= '0' && c <= '9') i--;
            else break;
        }
        String cell = range.substring(0, i+1);
        int row = Integer.parseInt(range.substring(i+1));
        int col = convertCellToNum(cell);
        return new int[]{row, col};
    }

    static int convertCellToNum(String cell){
        // 101 = 2^0*1 + 2^1*0 + 2^2 * 1
        //A => 1, Z => 26
        // AA => 26 + 1, AZ => 1*26^1 + 26*26^0
        // BA => 2*26 + 1*1
        int num = 0, counter = 1;
        int i = cell.length()-1;
        while(i >= 0){
            num += (cell.charAt(i) - 'A' + 1) * counter;
            counter *= 26;
            i--;
        }
        return num;
    }
}
