package problems.DP.Knapsack;

/**
 * Category: Dynamic Programming, Knapsack
 * Problem: https://leetcode.com/problems/partition-equal-subset-sum/
 * Level: Medium
 */
public class PartitionEqualSubsetSum {

	/*
	* given: n = nums.length and m = sum / 2:
	*
	* Time Complexity: O(n*m)
	* Space Complexity: O(m)
	* */
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum % 2 == 1) {
			return false;
		}

		boolean[] possibleSums = new boolean[(sum/2) + 1];
		possibleSums[0] = true;

		for (int num : nums) {
			for (int i = possibleSums.length - 1; i >= num; i--) {
				possibleSums[i] = possibleSums[i] || possibleSums[i - num];
			}
		}

		return possibleSums[possibleSums.length - 1];
	}
}
