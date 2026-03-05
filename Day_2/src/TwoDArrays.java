import java.util.Arrays;

//2D Arrays — Grids, Matrices, Tables
public class TwoDArrays {
    public static void main(String[] args) {
        //3 rows , 4 columns
        int [][] matrix = new int[3][4];

        //shorthand
        int [][] grid={
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9, 10, 11, 12}
        };

        //Access :[row][column]
        System.out.println(grid[1][2]);

        //Iterate with nested loops
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col] +" ");
            }
            System.out.println();
        }
        System.out.println("--------------");
        //Or with enhanced for
        for(int [] row: grid){
            System.out.println(Arrays.toString(row));
        }
    }
}