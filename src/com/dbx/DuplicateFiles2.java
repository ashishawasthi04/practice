package com.dbx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

//    Duplicate Files
//    Things to consider:
//        Symbolic link, same file/dir with diff name, cannot detect cycle by visited…cycle? use absolute path/
//        skip symbolic link (if we search the whole file system)
//        invalid or malformed files e.g. permission or cannot read
//        compare file by hashing, MD5 false positive (due to hash collision), use SHA256/512 very little chance of collision
//        If dir depth is large: DFS might stack overflow, use BFS; the variable to store pathname might overflow.
//        Can ask question after interview how Dropbox solves this type of problem.
//        Most memory consuming: MD5, read in files etc.
//        Race conditions?: someone is writing the file while you are reading etc.
//        If error / hanging up in between: checkpoints, save states from time to time
public class DuplicateFiles2 {

    public List<List<String>> findDuplicates(String path) throws Exception {
        List<List<String>> result = new ArrayList<>();
        if (path == null || path.length() == 0) return result;

        List<String> filesPath = getAllFiles(path);
        Map<String, List<String>> dupFilesMap = new HashMap<>();

        for (String filePath : filesPath) {
            File file = new File(filePath);
            String hashCode = hashFile(file, "MD5");

            if (!dupFilesMap.containsKey(hashCode)) {
                dupFilesMap.put(hashCode, new ArrayList<>());
            }
            dupFilesMap.get(hashCode).add(filePath);
        }

        for (List<String> dupFiles : dupFilesMap.values()) {
            if (dupFiles.size() > 1)
                result.add(dupFiles);
        }

        return result;
    }

    private List<String> getAllFiles(String path) {
        List<String> filesPath = new ArrayList<>();
        Stack<String> s = new Stack<>();
        s.push(path); //DFS with Stack

        while (!s.isEmpty()) {
            String currPath = s.pop();
            File file = new File(currPath);

            if (file.isFile()) {
                filesPath.add(currPath);
            } else if (file.isDirectory()) {
                String[] subDir = file.list();
                for (String dir : subDir) {
                    s.push(currPath + "/" + dir);
                }
            }
        }

        return filesPath;
    }

    public List<List<String>> findDuplicatesOpt(String path) throws Exception {
        List<List<String>> result = new ArrayList<>();
        if (path == null || path.length() == 0) return result;

        Map<Long, List<String>> fileSizeMap = getAllFilesBySize(path);
        Map<String, List<String>> dupFilesMap = new HashMap<>();

        for (List<String> filePaths : fileSizeMap.values()) {
            if (filePaths.size() < 2) continue;
            for (String filePath : filePaths) {
                File file = new File(filePath);
                String hashCode = hashFile(file, "MD5");

                if (!dupFilesMap.containsKey(hashCode)) {
                    dupFilesMap.put(hashCode, new ArrayList<>());
                }
                dupFilesMap.get(hashCode).add(filePath);
            }
        }

        for (List<String> dupFiles : dupFilesMap.values()) {
            if (dupFiles.size() > 1)
                result.add(dupFiles);
        }

        return result;
    }

    private Map<Long, List<String>> getAllFilesBySize(String path) {
        Map<Long, List<String>> fileSizeMap = new HashMap<>();
        Stack<String> s = new Stack<>();
        s.push(path);

        while (!s.isEmpty()) { //DFS by stack
            String currPath = s.pop();
            File file = new File(currPath);

            if (file.isFile()) {
                long size = file.length();
                if (!fileSizeMap.containsKey(size))
                    fileSizeMap.put(size, new ArrayList<>());
                fileSizeMap.get(size).add(currPath);
            } else if (file.isDirectory()) {
                String[] subDir = file.list();
                assert subDir != null;
                for (String dir : subDir) {
                    s.push(currPath + "/" + dir);
                }
            }
        }

        return fileSizeMap;
    }

    private static String hashFile(File file, String algorithm)
            throws Exception {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            byte[] bytesBuffer = new byte[1024];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
                digest.update(bytesBuffer, 0, bytesRead);
            }

            byte[] hashedBytes = digest.digest();

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | IOException ex) {
            throw new Exception(
                    "Could not generate hash from file", ex);
        }
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            //This String.format is the easiest and obvious way to convert a byte arrays into a hex,
            // %02x for lower case hex, %02X upper case hex.
            sb.append(String.format("%02X", arrayByte));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        List<List<String>> dupFiles = new DuplicateFiles2().findDuplicatesOpt("./Resources/Dropbox");
        for (List<String> dup : dupFiles) {
            System.out.println(dup.toString());
        }
        //new FindDuplicateFiles().read1KBEachTime("./Resources/Dropbox/board.txt");
    }

    // How to check the file types in Linux:
    // Linux has different APIs e.g. is_regular_file(), is_block_file etc.
    // How to hash 1KB each time:
    public void read1KBEachTime(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        while (fis.read(buffer) != -1) {
            // hash(buffer);
            System.out.print(Arrays.toString(buffer));
        }
    }
}

//Helper functions (need to be defined by you)
//public List<String> getFiles(String directoryPath); //Returns all files directly under this directory
//public List<String> getDirectories(String directoryPath); //Returns all directories directly under this directory
//public String getFileContent(String filePath); //Returns content of a file
//Write this function (Interviewer just cared about this)
//public List<String> findDuplicates(String rootDirectory) {
