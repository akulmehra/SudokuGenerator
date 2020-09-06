import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Sudoku extends JPanel{
    static int[][] Grid = new int[9][9];
    static int[] values = {1,2,3,4,5,6,7,8,9};
    int GRID_WIDTH = 500;
    int GRID_HEIGHT = 550;
    int GRID_ROWS = 9;
    int GRID_COL = 9;

    public static void main(String[] args) {
        //Graphics g = new Graphics();
        MakeSudoku();
        Random random = new Random();
        int val = random.nextInt(9) + 1;
        //Grid[0][0] = val;
        //Grid[0][1] = 2;
        //isValid(2,2,3);
        fillSudoku();
        printSudoku();
        Sudoku sudoku = new Sudoku();
    }

    public static boolean fillSudoku() {
        return fillSudokuHelper();
    }

    private static boolean fillSudokuHelper() {
        int[] cell = next();
        
        if(cell == null) {
            return true;
        }

        int row = cell[0];
        int col = cell[1];

        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();
        for(int i=0;i<9;i++) {
            int value = random.nextInt(9) + 1;
            if(!set.contains(value)) {
                if(isValid(row, col, value)) {
                    Grid[row][col] = value;
                    if(fillSudokuHelper()) {
                        return true;
                    }

                    Grid[row][col] = 0;
                }
                set.add(value);
            }
        }

        return false;
    }


    public static void MakeSudoku() {
        // Filling grid with zeros
        for(int i = 0; i < 9; i++) {
            for(int j=0; j < 9; j++) {
                Grid[i][j] = 0;
            }
        }
    }

    public static void printSudoku() {
        for(int i = 0; i < 9; i++) {
            for(int j=0; j<9; j++) {
                System.out.print("" + Grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValid(int row, int col, int value) {
        System.out.println("Checking " + value + " at " + row + " " + col);
        for(int i=0; i<9; i++) {
            if(Grid[row][i] == value) {
                //System.out.println("invalid cos of row");
                return false;
            }

            if(Grid[i][col] == value) {
                //System.out.println("invalid cos of col");
                return false;
            }
        }

        int smallCol = (col / 3) * 3;
        //System.out.println(smallCol);
        int smallRow = (row / 3) * 3;
        //System.out.println(smallRow);

        for(int i=smallCol; i<smallCol+3; i++) {
            for(int j=smallRow; j<smallRow+3; j++) {
                if(Grid[j][i] == value) {
                    //System.out.println("invalid cos of box");
                    return false;
                }
            }
        }
        System.out.println("valid");
        return true;
    }

    public static int[] next() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(Grid[i][j] == 0) {
                    int[] next = {i,j};
                    return next;
                }
            }
        }
        return null;
    }
}