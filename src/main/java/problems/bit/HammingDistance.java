package problems.bit;

/**
 * Category: Bit Manipulation
 * Problem: https://leetcode.com/problems/hamming-distance/
 * Level: Easy
 */
public class HammingDistance {

	public int solve(int x, int y) {
		return Integer.bitCount(x ^ y);
	}

	public int solve2(int x, int y) {
		 int result = x ^ y;
		 int counter = 0;
		 while (result > 0) {
		     if (result % 2 == 1) {
		         counter++;
		     }
		     result = result >> 1;
		 }
		 return counter;
	}
}
