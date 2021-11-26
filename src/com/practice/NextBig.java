package com.practice;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextBig {

    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
        int length = w.length();
        int right = length-1;
        while(right >= 1){
            char c = w.charAt(right);
            char p = w.charAt(right-1);
            if(c > p){
                //Find character which is greater than p and less than rest
                int srcIdx = right-1;
                int nextMaxIdx = right;
                char nextMax = w.charAt(right);
                right++;
                while(right < length){
                    char curChar = w.charAt(right);
                    if(curChar > p && curChar < nextMax){
                        nextMaxIdx = right;
                        nextMax = curChar;
                    }
                    right++;
                }
                //Swap
                char[] chars = w.toCharArray();
                chars[srcIdx] = nextMax;
                chars[nextMaxIdx] = p;
                //Sort all chars after srcIdx
                Arrays.sort(chars, srcIdx+1, length);
                return new String(chars);
            }else right--;
        }
        return "no answer";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        int t = 100000;
        List<String> lines = readFile();
        for (int i = 0; i < t; i++) {
            String w = lines.get(i);

            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static List<String> readFile(){
        List<String> list = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"));
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

