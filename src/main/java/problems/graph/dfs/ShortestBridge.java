package problems.graph.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Category: DFS, BFS
 * Problem: https://leetcode.com/problems/shortest-bridge/
 * Level: Medium
 */
public class ShortestBridge {

	/*
	* Time Complexity: O(m*n) note that in this problem m == n and m, n < 100
	* 		[3]: O(m*n) although we have m*n loop and inside it we call dfs()
	* 			 which is O(m*n) but first loop stops at the first '1' it finds,
	* 			 and from there dfs() covers all the 1's that are connected which
	* 			 in total are less than m*n
	* 		[4]: O(m*n) since elements of an island are for sure less than m*n
	* 		[5]: O(m*n) maximum elements that we can put in q are m*n
	* Space Complexity: O(m*n)
	* 		[1]: O(m*n) where m = A.length and n = A[0].length
	* 		[2]: O(m*n) maximum number of cells we can put in the queue is m*n
	* */
	public int shortestBridge(int[][] A) {
		int m = A.length, n = A[0].length;
		boolean[][] visited = new boolean[m][n];		// [1]
		int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		Queue<int[]> q = new LinkedList<>();		// [2]
		boolean found = false;

		// 1. dfs to find an island, mark it in `visited`
		for (int i = 0; i < m; i++) {		// [3]
			if (found) {
				break;
			}
			for (int j = 0; j < n; j++) {
				if (A[i][j] == 1) {
					dfs(A, visited, q, i, j, directions);		// [4]
					found = true;
					break;
				}
			}
		}

		// 2. bfs to expand this island
		int step = 0, size;
		while (!q.isEmpty()) {		// [5]
			size = q.size();
			while (size > 0) {
				int[] cur = q.poll();
				for (int[] dir : directions) {
					int row = cur[0] + dir[0];
					int col = cur[1] + dir[1];
					if (row >= 0 && col >= 0 && row < m && col < n && !visited[row][col]) {
						if (A[row][col] == 1) {
							return step;
						}
						q.offer(new int[]{row, col});
						visited[row][col] = true;
					}
				}
				size--;
			}
			step++;
		}
		return -1;
	}

	private void dfs(int[][] map, boolean[][] visited, Queue<int[]> q, int row, int col, int[][] directions) {
		// ignore invalid cases
		if (row < 0 || col < 0 || row >= map.length || col >= map[0].length || visited[row][col] || map[row][col] == 0) {
			return;
		}

		visited[row][col] = true;
		q.offer(new int[]{row, col});

		// find children
		for (int[] dir : directions) {
			dfs(map, visited, q, row + dir[0], col + dir[1], directions);
		}
	}





	/*********************** Original Solution, not optimized **********************/

	public int[] rowIndices = new int[1000000];
	public int[] colIndices = new int[1000000];
	public int rowColPointer = 0;
	public int[][] bfsMap = new int[100][100];

	public int shortestBridge2(int[][] A) {

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (A[i][j] == 1) {
					findBorders(A, i, j, new int[A.length][A.length]);
					return bfs(A);
				}
			}
		}

		// shouldn't get here
		return -1;
	}


	/*
	 * This method uses DFS to find cells (row+cols) that are on the border
	 * of the island.
	 */
	private void findBorders(int[][] map, int row, int col, int[][] visited) {
		if (visited[row][col] == 1) {
			return;
		}

		if (isBorder(map, row, col)) {
			rowIndices[rowColPointer] = row;
			colIndices[rowColPointer] = col;
			rowColPointer++;
			bfsMap[row][col] = 1;
		}
		visited[row][col] = 1;

		if (row > 0 && map[row-1][col] == 1) {
			findBorders(map, row-1, col, visited);
		}
		if (col > 0 && map[row][col-1] == 1) {
			findBorders(map, row, col-1, visited);
		}
		if (row+1 < map.length && map[row+1][col] == 1) {
			findBorders(map, row+1, col, visited);
		}
		if (col+1 < map.length && map[row][col+1] == 1) {
			findBorders(map, row, col+1, visited);
		}
	}

	private boolean isBorder(int[][] map, int row, int col) {
		if (row > 0 && map[row-1][col] == 0) {
			return true;
		} else if (col > 0 && map[row][col-1] == 0) {
			return true;
		} else if (row+1 < map.length && map[row+1][col] == 0) {
			return true;
		} else if (col+1 < map.length && map[row][col+1] == 0) {
			return true;
		}
		return false;
	}

	private int bfs(int[][] map) {
		int qIndex = 0, row, col;

		while (qIndex < rowColPointer) {
			row = rowIndices[qIndex];
			col = colIndices[qIndex];

			// add children
			if (row > 0) {
				if (map[row-1][col] == 1) {
					if (map[row][col] != 1 && bfsMap[row-1][col] == 0)
						return bfsMap[row][col] - 1;
				} else if (bfsMap[row-1][col] == 0) {
					rowIndices[rowColPointer] = row - 1;
					colIndices[rowColPointer] = col;
					bfsMap[row-1][col] = bfsMap[row][col] + 1;
					rowColPointer++;
				}
			}
			if (col > 0) {
				if (map[row][col-1] == 1) {
					if (map[row][col] != 1 && bfsMap[row][col-1] == 0)
						return bfsMap[row][col] - 1;
				} else if (bfsMap[row][col-1] == 0) {
					rowIndices[rowColPointer] = row;
					colIndices[rowColPointer] = col - 1;
					bfsMap[row][col-1] = bfsMap[row][col] + 1;
					rowColPointer++;
				}
			}
			if (row + 1 < map.length) {
				if (map[row+1][col] == 1) {
					if (map[row][col] != 1 && bfsMap[row+1][col] == 0)
						return bfsMap[row][col] - 1;
				} else if (bfsMap[row+1][col] == 0) {
					rowIndices[rowColPointer] = row + 1;
					colIndices[rowColPointer] = col;
					bfsMap[row+1][col] = bfsMap[row][col] + 1;
					rowColPointer++;
				}
			}
			if (col + 1 < map.length) {
				if (map[row][col+1] == 1) {
					if (map[row][col] != 1 && bfsMap[row][col+1] == 0)
						return bfsMap[row][col] - 1;
				} else if (bfsMap[row][col+1] == 0) {
					rowIndices[rowColPointer] = row;
					colIndices[rowColPointer] = col + 1;
					bfsMap[row][col+1] = bfsMap[row][col] + 1;
					rowColPointer++;
				}
			}

			qIndex++;
		}

		// shouldn't get here
		return -1;
	}
}
