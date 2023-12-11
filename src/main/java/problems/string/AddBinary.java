package problems.string;

/**
 * Category: String, Math, Bit Manipulation
 * Problem: https://leetcode.com/problems/add-binary/
 * Level: Easy
 */
public class AddBinary {

	// approach 1 - better solution:
	/*
	* Explanation:
	* have pointer on each string and start from last to first and add each element. At the end if there is a carry over,
	* add it as well. Note: StringBuilder has insert(0, char) which is O(n) so instead of calling it each time, we append
	* and reverse the string at the end.
	*
	* Time Complexity: O(n)
	*   [2]: O(n) where n == Max(a.len, b.len)
	*   [3]: O(n) because of the 'reverse()'
	*
	* Space Complexity: O(n)
	*   [1]: O(n) where n == Max(a.len, b.len)
	 */
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();  // [1]
		int i = a.length() - 1, j = b.length() - 1, carry = 0; // two pointers starting from the back, just think of adding two regular ints from you add from back
		while (i >= 0 || j >= 0) {   // [2]
			int sum = carry;
			if (j >= 0) sum += b.charAt(j--) - '0'; // we subtract '0' to get the int value of the char from the ascii
			if (i >= 0) sum += a.charAt(i--) - '0';
			sb.append(sum % 2);
			carry = sum / 2;
		}
		if (carry != 0) sb.append(carry); // leftover carry, add it
		// sb doesn't have 'prepend' but has insert(0, char) which is O(n), so we append and reverse:
		return sb.reverse().toString();   // [3]
	}

	// approach 2 - initial solution:
	public String addBinaryII(String a, String b) {
		StringBuilder sb = new StringBuilder();
		char[] sum;
		int aLen = a.length(), bLen = b.length();
		char carryOver = '0';
		int i;

		for (i = 1; i <= Math.min(aLen, bLen); i++) {
			sum = add(a.charAt(aLen - i), b.charAt(bLen - i), carryOver);
			sb.append(sum[0]);
			carryOver = sum[1];
		}

		if (aLen > bLen) {
			while (i <= aLen) {
				sum = add(a.charAt(aLen - i), '0', carryOver);
				sb.append(sum[0]);
				carryOver = sum[1];
				i++;
			}
		} else if (aLen < bLen) {
			while (i <= bLen) {
				sum = add(b.charAt(bLen - i), '0', carryOver);
				sb.append(sum[0]);
				carryOver = sum[1];
				i++;
			}
		}
		if (carryOver == '1') {
			sb.append('1');
		}

		return sb.reverse().toString();
	}

	// return array of size 2: {res, carryOver}
	private char[] add(char a, char b, char c) {
		int count = 0;
		if (a == '1') count++;
		if (b == '1') count++;
		if (c == '1') count++;
		if (count == 0)
			return new char[]{'0', '0'};
		if (count == 1)
			return new char[]{'1', '0'};
		if (count == 2)
			return new char[]{'0', '1'};
		// count == 3
		return new char[]{'1', '1'};
	}

	// approach 3 - Dec 8, 2023 - 1ms runtime (beats 100%) - 40.7MB (beats 94.61%)
	// almost identical to solution I
	public String addBinaryIII(String a, String b) {
		int maxLength = Math.max(a.length(), b.length());
		int aLenAdj = maxLength - a.length();
		int bLenAdj = maxLength - b.length();
		StringBuilder result = new StringBuilder();
		char currA, currB;
		int carryOver = 0, currentSum = 0;

		for (int i = maxLength - 1; i > -1; i--) {
			currA = (i - aLenAdj) < 0 ? '0' : a.charAt(i - aLenAdj);
			currB = (i - bLenAdj) < 0 ? '0' : b.charAt(i - bLenAdj);
			currentSum = carryOver + (currA - '0') + (currB - '0');
			carryOver = currentSum > 1 ? 1 : 0;
			result.append((currentSum % 2 == 0) ? '0' : '1');
		}

		if (carryOver > 0) {
			result.append('1');
		}

		return result.reverse().toString();
	}
}
