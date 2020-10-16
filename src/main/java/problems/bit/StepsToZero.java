package problems.bit;

/**
 * Category: Bit Manipulation
 * Problem: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 * Level: Easy
 */
public class StepsToZero {
	/*
	* Time Complexity: O(1)
	* 		- biggest input is 10^6 which is less than 2^20, so we
	* 		  will loop at most around 20 times
	* Space Complexity: O(1)
	* */
	public int numberOfSteps(int num) {
		if (num == 0)
			return 0;
		int steps = 0;
		while (num > 1) {
			if ((num & 1) == 1) {
				num -= 1;
				steps++;
			}
			num >>= 1;
			steps++;
		}
		return steps + 1;
	}
}
