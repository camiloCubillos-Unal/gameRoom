package gameroom.games.pacman.graphics;

import java.util.Arrays;
import java.util.List;

public class MatrixController {
    
    
    
    static int pattern1[][] = {{0,0,0,0,0,0},
                               {0,1,1,1,1,0},
                               {0,0,0,0,0,0},
                               {0,1,0,1,1,0},
                               {0,1,0,0,1,0},
                               {0,0,1,0,0,0}};
    
    int pattern2[][] = {{1,0,0,0,0,1},
                        {0,0,1,1,0,0},
                        {0,1,1,1,0,1},
                        {0,1,0,0,0,0},
                        {0,1,0,1,1,0},
                        {0,0,0,0,0,0}};
    
    int pattern3[][] = {{0,0,0,0,0,0},
                        {0,1,0,1,1,0},
                        {0,0,0,0,0,0},
                        {0,1,1,0,1,0},
                        {0,0,0,0,0,0},
                        {1,0,1,1,0,1}};
    
    int pattern4[][] = {{0,0,0,0,0,0},
                        {0,1,0,1,1,0},
                        {0,1,0,0,0,0},
                        {0,0,0,1,1,0},
                        {0,1,0,1,1,0},
                        {0,0,0,0,0,0}};
    
    int[][] patterns[] = {pattern1,pattern2,pattern3,pattern4};
    
    static int baseMatrix[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    public static void main(String[] args){
        test();
    }
    
    private static void test(){
        int newMatrix[][] = {{1,2,3},{4,5,6},{7,8,9}};
        newMatrix = reflexMatrix(newMatrix);
        printMatrix(newMatrix);
    }
    public static void composeMatrix(int[][] originalMatrix, int[][] composingMatrix, int quadrant){
        
        switch(quadrant){
            case 1:
                for (int row = 1; row <= 6; row++) {
                    for(int col = 1; col <= 6; col++){
                        originalMatrix[row][col] = composingMatrix[row-1][col-1];
                    }
                }
                break;
            case 2:
                for (int row = 1; row <= 6; row++) {
                    for(int col = 8; col <= 13; col++){
                        originalMatrix[row][col] = composingMatrix[row-1][col-8];
                    }
                }
                break;
            case 3:
                for (int row = 8; row <= 13; row++) {
                    for(int col = 1; col <= 6; col++){
                        originalMatrix[row][col] = composingMatrix[row-8][col-1];
                    }
                }
                break;
            case 4:
                for (int row = 8; row <= 13; row++) {
                    for(int col = 8; col <= 13; col++){
                        originalMatrix[row][col] = composingMatrix[row-8][col-8];
                    }
                }
                break;
            default:
                break;
        }
    }
    
    public static int[][] rotateMatrix(int[][] originalMatrix, int numberOfRotations){
        if(numberOfRotations > 0){
            int matrixSize = originalMatrix.length;
            int[][] rotatedMatrix = new int[matrixSize][matrixSize];
        
            for (int x=0;x<matrixSize;x++) {
                for (int y=0;y<matrixSize;y++) {
                    rotatedMatrix[y][matrixSize-1-x] = originalMatrix[x][y];
                }
            }    
            originalMatrix = rotateMatrix(rotatedMatrix,numberOfRotations-1);
        }
        return originalMatrix;
    }
    

    public static int[][] reflexMatrix(int[][] originalMatrix){
        int[][] reflexedMatrix = new int[originalMatrix.length][originalMatrix.length];
        for (int row = 0; row < originalMatrix.length; row++) {
            int colAuxiliar = 0;
            for (int col = originalMatrix.length - 1; col >= 0; col--) {
                reflexedMatrix[row][colAuxiliar] = originalMatrix[row][col];
                colAuxiliar++;
            }
        }
        return reflexedMatrix;
    }
    
    public int getZeroes(int[][] matrix){                           // Este método retorna la cantidad de galletas en pantallas, identificadas como números 0 en la matríz de mapa.
        int zeroesAmount = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 0) {
                    zeroesAmount++;
                }
            }
        }
        return zeroesAmount;
    }
    
    public static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("\n");
        }
    }
}


