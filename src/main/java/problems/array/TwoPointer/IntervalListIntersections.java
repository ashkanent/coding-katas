package problems.array.TwoPointer;

import java.util.ArrayList;
import java.util.List;

/**
 * Category: Array, Two Pointer, Array Merge
 * Problem: https://leetcode.com/problems/interval-list-intersections/
 * Level: Medium
 */
public class IntervalListIntersections {

	// better solution
	/*
	* Explanation:
	* have one pointer for each list. compare the current two blocks you are pointing to. if there is an intersection,
	* add it to the list and move the pointer for the block that was behind (we use end of block to determine which
	* one is behind, e.g. [1, 4] is behind [2, 5] since 4 < 5). This is very similar to merge portion of the merge
	* sort except in merge sort after the loop you have to add the remainder of the longer list but here since we are
	* finding the intervals, we don't need this last part.
	*
	* Time Complexity: O(n+m)
	*   [2]: worst case we visit all blocks in A and B, n = A.length, m = B.length => O(n + m)
	*
	* Space Complexity: O(k)
	*   [1]: if k = #intersections => in worst case: k = min(n, m)
	 */
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> res = new ArrayList<>();   // [1]
		for (int i = 0, j = 0; i < A.length && j < B.length; ) {    // [2]
			int start = Math.max(A[i][0], B[j][0]);
			int end = Math.min(A[i][1], B[j][1]);
			if (start <= end)
				res.add(new int[]{start, end});
			if (A[i][1] < B[j][1])
				++i;
			else
				++j;
		}
		return res.toArray(new int[0][]);
	}

	// my solution
	public int[][] intervalIntersectionII(int[][] firstList, int[][] secondList) {
		List<int[]> intersections = new ArrayList<>();
		int first = 0, second = 0; // pointers to first and second list
		int[] currentIntersection;

		while (first < firstList.length && second < secondList.length) {
			// find intersections
			currentIntersection = findIntersection(firstList[first], secondList[second]);
			if (currentIntersection[0] != -1) {
				intersections.add(currentIntersection);
			}

			// move pointers
			if (firstList[first][1] < secondList[second][1]) {
				first++;
			} else if (firstList[first][1] > secondList[second][1]) {
				second++;
			} else {
				first++;
				second++;
			}
		}

		return intersections.toArray(new int[0][]);
	}

	private int[] findIntersection(int[] first, int[] second) {
		if (first[1] < second[1]) {
			if (first[1] < second[0]) {
				return new int[]{-1, -1}; // no intersection
			}
			if (first[0] > second[0]) {
				return first; // entire 'first' is the intersection
			}
			return new int[]{second[0], first[1]};
		} else {
			if (second[1] < first[0]) {
				return new int[]{-1, -1}; // second is entirely behind first
			}
			if (first[0] < second[0]) {
				return second; //entire 'second' is the intersection
			}
			return new int[]{first[0], second[1]};
		}
	}
}
