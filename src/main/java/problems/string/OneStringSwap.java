package problems.string;

/**
 * Category: String
 * Problem: https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
 * Level: Easy
 */
public class OneStringSwap {

	/*
	* Explanation:
	* Traverse through the strings and find mismatches. There has to be exactly 2 (which are the 2 chars that if you
	* swap it will give you the other string)
	*
	* Time Complexity: O(n)
	*
	* Space Complexity: O(1)
	 */
	public boolean areAlmostEqual(String s1, String s2) {
		if (s1.length() != s2.length()) return false;

		int f = -1, s = -1;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (f == -1) f = i; // first mismatch
				else if (s == -1) s = i; // second mismatch
				else return false; // more than two mismatch
			}
		}

		if (f == -1 && s == -1) return true; // equal strings
		if (f == -1 ^ s == -1) return false; // only 1 mismatch (^ -> XOR operator)
		return s1.charAt(s) == s2.charAt(f) && s1.charAt(f) == s2.charAt(s); // mismatches are mirror images
	}

	/*   Initial Solution, fast (0ms) but not recommended   */
	public boolean areAlmostEqualII(String s1, String s2) {
		if (s1.equals(s2)) return true;

		int i = 0, j = s1.length();
		while (i < j) {
			if (s1.charAt(i) != s2.charAt(i))
				break;
			i++;
		}

		if (i > j - 2)
			return false;

		while (j > i) {
			j--;
			if (s1.charAt(j) != s2.charAt(j))
				break;
		}

		if (i == j)
			return false;

		String newString = s2.substring(0, i) + s2.charAt(j) +
				s2.substring(i + 1, j) +
				s2.charAt(i) +
				s2.substring(j + 1);

		return s1.equals(newString);
	}
}
