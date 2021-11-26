package com.practice;
/*
- count rows and cols
- count layers minNum = min(rows, cols) => Math.ceil(minNum/2)
- for each layer (Assume start at x, y)
  - go right from x, y => x, cols - layerIndex
  - go bottom from x + 1, cols - layerIndex => rows - layerIndex, cols - layerIndex
  - go left from rows - layerIndex, cols - layerIndex - 1 => rows - layerIndex, y
  - go up from rows - layerIndex - 1, y => x + 1, y
*/
class MatrixSpiralCopy {
    static int[] spiralCopy(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int layers = (int) Math.ceil(Math.min(rows, cols)/2.0);
        int counter = 1;
        int x = 0, y = 0;
        int[] res = new int[rows*cols];
        int index = 0;

        while(counter <= layers){
            //Go right
            int tempX = x, tempY = y;
            while(tempY <= cols-counter ){
                System.out.print(" " + matrix[tempX][tempY]);
                res[index++] = matrix[tempX][tempY];
                tempY++;
            }
            //Go down
            tempX = x+1; tempY = cols-counter;
            while(tempX <= rows-counter ){
                System.out.print(" " + matrix[tempX][tempY]);
                res[index++] = matrix[tempX][tempY];
                tempX++;
            }
            //Go left
            tempX = rows-counter; tempY = cols-counter-1;
            while(tempX != x && tempY >= y ){
                System.out.print(" " + matrix[tempX][tempY]);
                res[index++] = matrix[tempX][tempY];
                tempY--;
            }
            //Go up
            tempX = rows-counter-1; tempY = y;
            while(tempX >= x+1 ){
                System.out.print(" " + matrix[tempX][tempY]);
                res[index++] = matrix[tempX][tempY];
                tempX--;
            }
            counter++;
            x++;
            y++;
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}};
        spiralCopy(matrix);
    }
}
