package problems.DP.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Categories: HashMaps, Brute-Force, Sliding Window
 * Problem: https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Level: Medium
 */
public class FindAnagrams {
	/*
	* Time Complexity: O(n)
	* 		[2]: O(m) if m is p.length()
	* 		[3]: O(n) if n is s.length()
	* 			not sure about this but although we have two nested loops
	* 			here, the second one visit n elements maximum and 'begin'
	* 			doesn't reset (it just increases up to 'n')
	* Space Complexity: O(m)
	* 		[1]: O(m) if m is p.length()
	* */
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new LinkedList<>();
		if (p.length() > s.length()) {
			return result;
		}

		Map<Character, Integer> stillNeeds = new HashMap<>();    // [1]
		for (char c : p.toCharArray()) {    // [2]
			stillNeeds.put(c, stillNeeds.getOrDefault(c, 0) + 1);
		}
		int counter = stillNeeds.size();

		int begin = 0, end = 0;

		while (end < s.length()) {    // [3]
			char c = s.charAt(end);
			if (stillNeeds.containsKey(c)) {
				stillNeeds.put(c, stillNeeds.get(c) - 1);
				if (stillNeeds.get(c) == 0) {
					counter--;
				}
			}
			end++;

			while (counter == 0) {
				char tempc = s.charAt(begin);
				if (stillNeeds.containsKey(tempc)) {
					stillNeeds.put(tempc, stillNeeds.get(tempc) + 1);
					if (stillNeeds.get(tempc) > 0) {
						counter++;
					}
				}
				if (end - begin == p.length()) {
					result.add(begin);
				}
				begin++;
			}

		}
		return result;
	}


	// Optimized brute-force algorithm:

	public List<Integer> findAnagramsII(String s, String p) {
		List<Integer> result = new ArrayList<>();

		if (p.length() > s.length()) {
			return result;
		}

		// make a map of "p" characters:
		Map<Character, Integer> pChars = new HashMap<>();
		for (int i = 0; i < p.length(); i++) {
			Integer occurances = pChars.computeIfAbsent(p.charAt(i), k -> 0);
			pChars.put(p.charAt(i), occurances + 1);
		}

		for (int i = 0; i <= s.length() - p.length(); i++) {
			int anagramIndex = isAnagram(s, p, i, new HashMap<>(pChars));
			if (anagramIndex == -1) {
				result.add(i);
			} else if (anagramIndex > i) {
				i = anagramIndex;
			}
		}

		return result;
	}

	/*
	 * "-1": anagram found at given index
	 * "-2": no anagram
	 * "positive integer": skip to this index
	 */
	private int isAnagram(String main, String pattern, int mainIndex, HashMap<Character, Integer> pChars) {
		for (int i = mainIndex; i < mainIndex + pattern.length(); i++) {
			Integer current = pChars.get(main.charAt(i));
			if (current == null) {
				return i;
			} else if (current == 0) {
				return -2;
			} else {
				pChars.put(main.charAt(i), current - 1);
			}
		}

		// check if all remainings are 0
		for (int remaining : pChars.values()) {
			if (remaining != 0) {
				return -2;
			}
		}

		return -1; // to signal we have found an anagram
	}
}
