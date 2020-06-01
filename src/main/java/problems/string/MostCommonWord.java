package problems.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Category: String
 * Problem: https://leetcode.com/problems/most-common-word/
 * Level: Easy
 */
public class MostCommonWord {

	/*
     Time Complexity: O(n)
        [1] should be O(n), when n == paragraph.length()
        [3] O(m) where m == banned.length && m <= n
        [4] O(n): if 'paragraph' has length of 'n', number of words will be < n
        [5] O(1) on average case and O(log n) in worst case
        [6] O(1)

     Space Complexity: O(n)
        [1] O(n) if n is length of paragraph, we'll have less than n words
        [2] O(n) if n is length of paragraph, we'll have less than n words
         -  other variables are negligible
    */
	public String solve2(String paragraph, String[] banned) {
		// "\\W+": match one or more 'non-letter' characters:
		String[] words = paragraph.toLowerCase().split("\\W+"); //  [1]

		Map<String, Integer> wordCount = new HashMap<>(); //  [2]
		Set<String> bannedWords = new HashSet<>(Arrays.asList(banned)); //  [3]
		// the "maxOccurrence" can be used in case there is a tie to
		// print all words with maximum occurrence:
		Integer maxOccurrence = 0;
		String answer = "";

		for (String word : words) {  //  [4]
			if (!bannedWords.contains(word)) {  //  [5]
				int currentOccurrence = wordCount.computeIfAbsent(word, k -> 0); //  [6]
				wordCount.put(word, ++currentOccurrence);
				if (currentOccurrence > maxOccurrence) {
					maxOccurrence = currentOccurrence;
					answer = word;
				}
			}
		}

		return answer;
	}


	// Here I didn't use RegEx and made it complicated
	public String solve(String paragraph, String[] banned) {
		String newParagraph = replaceSpecialChars(paragraph.toLowerCase());
		String[] words = newParagraph.split(" ");

		Map<String, Integer> count = new HashMap<>();
		Integer maxCount = 0;
		String answer = "";
		count.put("", -1);
		for (String ban : banned) {
			count.put(ban.toLowerCase(), -1);
		}
		for (int i = 0; i < words.length; i++) {
			Integer cur = count.computeIfAbsent(words[i], k -> 0);
			if (cur != -1) {
				// not a banned word
				count.put(words[i], ++cur);
				if (cur > maxCount) {
					maxCount = cur;
					answer = words[i];
				}
			}
		}

		return answer;
	}

	// make it all lower case first and then replace non alphabets with space
	private String replaceSpecialChars(String paragraph) {
		StringBuilder newParagraph = new StringBuilder(paragraph);
		for (int i = 0; i < paragraph.length(); i++) {
			if (isSpecialChar(paragraph.charAt(i))) {
				newParagraph.setCharAt(i, ' ');
			} else {
				newParagraph.setCharAt(i, paragraph.charAt(i));
			}
		}
		return newParagraph.toString();
	}

	private boolean isSpecialChar(char c) {
		if (c >= 'a' && c <= 'z') {
			return false;
		} else if (c >= 'A' && c <= 'Z') {
			return false;
		}
		return true;
	}
}
