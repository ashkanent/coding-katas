package problems.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Category: Graph Traversal, BFS
 * Problem: https://leetcode.com/problems/rotting-oranges/
 * Level: Medium
 */
public class RottenOranges {
	public int solve(int[][] grid) {
		int minutes = 0;

		minutes = visit(grid);

		// second iteration over grid, if we find 1, we should return -1
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}

		// everything is rotten:
		return minutes;
	}

	public int visit(int[][] grid) {
		Queue<Cell> orangeQueue = new LinkedList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) {
					orangeQueue.add(new Cell(i, j, 0));
				}
			}
		}
		return bfs(grid, orangeQueue);
	}

	public int bfs(int[][] grid, Queue<Cell> orangeQueue) {
		if (orangeQueue.isEmpty()) {
			return 0;
		}
		Cell currentCell = orangeQueue.remove();

		// add neighbours to the queue
		Cell aboveCell = currentCell.getAboveCell(grid);
		Cell belowCell = currentCell.getBelowCell(grid);
		Cell rightCell = currentCell.getRightCell(grid);
		Cell leftCell = currentCell.getLeftCell(grid);
		if (aboveCell != null && !orangeQueue.contains(aboveCell)) orangeQueue.add(aboveCell);
		if (belowCell != null && !orangeQueue.contains(belowCell)) orangeQueue.add(belowCell);
		if (rightCell != null && !orangeQueue.contains(rightCell)) orangeQueue.add(rightCell);
		if (leftCell != null && !orangeQueue.contains(leftCell)) orangeQueue.add(leftCell);


		// visit current node
		grid[currentCell.row][currentCell.col] = 2;

		return Math.max(bfs(grid, orangeQueue), currentCell.level);
	}


	class Cell {
		public int row;
		public int col;
		public int level;

		public Cell(int row, int col, int level) {
			this.row = row;
			this.col = col;
			this.level = level;
		}

		public Cell getAboveCell(int[][] grid) {
			if (this.row - 1 < 0) return null;
			if (grid[this.row - 1][this.col] == 1)
				return new Cell(this.row - 1, this.col, this.level + 1);
			return null;
		}

		public Cell getBelowCell(int[][] grid) {
			if (this.row + 1 >= grid.length) return null;
			if (grid[this.row + 1][this.col] == 1)
				return new Cell(this.row + 1, this.col, this.level + 1);
			return null;
		}

		public Cell getRightCell(int[][] grid) {
			if (this.col + 1 >= grid[0].length) return null;
			if (grid[this.row][this.col + 1] == 1)
				return new Cell(this.row, this.col + 1, this.level + 1);
			return null;
		}

		public Cell getLeftCell(int[][] grid) {
			if (this.col - 1 < 0) return null;
			if (grid[this.row][this.col - 1] == 1)
				return new Cell(this.row, this.col - 1, this.level + 1);
			return null;
		}

		@Override
		public boolean equals(Object obj) {
			return (col == ((Cell)obj).col && row == ((Cell)obj).row);
		}
	}
}
