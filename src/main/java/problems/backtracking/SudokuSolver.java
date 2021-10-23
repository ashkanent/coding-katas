package problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Category: Backtracking, Bit Masking, Array, Matrix
 * Problem: https://leetcode.com/problems/sudoku-solver/
 * Level: Hard
 */
public class SudokuSolver {
	int[] rowMask = new int[9], colMask = new int[9], boxMask = new int[9];
	List<int[]> emptyCells = new ArrayList<>();

	/*
	* Explanation:
	* table has 3 main sections (row, column and a box) in which a number should not repeat. We scan the board once and
	* extract all empty cells (that needs to be filled), and also use bit mask approach to keep track of existing numbers
	* in each row, col and box (there are 9 of each).
	* Now we just do backtracking on empty cells! Start from the first empty cell and try all possibilities (1-9). If a
	* number is valid (i.e. hasn't occurred in that row/col/box), we have to update 'rowMask', 'colMask' and 'boxMask'.
	* since that number may not be valid, we have to capture these 3 values beforehand, and if it didn't work reset them
	* to what they were before trying the next valid number.
	*
	* Time Complexity: O(9^m)
	*   - m is number of empty cells
	*   - initial scan is O(1) since board has a fixed size
	*
	* Space Complexity: O(1)
	*   - or we can say O(m) for emptyCells but m will be less than 81, so it's constant!
	 */
	public void solveSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					emptyCells.add(new int[]{i, j});
				} else {
					int num = board[i][j] - '0';
					rowMask[i] |= 1 << num;
					colMask[j] |= 1 << num;
					boxMask[(i/3 * 3) + j/3] |= 1 << num;
				}
			}
		}
		backtrack(board, 0);
	}

	private boolean backtrack(char[][] board, int index) {
		int row = emptyCells.get(index)[0], col = emptyCells.get(index)[1];
		int box = (row/3 * 3) + col/3;
		int prevRowMask = rowMask[row],
				prevColMask = colMask[col],
				prevBoxMask = boxMask[(row/3 * 3) + col/3];
		for (int i = 1; i <= 9; i++) {
			if (isValid(row, col, box, i)) {
				board[row][col] = (char)('0' + i);
				rowMask[row] |= 1 << i;
				colMask[col] |= 1 << i;
				boxMask[(row/3 * 3) + col/3] |= 1 << i;
				if (index < emptyCells.size() - 1) {
					if (backtrack(board, index + 1)) {
						return true;
					}
				} else {
					return true; // solved!
				}
				// current selection 'i' did not work, restore prev masks:
				rowMask[row] = prevRowMask;
				colMask[col] = prevColMask;
				boxMask[(row/3 * 3) + col/3] = prevBoxMask;
			}
		}
		return false;
	}

	private boolean isValid(int row, int col, int box, int num) {
		return ((rowMask[row] >> num) & 1) != 1 &&
				((colMask[col] >> num) & 1) != 1 &&
				((boxMask[box] >> num) & 1) != 1;
	}
}
