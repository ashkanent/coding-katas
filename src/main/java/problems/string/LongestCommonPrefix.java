package problems.string;

/**
 * Category: Strings, Divide and Conquer
 * Problem: https://leetcode.com/problems/longest-common-prefix/
 * Level: Easy
 */
public class LongestCommonPrefix {

	/*
	* Time Complexity: O(m.n)
	* 	[1]: O(n) where n == strs.length
	* 	[2]: O(m) where m is length of shortest string in the array
	* 	[3]: O(m.n)
	* Space Complexity: O(1)
	* */
	public String solve(String[] strs) {
		if (strs.length == 0) return "";
		if (strs.length == 1) return strs[0];

		int min = Integer.MAX_VALUE;
		int minIndex = 0;

		for (int i = 0; i < strs.length; i++) {  // [1]
			if (strs[i].length() < min) {
				min = strs[i].length();
				minIndex = i;
			}
		}

		for (int i = 0; i < strs[minIndex].length(); i++) {  // [2]
			for (int j = 0; j < strs.length; j++) {  // [3]
				if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
					return strs[minIndex].substring(0, i);
				}
			}
		}

		return strs[minIndex];
	}
}
