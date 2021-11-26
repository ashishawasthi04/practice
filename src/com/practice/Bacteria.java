package com.practice;

class Bacteria {
    /*
     * You're given a matrix of characters, x by y. Each index can have a
     * value of 'B' for bacteria, 'X' for chlorine, or '-' for
     * empty air. A bacteria blob is the set of all connected bacteria
     * indices. The blob is 'killed' if any part of the bacteria blob
     * is touching chlorine.  For example:
     *
     *    ALIVE:                      ALIVE:
     *   ----------                 ----------
     *   | B| B| -|                 | B| B| B|
     *   | -| B| -|                 | -| B| B|
     *   | -| -| -|                 | -| B| -|
     *   | -| X| X|                 | X| -| X|
     *   ----------                 ----------
     *
     *
     *    DEAD:                       DEAD:
     *   ----------                 ----------
     *   | B| B| -|                 | B| B| B|
     *   | -| B| -|                 | -| B| -|
     *   | -| X| -|                 | -| B| X|
     *   | -| X| X|                 | -| B| -|
     *   ----------                 ----------
     *
     * Write a function(s) that determines if your matrix contains a
     * living or dead bacteria blob.
     */



    /*
    TC => O(RC)
    SC => O(1)

    */
    public static boolean isAlive(char[][] sample) {
        System.out.println();
        int rows = sample.length;
        int cols = sample[0].length;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(sample[i][j] != '-'){
                    if(i+1 < rows){
                        if(sample[i][j] != sample[i+1][j] && sample[i+1][j] != '-'){
                            System.out.println("Found");
                           return false;
                        }
                    }
                    if(j+1 < cols){
                        if(sample[i][j] != sample[i][j+1] && sample[i][j+1] != '-'){
                            System.out.println("Found");
                            return false;
                        }
                    }
                }
            }
        }
        System.out.println("Not Found");
        return true;
    }

    public static void main(String[] args) {
        // TODO: Your algorithm goes here
        char[][] sample1 = new char[][] {
                {'B', 'B', 'B'},
                {'-', 'B', '-'},
                {'-', 'B', 'X'},
                {'-', 'B', '_'}
        };

        char[][] sample2 = new char[][] {
                {'B', 'B', '-'},
                {'-', 'B', '-'},
                {'-', 'X', 'X'},
                {'-', 'X', 'X'}
        };

        char[][] sample3 = new char[][] {
                {'B', 'B', '-'},
                {'-', 'B', '-'},
                {'-', '-', '-'},
                {'-', 'X', 'X'}
        };

        char[][] sample4 = new char[][] {
                {'B', 'B', 'B'},
                {'-', 'B', 'B'},
                {'-', 'B', '-'},
                {'X', '-', 'X'}
        };

        boolean res = isAlive(sample1);
        System.out.println("Result:" + res);

        res = isAlive(sample2);
        System.out.println("Result:" + res);

        res = isAlive(sample3);
        System.out.println("Result:" + res);

        res = isAlive(sample4);
        System.out.println("Result:" + res);

    }
}

// Should return false:
// [['B', 'B', 'B'], ['-', 'B', '-'], ['-', 'B', 'X'], ['-', 'B', '_']]
// [['B', 'B', '-'], ['-', 'B', '-'], ['-', 'X', '-'], ['-', 'X', 'X']]

// Should return true:
// [['B', 'B', '-'], ['-', 'B', '-'], ['-', '-', '-'], ['-', 'X', 'X']]
// [['B', 'B', 'B'], ['-', 'B', 'B'], ['-', 'B', '-'], ['X', '-', 'X']]