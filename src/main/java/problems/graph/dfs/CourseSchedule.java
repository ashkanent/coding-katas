package problems.graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Category: Topological Sort, DAG, Cycle Detection, DFS, BFS
 * Problem: https://leetcode.com/problems/course-schedule/
 * Level: Medium
 */
public class CourseSchedule {

	/*
	* Explanation:
	* There can be multiple ways of solving it. Basic idea is to see if there is any cycles
	* in the graph we generate from prerequisites. If there is a cycle return false.
	* Here we can use topological sort approach and use BFS to see if it's possible but in
	* this implementation I am using DFS to find cycles.
	*
	* Time Complexity: O(V+E)
	*   [2]: O(m) where m = prerequisites.length() (here we have 5000 max)
	*   [4]+[5]: O(V+E) where V and E are number of vertices and edges in our graph
	*            in this implementation, V and E are both 5000 maximum!
	*
	* Space Complexity: O(m)
	*   [1]: O(m) where m = prerequisites.length() (the max is 5000)
	*   [3]: O(m)
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();    // [1]
		boolean cycleDetected;

		if (prerequisites.length == 0) {
			return true; // no prerequisites, no problem!
		}

		for (int[] pre : prerequisites) {    // [2]
			graph.computeIfAbsent(pre[0], v -> new ArrayList<>()).add(pre[1]);
		}


		Map<Integer, Integer> colors = new HashMap<>();    // [3]

		for (Integer node : graph.keySet()) {    // [4]
			colors.put(node, 1);
			cycleDetected = hasCycle(graph, node, colors);    // [5]
			if (cycleDetected) {
				return false;
			}
		}
		return true;
	}

	// DFS
	private boolean hasCycle(Map<Integer, List<Integer>> graph, int root, Map<Integer, Integer> colors) {
		boolean cycleDetected;
		List<Integer> children = graph.get(root);
		if (children == null || children.size() == 0) {
			colors.put(root, 2); // no children == visited all children!
			return false;
		}
		for (Integer child : children) {
			if (colors.get(child) != null && colors.get(child) == 1) {
				return true; // back edge -> cycle
			} else if (colors.get(child) == null || colors.get(child) == 0) {
				colors.put(child, 1);
				cycleDetected = hasCycle(graph, child, colors);
				if (cycleDetected) {
					return true;
				}
			} // else colors.get(child) == 2 --> cross edge or forward edge
		}
		colors.put(root, 2); // visited all children
		return false;
	}
}
