package problems.string;

/**
 * Category: String, Two Pointers
 * Problem: https://leetcode.com/problems/valid-palindrome/
 * Level: Easy
 */
public class ValidPalindrome {
	/*
	Here I include 2 solutions:
	1) my initial solution
	2) a more concise solution
	both have the exact same performance but solution 2 if practiced, can be easier to implement in interview as
	solution 1 requires being mindful of so many edge cases!
	The good thing about solution 1 is that I forgot that 'Character' can check for alphanumeric and I implemented
	my own simple version of it. It will be helpful to think outside the box during interview!
	 */

	// approach 1

	/*
	* Explanation:
	* we can potentially create a new string and omit non-alphanumeric chars but it will require O(n) space. Instead we
	* use two pointer approach from both ends, ignoring the non-alphanumeric chars and compare chars.
	*
	* Time Complexity: O(n)
	*   - we visit every element once, n == s.length()
	*
	* Space Complexity: O(1)
	 */
	public boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;

		while (i < j) {
			while (j > 0 && !isAlphanumeric(s.charAt(j))) j--;
			while (i < j && !isAlphanumeric(s.charAt(i))) i++;

			if (i > j || Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
				return !isAlphanumeric(s.charAt(i));

			i++;
			j--;
		}

		return true;
	}

	private boolean isAlphanumeric(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
	}

	// approach 2
	public boolean isPalindromeII(String s) {
		char[] c = s.toCharArray();
		for (int i = 0, j = c.length - 1; i < j; ) {
			if (!Character.isLetterOrDigit(c[i])) i++;
			else if (!Character.isLetterOrDigit(c[j])) j--;
			else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--]))
				return false;
		}
		return true;
	}
}
