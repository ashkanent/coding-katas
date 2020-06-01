package problems.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Category: Arrays, Priority Queue, Greedy
 * Problem: https://leetcode.com/problems/queue-reconstruction-by-height/
 * Level: Medium
 */
public class QueueReconstruction {
	public int[][] solve(int[][] people) {

		if (people.length < 2) {
			return people;
		}

		// sort 1) descending based on height, if tie 2) ascending based on 'k'
		Arrays.sort(people, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);

		List<int[]> answer = new LinkedList<>();

		// now every 'k' we see, it is the final location in queue:
		for (int i = 0; i < people.length; i++) {
			answer.add(people[i][1], people[i]);
		}

		return answer.toArray(new int[people.length][]);
	}

	// dumb solution with priorityqueue and implementing linkedlist order
	// memory usage < 98% of submissions
	// runtime < 17% of submissions
	public int[][] solve2(int[][] people) {

		if (people.length < 2) {
			return people;
		}


		Queue<int[]> pq = new PriorityQueue<>(people.length, (a, b) -> {
			if (a[0] == b[0])
				return a[1] -  b[1];
			else
				return b[0] - a[0];
		});

		// populate the queue:
		for (int i = 0; i < people.length; i++) {
			pq.add(people[i]);
		}

		int[][] temp = new int[people.length][2];
		int[][] answer = new int[people.length][2];
		int[] order = new int[people.length];

		for (int i = 0; i < people.length; i++) {
			// populate the temp for further reference
			int[] qhead = pq.poll();
			temp[i][0] = qhead[0];
			temp[i][1] = qhead[1];

			// calculate current element's order
			order[i] = temp[i][1];
			for (int j = 0; j < i; j++) {
				if (order[j] >= order[i])
					order[j]++;
			}
		}

		// iterate on "order" and construct answer:
		for (int i = 0; i < order.length; i++) {
			answer[order[i]] = temp[i];
		}

		return answer;
	}
}
