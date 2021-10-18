package problems.graph.tree.bst;

/**
 * Category: BST, Tree
 * Problem: https://leetcode.com/problems/range-sum-of-bst/
 * Level: Easy
 */
public class RangeSumOfBst {

	public class TreeNode {
	  int val;
	  TreeNode left;
	  TreeNode right;
	  TreeNode() {}
	  TreeNode(int val) { this.val = val; }
	  TreeNode(int val, TreeNode left, TreeNode right) {
	      this.val = val;
	      this.left = left;
	      this.right = right;
	  }
	}

	/*
	* Explanation:
	* here we do a modified inOrder traversal. InOrder traversal will visit BST in correct order, we modify it in 2 ways:
	* 1. don't bother visit nodes out of the range [low, high]
	* 2. when visiting root, add its value to 'res'
	* once we are done, 'res' is the final answer
	*
	* Time Complexity: O(n)
	*   - worst case, range includes the entire tree and we have to visit all nodes
	*   - here we can assume 'n' is number of nodes in the range [low, high]
	*   - we don't visit nodes outside this range
	*
	* Space Complexity: O(1)
	*   - other than the given tree nodes, we don't take additional memory space
	 */
	int res = 0;
	public int rangeSumBST(TreeNode root, int low, int high) {
		inOrder(root, low, high);
		return res;
	}

	private void inOrder(TreeNode root, int low, int high) {
		if (root.left != null && root.val > low) {
			inOrder(root.left, low, high);
		}
		if (root.val >= low && root.val <= high) {
			res += root.val;
		}
		if (root.right != null && root.val < high) {
			inOrder(root.right, low, high);
		}
	}

	// similar but easier approach:
	public int rangeSumBSTGood(TreeNode root, int L, int R) {
		if(root == null) return 0;
		if(root.val > R) return rangeSumBSTGood(root.left, L, R);
		if(root.val < L) return rangeSumBSTGood(root.right, L, R);
		return root.val + rangeSumBSTGood(root.left, L, R) + rangeSumBSTGood(root.right, L, R);
	}
}
