package com.practice;

import java.util.Stack;

class RPNCalculator {
    public static void main(String[] args) {
        System.out.println(calculateRPN("3 5 + 2 * 7 -"));
        System.out.println(calculateRPN("3 5 +"));
        System.out.println(calculateRPN("3 5"));
        System.out.println(calculateRPN("3"));
        System.out.println(calculateRPN("1 0 /"));
    }

    public static Integer calculateRPN(String str) {
        try {
            return calculateRPNString(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer calculateRPNString(String str) {
        if (str == null || str.trim().length() == 0) return null;

        Stack<Integer> st = new Stack<>();
        String[] strArr = str.trim().split("\\s+");

        for (String item : strArr) {
            //Check if item is operator
            if (isOperator(item)) {
                if (st.size() < 2) return null;
                int res = calculate(st.pop(), st.pop(), item);
                st.push(res);
            } else {
                //If item is operand
                int num = Integer.parseInt(item);
                st.push(num);
            }
        }
        return st.size() == 1 ? st.pop() : null;
    }

    public static int calculate(int num2, int num1, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        return 0;
    }

    public static boolean isOperator(String item) {
        if (item.length() == 1 && "+-*/".contains(item)) {
            return true;
        }
        return false;
    }
}

/*
if str != null && str.trim().length() == 0

split the string by space

Store that into array
iterate over the array
  - For each item
    decide if item is operator or number
      if operator
      pop from stack, compute number then push back to the stack
    if item starts with "-" and length() > 1
      then exclude -ve and parse rest of the item into  integer then multiply by -1
      convert that into int
      push to the stack

At the end of the processing if there's more than one number in the stack
  return false;

/*


*/
/*
Your previous Python 3 content is preserved below:

# Hello!
#
# We'd like you to create a Reverse Polish Notation (RPN) calculator.
#
# RPN is a way to unambiguously write math expressions without using parentheses.
# For example, the following is an example RPN input string:
#
#    3 5 + 2 * 7 -
#
# The way the calculator works:
#   - Numbers and operators are separated by whitespace
#   - Each number is pushed onto the stack
#   - Each operator (+,-,*,/) pops two operand values off the stack, applies the
#     operator to the operands, and pushes the result back onto the stack.
#
# In the example above:
#
#           3 5 + 2 * 7 -
#      loc: ^              stack: <empty>
#
# 1) value is '3', which we push onto the stack:
#
#           3 5 + 2 * 7 -
#      loc:   ^            stack: 3
#
# 2) value is '5', which we push onto the stack:
#
#           3 5 + 2 * 7 -
#      loc:     ^          stack: 3 5
#
# 3) value is operator '+'.  Pop operands off stack, add them, push result onto
#    the stack:
#
#           3 5 + 2 * 7 -
#      loc:       ^        stack: 8
#
# 4) value is '2', which we push onto the stack:
#
#           3 5 + 2 * 7 -
#      loc:         ^      stack: 8 2
#
# 5) value is operator '*'.  Pop operands off the stack, multiply them, push
#    result onto the stack:
#
#           3 5 + 2 * 7 -
#      loc:           ^    stack: 16
#
# 6) value is '7', which we push onto the stack:
#
#           3 5 + 2 * 7 -
#      loc:             ^  stack: 16 7
#
# 5) value is operator '-'.  Pop operands off the stack, subtract them, push
#    result onto the stack.  The operator order matters here.  We subtract the
#    top of the stack from the next item.  Here, result = 16 - 7
#
#           3 5 + 2 * 7 -
#      loc:              ^ stack: 9
#
# The result is on the top of the stack: 9.
#
# Please write a function that takes an RPN string and returns a result or
# None if the string is not a valid RPN string.
#
# - Numbers will always be integers, and may be negative.
#
# - The stack of a valid RPN string must have exactly one value at the end
#   of the string.
#
#   For example: "3" and "3 5 +" are valid.  "" and "3 5" are invalid.
#
# - Operator rules:
#
#   Let A be the top of the stack and B be the next item.  Then:
#
#   operator      result
#       +          B+A
#       -          B-A
#       *          B*A
#       /          B/A    <-- integer division
#
#

def calculate_rpn_string(s):
    return None

 */
