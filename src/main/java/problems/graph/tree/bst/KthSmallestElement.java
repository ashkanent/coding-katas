package problems.graph.tree.bst;

/**
 * Category: Tree, BST, In Order Traversal, DFS
 * Problem: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * Level: Medium
 */
public class KthSmallestElement {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	/*
	* Time Complexity: O(k + h) where 'h' is height of tree
	* Space Complexity: O(1)
	* */
	public int solve(TreeNode root, int k) {
		int[] arr = {0, -1}; // current + result
		inOrder(root, k, arr);
		return arr[1];
	}

	public boolean inOrder(TreeNode root, int target, int[] arr) {
		if (root.left != null) {
			boolean found = inOrder(root.left, target, arr);
			if (found) {
				return true;
			}
		}
		arr[0]++;
		if (target == arr[0]) {
			arr[1] = root.val;
			return true;
		}
		if (root.right != null) {
			boolean found = inOrder(root.right, target, arr);
			if (found) {
				return true;
			}
		}
		return false;
	}
}
