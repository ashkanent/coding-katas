package problems.string;

import java.util.Stack;

/**
 * Category: String, Stack
 * Problem: https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Level: Medium
 */
public class MinRemoveToMakeValid {

	/*
	* Explanation:
	* Overall, making valid parenthesis is much easier with stacks. Here we use the same idea with some
	* modifications to solve the problem. We iterate on input and add chars to StringBuilder, if they
	* are '(' or ')', we add them to stack unless they are invalid. It is easy to find invalid ')' so we
	* don't add them to stack and at the end if there are '(' remaining, they are all invalid (since we
	* couldn't find ')' for them, so they are not popped!)
	*
	* Time Complexity: O(n^2)
	*   - this is absolute worst case, on average it is O(n)
	*   [3]: O(n) where n = input.length()
	*   [4]: if all of input is '(' then sb (result) will be '(((..(('
	*        each deleteCharAt() is O(n) so overall it can be O(n^2)
	*
	* Space Complexity: O(n)
	*   [1]: O(n)
	*   [2]: O(n)
	 */
	public String minRemoveToMakeValid(String input) {
		StringBuilder result = new StringBuilder();    // [1]
		Stack<Integer> s = new Stack<>(); // index of '('  - [2]
		Integer index = 0;

		for (char c : input.toCharArray()) {  // [3]
			if (c == '(') {
				result.append(c);
				s.push(index);
				index++;
			} else if (c == ')') {
				if (!s.empty()) {
					s.pop();
					result.append(c);
					index++;
				}
			} else {
				result.append(c); // it's a letter
				index++;
			}
		}


		// since we delete the string from right to left, we won't
		// get issues with remaining elements having their indices
		// updated (e.g  x[0]y[1]z[2] ---remove[1]---> x[0]z[1])
		while (!s.empty()) {       // [4]
			result.deleteCharAt(s.pop());
		}

		return result.toString();
	}
}
