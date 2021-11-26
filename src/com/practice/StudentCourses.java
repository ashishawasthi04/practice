package com.practice;

import java.util.*;
import java.util.stream.Collectors;

public class StudentCourses {
    public static void main(String[] args) {
        String[][] studentCourses = {
                {"1", "1-A"},
                {"1", "3-A"},
                {"2", "2-A"},
                {"2", "1-A"},
                {"3", "1-A"},
                {"3", "2-A"},
                {"1", "1-B"},
                {"3", "3-A"}
        };
        pairStudentCourses(studentCourses);
    }

    public static void pairStudentCourses(String[][] studentCourses){
        Map<String, Set<String>> pairCommonCourses = new HashMap<>();
        Map<String, Set<String>> studentToCourses = new HashMap<>();
        for(String[] testData : studentCourses){
            studentToCourses.compute(testData[0], (key, value) -> {
                if(value == null) value = new HashSet<String>();
                value.add(testData[1]);
                return value;
            });
        }

        List<String> students = studentToCourses.keySet().stream().sorted().collect(Collectors.toList());
        for(int i=0; i < students.size()-1; i++){
            for(int j=i+1; j < students.size(); j++){
                String student1 = students.get(i), student2 = students.get(j);
                Set<String> commonCourses = studentToCourses.get(student1).stream()
                        .filter(studentToCourses.get(student2)::contains)
                        .collect(Collectors.toSet());
                pairCommonCourses.put(student1 + "," + student2, commonCourses);
            }
        }

        //Print Result
        pairCommonCourses.forEach((k, v) -> System.out.println(k + "==>" + v));
    }
}
