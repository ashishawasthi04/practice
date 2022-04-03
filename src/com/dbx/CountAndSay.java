package com.dbx;

/*
https://leetcode.com/problems/count-and-say/
Dropbox
Count And Say
This question is mainly for college interns.
This question can directly be found on LeetCode.
A follow-up question is to read from a file stream of numbers.
*/
/*
The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a
different digit string.
To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a
contiguous section all the same character. Then for each group, say the number of characters,
then say the character. To convert the saying into a digit string, replace the counts with a number
and concatenate every saying.
For example, the saying and conversion for digit string "3322251":

Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"

 */
class CountAndSay {
    public String countAndSay(int n) {
        String temp = "1";
        while (--n > 0) {
            temp = helper(temp);
        }
        return temp;
    }

    public String helper(String num) {
        int i = 0, counter = 0;
        char cur = 0, prev = 0;
        StringBuilder sb = new StringBuilder();
        while (i < num.length()) {
            cur = num.charAt(i++);
            if (cur == prev) counter++;
            else {
                if (i > 1) sb.append(counter).append(prev);
                counter = 1;
            }
            if (i == num.length()) sb.append(counter).append(cur);
            prev = cur;
        }
        return sb.toString();
    }
}
