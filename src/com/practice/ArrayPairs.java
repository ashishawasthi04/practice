package com.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArrayPairs {

    // Complete the solve function below.
    static long solve(int[] arr) {
        int length = arr.length;
        if(length <= 1) return 0;
        long count = 0;

        for(int i = 0; i < length-1; i++){
            long max = arr[i];
            for(int j = i+1; j < length; j++){
                if(arr[j] > max){
                    max = arr[j];
                }
                long a = max;
                long b = arr[i];
                long c = arr[j];
                long mul = b * c;
                if(mul <= a) count++;
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = 100000;
        int[] arr = new int[arrCount];
        Path path = Paths.get("/Users/ashishawasthi/Practice/src/com/practice/fileTest.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        String[] arrItems = line.split(" ");

        for (int i = 0; i < arrCount; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        long result = solve(arr);
        System.out.println(result);
    }
}

