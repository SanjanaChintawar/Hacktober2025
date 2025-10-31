import java.util.ArrayList;
import java.util.List;

class NQueens {
    private List<List<String>> solutions;
    
    private boolean isSafe(char[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        
        // Check upper left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        
        // Check upper right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        
        return true;
    }
    
    private void solve(char[][] board, int row, int n) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] r : board) {
                solution.add(new String(r));
            }
            solutions.add(solution);
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                solve(board, row + 1, n);
                board[row][col] = '.';  // Backtrack
            }
        }
    }
    
    public List<List<String>> solveNQueens(int n) {
        solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize board with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        solve(board, 0, n);
        return solutions;
    }
    
    public void printSolutions(List<List<String>> sols) {
        System.out.println("Total solutions: " + sols.size() + "\n");
        for (int s = 0; s < sols.size(); s++) {
            System.out.println("Solution " + (s + 1) + ":");
            for (String row : sols.get(s)) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        NQueens solver = new NQueens();
        int n = 4;
        List<List<String>> solutions = solver.solveNQueens(n);
        solver.printSolutions(solutions);
    }
}
