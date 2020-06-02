package problems.string;

import java.util.Arrays;

/**
 * Category: String
 * Problem: https://leetcode.com/problems/reorder-data-in-log-files/
 * Level: Easy
 */
public class ReorderLogs {

	/*
	* Time Complexity: O(n log n) where 'n' is logs.length
	*  [1]: O(m) where 'm' is size of each log, since in problem it is stated that m < 100, we consider it O(1)
	*   -  everything else in comparator is constant time
	*
	* Space Complexity: O(n)
	*  [1]: since String length is <100, it is constant but we do create it n times (for all the logs)
	*  [2]: since types are non-primitive, Arrays.sort() should use merge sort which needs O(m*n) add-
	*       itional space where 'm' is size of each log (negligible) => O(n)
	* */
	public String[] solve(String[] logs) {
		Arrays.sort(logs, (a, b) -> {    // [2]
			// break into two parts: 0) identifier 1) logs
			String[] log1 = a.split(" ", 2);  // [1]
			String[] log2 = b.split(" ", 2);

			boolean isDigit1 = Character.isDigit(log1[1].charAt(0));
			boolean isDigit2 = Character.isDigit(log2[1].charAt(0));

			if (!isDigit1 && !isDigit2) {
				// both letter-logs, sort lexicographically
				int compare = log1[1].compareTo(log2[1]);
				if (compare == 0) return log1[0].compareTo(log2[0]);
				return compare;
			} else if (!isDigit1 && isDigit2) {
				// letter - digit => 'letters' shoud be considered smaller than 'digits'
				return -1;
			} else if (isDigit1 && !isDigit2) {
				// digit - letter => reverse the order
				return 1;
			} else {
				// digit - digit => keep the order
				return 0;
			}
		});

		return logs;
	}
}
