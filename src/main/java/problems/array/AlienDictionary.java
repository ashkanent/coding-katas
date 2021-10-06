package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Category: Arrays, String, Hash Table
 * Problem: https://leetcode.com/problems/verifying-an-alien-dictionary/
 * Level: Easy
 */
public class AlienDictionary {

	/*
	* Time Complexity: O(n)
	*   [1]: O(1) since order.length == 26
	*   [2]: O(n) where n = words.length
	*   [3]: O(1) since each word is less than 20 characters
	*   [4]: O(1) lookups from the HashMap
	*
	* Space Complexity: O(1)
	*   - the HashMap has always 26 entries: O(1)
	 */
	public boolean isAlienSorted(String[] words, String order) {
		boolean result;
		Map<Character, Integer> letterToRank = new HashMap<>();

		for (int i = 0; i < order.length(); i++) {         // [1]
			letterToRank.put(order.charAt(i), i);
		}

		for (int i = 0; i < words.length - 1; i++) {         // [2]
			result = compare(words[i], words[i+1], letterToRank);
			if (!result)
				return false;
		}
		return true;
	}

	private boolean compare(String w1, String w2, Map<Character, Integer> letterToRank) {
		for (int i = 0; i < Math.min(w1.length(), w2.length()); i++) {         // [3]
			if (letterToRank.get(w1.charAt(i)) > letterToRank.get(w2.charAt(i))) {         // [4]
				return false;
			} else if (letterToRank.get(w1.charAt(i)) < letterToRank.get(w2.charAt(i))) {
				return true;
			}
		}

		return w1.length() <= w2.length();
	}
}
