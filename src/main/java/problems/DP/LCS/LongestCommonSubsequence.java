package problems.DP.LCS;

/**
 * Category: Dynamic Programming, Longest Common Subsequence
 * Problem: https://leetcode.com/problems/longest-common-subsequence/
 * Level: Medium
 */
public class LongestCommonSubsequence {

	/*
	* Time Complexity: O(m*n)
	* 		[2]: O(m) assuming m is the length of longer input
	* 		[3]: O(m*n) this is a nested for loop, we assume that m and n
	* 			 are length of the two inputs
	* Space Complexity: O(m*n)
	* 		[1]: a 2D array that uses O(m*n) space
	* */
	public int longestCommonSubsequence(String text1, String text2) {

		if (text1.length() == 1 && text2.length() == 1) {
			if (text1.equals(text2)) {
				return 1;
			} else {
				return 0;
			}
		}

		int[][] dp = new int[text1.length()][text2.length()];      // [1]

		// initialize "dp"
		if (text1.charAt(0) == text2.charAt(0)) {
			dp[0][0] = 1;
		} else {
			dp[0][0] = 0;
		}
		for (int i = 1; i < Math.max(text1.length(), text2.length()); i++) {      // [2]
			if (i < text1.length()) {
				if (text1.charAt(i) == text2.charAt(0)) {
					dp[i][0] = 1;
				} else {
					dp[i][0] = Math.max(0, dp[i-1][0]);
				}
			}
			if (i < text2.length()) {
				if (text1.charAt(0) == text2.charAt(i)) {
					dp[0][i] = 1;
				} else {
					dp[0][i] = Math.max(0, dp[0][i-1]);
				}
			}
		}

		for (int i = 1; i < dp.length; i++) {      // [3]
			for (int j = 1; j < dp[0].length; j++) {
				if (text1.charAt(i) == text2.charAt(j)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[text1.length()-1][text2.length()-1];
	}


	/*
	* This method attempts to do the same but in a way that we don't need
	* the initialization part. in this method index 0 is not first character
	* but it's character 0 (or length of zero). so we change couple of things
	* such as:
	* - length of dp array (check below)
	* - ith character in the loop is `text1.charAt(i - 1)`
	*
	* time complexity is still the same, the code is just cleaner
	* */
	public int longestCommonSubsequenceII(String text1, String text2) {

		if (text1.length() == 1 && text2.length() == 1) {
			if (text1.equals(text2)) {
				return 1;
			} else {
				return 0;
			}
		}

		int[][] dp = new int[text1.length() + 1][text2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[text1.length()][text2.length()];
	}
}
