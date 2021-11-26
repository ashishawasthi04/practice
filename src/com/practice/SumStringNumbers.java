package com.practice;

public class SumStringNumbers {
    public static void main(String[] args) {
        add("4,123", "2,456,8");
    }

    public static String add(String s1, String s2){
        StringBuilder res = new StringBuilder();
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int pos1 = arr1.length-1;
        int pos2 = arr2.length-1;
        int num1 = 0, num2 = 0, carry = 0;
        boolean found = false;
        while(pos1 >= 0 || pos2 >= 0){
            found = false;
            while(pos1 >= 0 && !(arr1[pos1] - '0' >= 0 && arr1[pos1] - '9' <= 9)){
                pos1--;
                found = true;
            }
            while(pos2 >= 0 && !(arr2[pos2] - '0' >= 0 && arr2[pos2] - '9' <= 9)){
                pos2--;
                found = true;
            }
            if(found) res.insert(0, ',');
            num1 = pos1 >= 0 ? arr1[pos1--] - '0' : 0;
            num2 = pos2 >= 0 ? arr2[pos2--]  - '0' : 0;
            System.out.println(num1 + " => " + num2);
            int sum = num1 + num2 + carry;
            res.insert(0, (sum)%10);
            carry = sum/10;
        }
        if(carry > 0) res.insert(0, carry);
        System.out.println(res.toString());
        return res.toString();
    }
}
