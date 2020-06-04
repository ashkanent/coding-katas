package problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Category: Graph
 * Problem: https://leetcode.com/problems/minimum-height-trees/
 * Level: Medium
 */
public class MinimumHeightTrees {

	/*
	* Explanation:
	* 	- tree has n nodes and n-1 edges
	* 	- leaf is a node with degree 1
	* 	- path graph is a tree that all the nodes in it have degree 1 or 2
	* 		- something like this: * --- * --- *
	* 		- root of MHT in path graph is obviously the middle element (or 2 middle elements depending on the parity)
	* 	- this gives us the idea that for MHT leaves are not good options as their adjacent node will be a better option
	* 		- unless there are two nodes remaining (* --- *)
	* 		- in this case, these two nodes are the answer
	* 	- so we find leaves and eliminate them until we have 1 or 2 leaves left and that would be the answer
	*
	* Time Complexity: O(n)
	* 	[1] O(n)
	* 	[3] O(n) as we have n-1 edges
	* 	[5] O(n)
	* 	[6] & [7]: O(n) - we go through all of the current leaves AND new leaves (new leaves we find after removing
	* 		current leaves). This number is maximum n leaves in total
	*
	* Space Complexity: O(n)
	* 	[2] O(n) - list of size n
	* 	[3] O(n) we keep n-1 edges collectively in our sets
	* 	[8] O(n) total number of leaves in each level will add up to n (maximum)
	* */
	public List<Integer> solve(int n, int[][] edges) {
		if (n < 2) {
			return Arrays.asList(0);
		} else if (n == 2) {
			return Arrays.asList(edges[0][0], edges[0][1]);
		}
		List<Set<Integer>> adj = new ArrayList<>();
		List<Integer> leaves = new ArrayList<>();

		for (int i = 0; i < n; i++) {   // [1]
			adj.add(i, new HashSet<Integer>());   // [2]
		}

		for (int[] edge : edges) {   // [3]
			adj.get(edge[0]).add(edge[1]);   // [4]
			adj.get(edge[1]).add(edge[0]);
		}

		for (int i = 0; i < n; i++) {   // [5]
			if (adj.get(i).size() == 1)
				leaves.add(i);
		}

		while (n > 2) {   // [6]
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			// remove current leaves:
			for (Integer leaf : leaves) {   // [7]
				int j = adj.get(leaf).iterator().next(); // there should be exactly 1 element
				adj.get(j).remove(leaf); // remove the leaf
				if (adj.get(j).size() == 1) {
					newLeaves.add(j);   // [8]
				}
			}
			// set the new leaves:
			leaves = newLeaves;
		}

		return leaves;
	}
}
