package problems.DP;

/**
 * Category: DP, Divide and Conquer
 * Problem: https://leetcode.com/problems/maximum-subarray/
 * Level: Easy
 */
public class MaximumSubarray {

    /*
    * Explanation:
    * I'm using DP here and use an array called dp[] to help with that. dp[i] is the maxSubArray ending at index i (so
    * it should include num[i]). then the maximum of dp[i] (i: 1 -> n) is the answer. so the only thing is to iterate
    * on nums[] and populated dp[]. on each iteration we have to include dp[i] but we add dp[i - 1] only when it's not
    * negative
    *
    * Time Complexity: O(n)
    *
    * Space Complexity: O(n)
    *   - since we use dp[]
    *   - now if you look closely, we don't need the entire dp[] and all we need is the dp[i - 1] when we are at index
    *     i, so we can replace the dp[] with 'maxEndingHere'. look at the next method maxSubArrayII() which implements
    *     this idea.
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(dp[i], max);
        }

        return max;
    }

    // better solution: same idea, more memory efficient

    public int maxSubArrayII(int[] nums) {
        int maxEndingHere = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = nums[i] + (maxEndingHere > 0 ? maxEndingHere : 0);
            max = Math.max(maxEndingHere, max);
        }

        return max;
    }
}
