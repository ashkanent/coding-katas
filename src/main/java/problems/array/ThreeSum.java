package problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Category: Array, Two pointers
 * Problem: https://leetcode.com/problems/3sum/
 * Level: Medium
 */
public class ThreeSum {

	/*
	* Time Complexity: O(n^2)
	* 	[1]: O(n logn) where n == nums.length
	* 	[2]: O(n^2) the most number of elements that we may visit for
	* 		 second and third element will be 'n'
	* Space Complexity: O(n) [not sure]
	* 	[1]: O(1) for primitive types, it should be quicksort
	* 	[3]: maximum number of elements that we add to 'answer' will
	* 		 be "Cr(n)" which for "r=3" is: "n! / 3! (n-3)!" but we
	* 		 have no duplicates so my guess is that it should be
	* 		 less than "n" at all times!
	* */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> answer = new ArrayList<>();

		Arrays.sort(nums);    //  [1]

		// iterate on 'first' and for each 'first' do a bi-directional
		// sweep from both ends of remaining elements to find 'second'
		// and 'third' element that satisfy the condition:
		for (int first = 0; first < nums.length - 2; first++) {    //  [2]
			if (nums[first] > 0) break; // first element positive => not possible to find an answer
			if (first == 0 || nums[first] != nums[first - 1]) { // edge case: [0, 0, 0, 0]
				int second = first + 1;
				int third = nums.length - 1;
				while (second < third) {
					if (nums[first] + nums[second] + nums[third] == 0) {
						answer.add(Arrays.asList(nums[first], nums[second], nums[third]));    //  [3]
						while (second < third && nums[second] == nums[second + 1])
							second++;
						while (second < third && nums[third] == nums[third - 1])
							third--;
						second++;
						third--;
					} else if (nums[first] + nums[second] + nums[third] < 0) {
						second++;
					} else {
						third--;
					}
				}
			}
		}

		return answer;
	}

	/**
	 * - Easy solution to come up with
	 * - pretty inefficient but still good enough to pass the tests
	 */
	public List<List<Integer>> threeSumII(int[] nums) {
		Set<List<Integer>> answer = new HashSet<>();
		Map<Integer, Integer> num2count = new HashMap<>();

		for (int num : nums) {
			int current = num2count.computeIfAbsent(num, k -> 0);
			num2count.put(num, current + 1);
		}

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int target = -1 * (nums[i] + nums[j]);
				int minCount = 0;
				if (nums[i] == target) minCount++;
				if (nums[j] == target) minCount++;
				if (num2count.keySet().contains(target) && num2count.get(target) > minCount) {
					List<Integer> result = Arrays.asList(nums[i], nums[j], target);
					Collections.sort(result);
					answer.add(result);
				}
			}
		}

		return new ArrayList<>(answer);
	}
}
