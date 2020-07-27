package problems.graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Category: Graph Traversal, BFS
 * Problem: https://leetcode.com/problems/01-matrix/
 * Level: Medium
 */
public class ZeroOneMatrix {

	/*
	* Time Complexity: O(r*c)
	* 		[1]: O(r*c) where "r" is number of rows and "c" is number of columns
	* 		[2]: because of the condition we only visit cells if their value is
	* 			 greater than the calculated distance so we shouldn't revisit
	* 			 cells. Maximum cells that we visit should be less than r*c
	* Space Complexity: O(r*c)
	* 		[3]: O(r*c)
	* 		[4]: maximum is r*c cells => O(r*c)
	* 		[5]: should be negligible and can be avoided. I added it for better
	* 			 readability.
	* */
	public int[][] updateMatrix(int[][] matrix) {
		return bfs(matrix);
	}

	private int[][] bfs(int[][] matrix) {
		int[][] result = new int[matrix.length][matrix[0].length];		// [3]
		Queue<Point> visitQueue = new LinkedList<Point>();		// [4]

		for (int i = 0; i < matrix.length; i++) {		// [1]
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					result[i][j] = 0;
					visitQueue.add(new Point(i, j));
				} else {
					result[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		while (!visitQueue.isEmpty()) {		// [2]
			Point current = visitQueue.remove();
			List<Point> neighbours = new ArrayList<>();		// [5]
			neighbours.add(current.above());
			neighbours.add(current.left());
			neighbours.add(current.below(matrix.length));
			neighbours.add(current.right(matrix[0].length));

			for (Point point : neighbours) {
				if (point != null && result[point.row][point.col] > result[current.row][current.col] + 1) {
					result[point.row][point.col] = result[current.row][current.col] + 1;
					visitQueue.add(point);
				}
			}
		}

		return result;
	}


	class Point {
		public int row;
		public int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public Point above() {
			if (this.row == 0)
				return null;
			else {
				return new Point(this.row - 1, this.col);
			}
		}

		public Point below(int rows) {
			if (this.row == rows - 1)
				return null;
			else {
				return new Point(this.row + 1, this.col);
			}
		}

		public Point left() {
			if (this.col == 0)
				return null;
			else {
				return new Point(this.row, this.col - 1);
			}
		}

		public Point right(int columns) {
			if (this.col + 1 == columns) {
				return null;
			} else {
				return new Point(this.row, this.col + 1);
			}
		}

	}
}
