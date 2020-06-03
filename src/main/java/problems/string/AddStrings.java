package problems.string;

/**
 * Category: String
 * Problem: https://leetcode.com/problems/add-strings
 * Level: Easy
 */
public class AddStrings {

	/*
	* Time Complexity: O(n)
	* Space Complexity: O(n) where n is length of returned sum
	* */
	public String solve(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		int num1Index = num1.length() - 1;
		int num2Index = num2.length() - 1;
		char c1, c2, carryOver = '0';
		int sum;

		while (num1Index >= 0 || num2Index >= 0) {
			c1 = (num1Index > -1) ? num1.charAt(num1Index) : '0';
			c2 = (num2Index > -1) ? num2.charAt(num2Index) : '0';
			sum = (c1 - '0') + (c2 - '0') + (carryOver - '0');

			carryOver = sum > 9 ? '1' : '0';

			sb.append(sum % 10);

			num1Index--;
			num2Index--;
		}

		if (carryOver == '1') {
			sb.append("1");
		}

		return sb.reverse().toString();
	}
}
