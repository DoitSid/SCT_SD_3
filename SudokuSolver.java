public class SudokuSolver {

    // Grid size
    private static final int SIZE = 9;

    // Function to print the Sudoku grid
    public static void printGrid(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Function to check if a number can be placed in a specific cell
    private static boolean isValid(int[][] grid, int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true; // If all checks pass, the number is valid
    }

    // Backtracking function to solve the Sudoku puzzle
    private static boolean solveSudoku(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == 0) { // Empty cell
                    for (int num = 1; num <= 9; num++) { // Try numbers 1-9
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num; // Place number

                            // Recursively solve for the next cells
                            if (solveSudoku(grid)) {
                                return true;
                            }

                            // Backtrack: Reset the cell
                            grid[row][col] = 0;
                        }
                    }
                    return false; // No valid number found, trigger backtracking
                }
            }
        }
        return true; // All cells are filled
    }

    public static void main(String[] args) {
        // Example Sudoku puzzle (0 represents empty cells)
        int[][] sudokuGrid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Unsolved Sudoku Grid:");
        printGrid(sudokuGrid);

        // Solve the puzzle
        if (solveSudoku(sudokuGrid)) {
            System.out.println("\nSolved Sudoku Grid:");
            printGrid(sudokuGrid);
        } else {
            System.out.println("This Sudoku puzzle cannot be solved.");
        }
    }
}
