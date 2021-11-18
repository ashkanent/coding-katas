package problems.graph.tree.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Category: Binary Tree, String, DFS, BFS
 * Problem: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Level: Hard
 */
public class SerializeDeserializeTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}


	// First solution, not recommended as coding it is more involved. Better solution follows

	/*
	* Explanation:
	* First of all, this problem asks us to implement two methods: serialize() and deserialize(). It will
	* call them in order on different trees to make sure final deserialized tree is equal to the input tree.
	* In my first approach, I use level order traversal (BFS) to serialize and then I try to deserialize it in the same
	* fashion. We use a list of "TreeNode"s with multiple pointers to keep track of parents, children, begining of each
	* level, etc. (that's why it's a bit complicated, but the logic is very simple).
	* The main thing is to understand the pointers (and make sure we handle them properly):
	* index: position of parent. increment it every two nodes we visit (binary tree)
	* counter: number of real nodes (non-null) that we add in each level
	* children: real nodes x 2 (binary tree, so we have 2*counter more nodes to visit in next level of the tree)
	* levelStart: index of the starting node (left most child) in the curren level we are visiting
	*       note: it's actually "levelStart - 1" or "prevLevelEnd"
	*
	* Time Complexity: O(n)
	*   - n is size of the tree
	*   - we will visit every node twice (2n)
	*
	* Space Complexity: O(n)
	*   - we need a queue of size n
	*   - we need a list of size n
	*   - all of these combined are still O(n)
	 */

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) return "";

		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode current = q.poll();
			if (current == null) {
				sb.append("n,");
				continue;
			}
			sb.append(current.val);
			sb.append(',');

			q.offer(current.left);
			q.offer(current.right);
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.isEmpty()) return null;

		List<TreeNode> nodes = new ArrayList<>();
		int index = 0, children = 2, counter = 0, levelStart = 0;
		String[] vals = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(vals[index]));
		nodes.add(root);
		while (levelStart < vals.length) {
			for (int i = 1; i <= children; i++) {
				if (levelStart + i >= vals.length) {
					// we are done!
					return nodes.get(0);
				}
				if (!vals[levelStart + i].contains("n")) {
					TreeNode current = new TreeNode(Integer.parseInt(vals[levelStart + i]));
					nodes.add(current);
					counter++;
					if ((i & 1) == 1)
						nodes.get(index).left = current;
					else
						nodes.get(index).right = current;
				} else {
					if (nodes.get(index) != null) {
						if ((i & 1) == 1)
							nodes.get(index).left = null;
						else
							nodes.get(index).right = null;
					}
				}
				// increase 'index' (parent) every 2 children:
				if ((i & 1) == 0) index++;
			}
			if (counter == 0)
				break; // no new nodes added
			levelStart += children;
			children = counter * 2;
			counter = 0;
		}

		return nodes.get(0);
	}





	// Following is a solution by @gavinlinasd (plus some small adjustments)
	// it is easier to understand and implement

	/*
	* Explanation:
	* Unlike above solution, we use DFS approach to both serialize and deserialize. We do pre-order traversal to
	* serialize and then build the tree.
	 */


	private static final String splitter = ",";
	private static final String NN = "X";

	// Encodes a tree to a single string.
	public String serializeII(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	private void buildString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append(NN).append(splitter);
		} else {
			sb.append(node.val).append(splitter);
			buildString(node.left, sb);
			buildString(node.right,sb);
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserializeII(String data) {
		Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(splitter)));
		return buildTree(nodes);
	}

	private TreeNode buildTree(Queue<String> nodes) {
		String val = nodes.poll();
		if (val.equals(NN)) return null;
		TreeNode node = new TreeNode(Integer.parseInt(val));
		node.left = buildTree(nodes);
		node.right = buildTree(nodes);
		return node;
	}
}
