package problems.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Category: Array, Math
 * Problem: https://leetcode.com/problems/plus-one/
 * Level: Easy
 */
public class PlusOne {

	/*
	* Explanation:
	* just increment the last digit, if it is '9' you should set it to '0' and increment the previous digit, o.w just
	* increment the last digit and return the element. if you go all the way to the end, then we need another digit at
	* the beginning (which will be '1'). Note that in this case all other digits must be '0'
	*
	* Time Complexity: O(n)
	*   [1]: O(n) where n = digits.length
	*
	* Space Complexity: O(n)
	*   [2]: although very unlikely, but when we get to this point we need to create another array of size n+1
	 */
	public int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {   // [1]
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}

		int[] newArray = new int[digits.length + 1];   // [2]
		newArray[0] = 1; // rest should be 0 (which already is!)
		return newArray;
	}

	// bad solution —aka my initial solution
	public int[] plusOneBad(int[] digits) {
		int carryOver, index = digits.length - 1;

		if (digits[index] < 9) {
			digits[index]++;
			return digits;
		}

		carryOver = 1;
		while (carryOver != 0 && index >= 0) {
			digits[index] += carryOver;
			carryOver = digits[index] > 9 ? 1 : 0;
			digits[index] %= 10;
			index--;
		}

		if (carryOver == 0)
			return digits;

		int[] tail = {1};
		return IntStream.concat(Arrays.stream(tail), Arrays.stream(digits)).toArray();
	}
}
