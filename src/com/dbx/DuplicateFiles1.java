package com.dbx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/find-duplicate-file-in-system/
class DuplicateFiles1 {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (String path : paths) {
            processDetails(path, map);
        }

        for (String content : map.keySet()) {
            if (map.get(content).size() >= 2) {
                res.add(map.get(content));
            }
        }
        return res;
    }

    public void processDetails(String path, Map<String, List<String>> map) {
        String[] paths = path.split("\\s");
        String dir = paths[0];
        for (int i = 1; i < paths.length; i++) {
            String[] fileNameContent = paths[i].split("\\(");
            String fileName = fileNameContent[0];
            String fileContent = fileNameContent[1].split("\\)")[0];
            map.putIfAbsent(fileContent, new ArrayList<>());
            map.get(fileContent).add(dir + "/" + fileName);
        }
    }
}
