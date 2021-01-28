package problems.string;

/**
 * Category: String
 * Problem: https://leetcode.com/problems/valid-palindrome-ii/
 * Level: Easy
 */
public class ValidPalindromeII {

	/*
	 * Explanation: we move from left and right towards the middle until we see a mismatch.
	 * 	Then we either eliminate (1) left char or (2) right char and then the remainder of
	 * 	string should be palindrome.
	 *
	 * Time Complexity: O(n)
	 * 				- where 'n' is s.length()
	 * Space Complexity: O(1)
	 * */
	public boolean validPalindrome(String s) {
		int r = s.length() - 1;
		int l = 0;

		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return isStrictlyPalindrome(s, l+1, r) || isStrictlyPalindrome(s, l, r-1);
			}
			l++;
			r--;
		}

		return true;
	}

	// no exception
	private boolean isStrictlyPalindrome(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
}
