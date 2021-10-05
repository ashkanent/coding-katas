package problems.graph.UnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * Category: DFS, Union Find
 * Problem: https://leetcode.com/problems/friend-circles/
 * Level: Medium
 */
public class FriendCircles {

	/*
	* Time Complexity: O(n^2)
	* 	[1]: O(n) where n == M.length
	* 	[2]: O(n+e) where 'e' is number of edges (friendships)
	*
	* Space Complexity: O(1)
	*
	* Explanation:
	* 	- Here M[i][i] will be always '1' so to mark it as visited
	* 	  we can instead set this value to '0' so we don't need e-
	* 	  xtra time and space to check for visited nodes
	* */
	public int findCircleNum(int[][] M) {
		int circles = 0;

		for (int i = 0; i < M.length; i++) {   // [1]
			if (M[i][i] != 0) {
				circles++;
				dfs(M, i);   // [2]
			}
		}

		return circles;
	}

	private void dfs(int[][] matrix, int root) {
		matrix[root][root] = 0;

		for (int friend = 0; friend < matrix[root].length; friend++) {
			if (matrix[root][friend] == 1 && matrix[friend][friend] != 0) {
				dfs(matrix, friend);
			}
		}
	}


	/*
	* - First implementation which is a bit slower (because of 'set' look-up)
	* - It uses more memory because of the "visited" set
	* */
	public int findCircleNumII(int[][] M) {
		int circles = 0;
		Set<Integer> visited = new HashSet<>();

		for (int i = 0; i < M.length; i++) {
			if (!visited.contains(i)) {
				circles++;
				dfsII(M, visited, i);
			}
		}

		return circles;
	}

	private void dfsII(int[][] matrix, Set<Integer> visited, int root) {
		if (visited.contains(root)) {
			return;
		}
		visited.add(root);

		for (int friend = 0; friend < matrix[root].length; friend++) {
			if (matrix[root][friend] == 1 && !visited.contains(friend)) {
				dfsII(matrix, visited, friend);
			}
		}
	}
}
