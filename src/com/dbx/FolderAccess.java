package com.dbx;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FolderAccess {
    private final Map<String, String> foldersParent;
    private final Set<String> access;

    public FolderAccess(Map<String, String> foldersParent, Set<String> access) {
        this.foldersParent = foldersParent;
        this.access = access;
    }

    public boolean hasAccess(String folderName) {
        String currFolder = folderName;
        while (currFolder != null) {
            if (access.contains(currFolder)) {
                return true;
            } else {
                currFolder = foldersParent.get(currFolder);
            }
        }
        return false;
    }

    public Set<String> simplifyAccess() {
        Set<String> simplifiedAccess = new HashSet<>();

        for (String folder : access) {
            String currFolder = foldersParent.get(folder);
            boolean shouldDelete = false;
            while (currFolder != null && !shouldDelete) {
                if (access.contains(currFolder)) {
                    shouldDelete = true;
                } else {
                    currFolder = foldersParent.get(currFolder);
                }
            }

            if (!shouldDelete)
                simplifiedAccess.add(folder);
        }
        return simplifiedAccess;
    }

    public static void main(String[] args) {
        Map<String, String> foldersParent = new HashMap<>();
        foldersParent.put("B", "A");
        foldersParent.put("C", "B");
        foldersParent.put("D", "B");
        foldersParent.put("E", "A");
        foldersParent.put("F", "E");

        Set<String> access = new HashSet<>();
        access.add("C");
        access.add("E");

        FolderAccess solver = new FolderAccess(foldersParent, access);
        assert !solver.hasAccess("B");
        assert solver.hasAccess("C");
        assert solver.hasAccess("F");
        assert !solver.hasAccess("G"); //G is not in the folders structure
    }
}