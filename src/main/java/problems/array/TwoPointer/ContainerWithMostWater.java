package problems.array.TwoPointer;

/**
 * Category: Array, Two Pointer, Greedy
 * Problem: https://leetcode.com/problems/container-with-most-water/
 * Level: Medium
 */
public class ContainerWithMostWater {

	/*
	* Explanation:
	* We have to pointers at the two ends (left and right). Calculate the area.
	* To move the pointer see which line is shorter (since that will limit our
	* area in future) and move that one (left++ or right--). If length of left
	* and right is equal, doesn't matter which one we move.
	* keep calculating the area and track the maximum.
	*
	* Time Complexity: O(n)
	*
	* Space Complexity: O(1)
	 */
	public int maxArea(int[] height) {
		int maxArea = 0, area = 0;
		int left = 0;
		int right = height.length - 1;

		while (left < right) {
			maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}

		return maxArea;
	}
}
