package problems.graph.UnionFind;

import java.util.HashMap;
import java.util.Map;

/**
 * Category: Graph, Union-Find or DFS (you can solve with both)
 * Problem: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 * Level: Medium
 */
public class MostStonesRemoved {
	public Map<Integer, Integer> disjointSets = new HashMap<>(); // [3]
	public int numOfIslands = 0;

	/*
	* Explanation:
	* Connected stones can be reduced to 1 stone. If we count number of islands
	* then: max removed stones = number of stones - number of islands
	* You can use DFS or Union-Find to solve this. to simplify, instead of points
	* we use indexes. So we think:
	* - a row index, connect two stones on this row
	* - a col index, connect two stones on this col.
	* so a stone [i, j] connects a row index (i) to column index (j). In this view
	* number of islands of "points" is equivalent of number of islands of indexes.
	*
	* Union-Find Approach:
	* iterate on each stone, unify its row with its col. Number of "disjoint sets"
	* is the #islands.
	* 	Note: row 2 is different from col 2. we use ~ on col indices to distinguish them
	*
	* Time Complexity: O(n)
	*   [1]: O(n) where n = stones.length
	*   [2]: amortized O(1) since we use path compression
	*
	* Space Complexity: O(n)
	*   [3]: above^ map of disjointSets can hold maximum of n sets
	**/
	public int removeStones(int[][] stones) {
		for (int[] stone : stones) { // [1]
			union(stone[0], ~stone[1]);  // [2]
		}
		return stones.length - numOfIslands;
	}

	private void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);

		if (rootP == rootQ) {
			return; // already joined!
		}

		disjointSets.put(rootP, rootQ);
		numOfIslands--;
	}

	private int find(int p) {
		if (disjointSets.putIfAbsent(p, p) == null) {
			numOfIslands++; // initialize the set
			return p;
		}
		int root = disjointSets.get(p);
		// path compression:
		while (disjointSets.get(root) != root) {
			root = find(disjointSets.get(root));
			disjointSets.put(p, root);
		}
		return root;
	}
}
