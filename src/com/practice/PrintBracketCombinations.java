package com.practice;

public class PrintBracketCombinations {

    public static void main(String[] args) {
        brackets("", 0, 0, 3);
    }

    public static void brackets(String output, int open, int close, int pairs) {
        if ((open == pairs) && (close == pairs)) {
            System.out.println(output);
        } else {
            if (open < pairs)
                brackets(output + "(", open + 1, close, pairs);
            if (close < open)
                brackets(output + ")", open, close + 1, pairs);
        }
    }
}
