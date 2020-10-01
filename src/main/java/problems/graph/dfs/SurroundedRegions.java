package problems.graph.dfs;

/**
 * Category: Graph Traversal, DFS
 * Problem: https://leetcode.com/problems/surrounded-regions/
 * Level: Medium
 */
public class SurroundedRegions {
	/*
	 * Time Complexity: O(n*m)
	 * 		[1]-[2]: the way dfs() is implemented, we don't go over any element
	 * 		  		 twice even if we call it multiple times, so if we have n*m
 * 		 	 		 elements, [1] and [2] give us O(n*m)
	 * 		[3]: 	 O(n*m)
	 * Space Complexity: O(1)
 * 			- no additional space is used
	 */
	public void solve(char[][] board) {
		if (board.length < 3 || board[0].length < 3) {
			return;
		}

		// start from first and last column
		for (int i = 0; i < board.length; i++) { //    [1]
			if (board[i][0] == 'O') {
				dfs(board, i, 0);
			}
			if (board[i][board[0].length - 1] == 'O') {
				dfs(board, i, board[0].length - 1);
			}
		}

		// start from first and last row
		for (int j = 0; j < board[0].length; j++) { //    [2]
			if (board[0][j] == 'O') {
				dfs(board, 0, j);
			}
			if (board[board.length - 1][j] == 'O') {
				dfs(board, board.length - 1, j);
			}
		}

		for (int i = 0; i < board.length; i++) { //    [3]
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				} else if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
	}

	// this method starts from a 'O' on edge and then marks all the other 'O's
	// connected to it
	public void dfs(char[][] board, int row, int col) {
		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 'O') {
			return;
		}

		board[row][col] = '#'; // we mark the nodes
		dfs(board, row - 1, col);
		dfs(board, row + 1, col);
		dfs(board, row, col - 1);
		dfs(board, row, col + 1);
	}
}
