package com.practice;

import java.util.Arrays;

class TestCopilot {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

//    public static void main(String[] args) {
//        toBinary(6);
//        int[][] matrix = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16},
//        };
//        spiralTraversal(matrix);
//    }

    //number to binary conversion
    public static void toBinary(int n) {
        StringBuilder binary = new StringBuilder();
        while (n != 0) {
            binary.insert(0, (n % 2));
            n /= 2;
        }
        System.out.println(binary);
    }

    //matrix spiral traversal
    public static void spiralTraversal(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int top = 0;
        int bottom = row - 1;
        int left = 0;
        int right = col - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                System.out.print(matrix[top][i] + " ");
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--;
            for (int i = right; i >= left; i--) {
                System.out.print(matrix[bottom][i] + " ");
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                System.out.print(matrix[i][left] + " ");
            }
            left++;
        }
    }

    //serialize tree
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public static void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
            return;
        }
        sb.append(root.val).append(" ");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    //deserialize tree
    public static TreeNode deserialize(String data) {
        String[] nodes = data.split(" ");
        int index = 0;
        return deserialize(nodes, index);
    }

    public static TreeNode deserialize(String[] nodes, int index) {
        if (index >= nodes.length || nodes[index].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes[index]));
        index++;
        root.left = deserialize(nodes, index);
        index++;
        root.right = deserialize(nodes, index);
        return root;
    }

    //edit distance
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    //max length of common sub array
    public static int maxLength(int[] arr1, int[] arr2) {
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        int max = 0;
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    //duplicate in array
    public static boolean duplicate(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int j : arr) {
            if (dp[j] == 1) {
                return true;
            }
            dp[j] = 1;
        }
        return false;
    }

//    int[] arr = [1.......M] where M is the size of the array
//    Find the FIRST duplicate number in array.
//    If there is no duplicate then return -1;
//
//    arr =   [4, 2, 1, 3, 2, 1]
//            [2, 2, 1, 3, 4, 1]
//            [4, 2, 1, 3, 2, 1]
//            [5, 2, 1, 7, 2, 1]
//
//            [0, 1, 2, 3, 4, 5]
//            [1, 2, 3, 4, 2, 1]
//            ^

    public static void main1(String[] args) {
        int[] arr = new int[]{4, 2, 1, 3, 2, 1};
        System.out.println(findDuplicate(arr));
        arr = new int[]{5, 2, 1, 3, 1, 1};
        System.out.println(findDuplicate(arr));

        arr = new int[]{4, 2, 1, 3, 2, 1};
        System.out.println(findDuplicateAnother(arr));
        arr = new int[]{5, 2, 1, 3, 1, 1};
        System.out.println(findDuplicateAnother(arr));
    }
    //Excepted TC= O(N) and SC = O(1)
    public static int findDuplicate(int[] arr){
        int length = arr.length;
        for(int i = 0; i < length; i++){
            int num = arr[i];
            if(num != i+1){
                if(arr[i] == arr[num-1]) return num;
                swap(arr, i, num-1);
            }
        }
        return -1;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int findDuplicateAnother(int[] arr){
        int length = arr.length, dupIdx = -1;
        boolean found = false;
        for(int i = 0; i < length; i++){
            for(int j = i+1; j < arr.length-1; j++){
                if(arr[i] == arr[j]){
                    dupIdx = !found ? j : Math.min(dupIdx, j);
                    if(!found) found = true;
                    break;
                }
            }
        }
        return !found ? -1 : arr[dupIdx];
    }

    //Island count
    public static int islandCount(char[][] grid){
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public static void dfs(char[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }

    //Find prime number
    public static void findPrime(int n){
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for(int i = 2; i <= n; i++){
            if(prime[i]){
                for(int j = 2; j*i <= n; j++){
                    prime[i*j] = false;
                }
            }
        }
        for(int i = 0; i < prime.length; i++){
            if(prime[i]) System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        findPrime(200);
    }
}