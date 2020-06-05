package problems.DP.LIS;

/**
 * Category: Dynamic Programming, Longest Increasing Subsequence
 * Problem: https://leetcode.com/problems/longest-increasing-subsequence
 * Level: Medium
 */
public class LongestIncreasingSubsequence {

	/*
	* Time Complexity: O(n^2)
	* Space Complexity: O(n)
	* */
	public int solve(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int[] dp = new int[nums.length];
		dp[0] = 1;
		int answer = 1;

		for (int i = 1; i < nums.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
			answer = Math.max(answer, dp[i]);
		}

		return answer;
	}
}
