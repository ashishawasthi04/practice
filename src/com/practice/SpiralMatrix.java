package com.practice;
class SpiralMatrix {

    static int[] spiralCopy(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int topRow = 0;
        int btmRow = numRows - 1;
        int leftCol = 0;
        int rightCol = numCols - 1;
        int[] result = new int[numRows * numCols];
        int index = 0;
        while (topRow <= btmRow && leftCol <= rightCol){
            //copy the next top row
            int i = leftCol;
            while(i <= rightCol) result[index++] = matrix[topRow][i++];
            topRow++;

            //copy the next right hand side column
            i = topRow;
            while(i <= btmRow) result[index++] = matrix[i++][rightCol];
            rightCol--;

            //copy the next bottom row
            if (topRow <= btmRow){
                i = rightCol;
                while(i >= leftCol) result[index++] = matrix[btmRow][i--];
                btmRow--;
            }

            //copy the next left hand side column
            if (leftCol <= rightCol){
                i = btmRow;
                while(i >= topRow) result[index++] = matrix[i--][leftCol];
                leftCol++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2}};
        spiralCopy(matrix);
    }
}
