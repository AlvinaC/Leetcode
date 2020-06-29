package com.example.programs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/555/
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/625/
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/628/
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/631/

public class MaxDepthBinTree {
	InputStream is;
	PrintWriter out;
	String INPUT = "";

	public static void main(String[] args) throws Exception {
		new MaxDepthBinTree().run();
	}

	void run() throws Exception {
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!INPUT.isEmpty())
			tr(System.currentTimeMillis() - s + "ms");
	}

	void solve() {
		for (int T = ni(); T > 0; T--) {
			TreeNode root = new TreeNode(4);
			root.left = new TreeNode(2);
			root.right = new TreeNode(3);
			root.left.left = new TreeNode(4);
			root.left.right = new TreeNode(5);
			root.right.left = new TreeNode(6);
			// int len = maxDepth(root);
			// boolean b = isValidBST(root);
			// boolean a = isSymmetric(root);
			// out.println(a);
			// invertTree(root);
			// List<List<Integer>> order = levelOrder(root);
			/*
			 * for (List<Integer> list : order) { for (Integer item : list) {
			 * System.out.print(item + " "); } System.out.println(); }
			 */
			out.print(countNodes(root));
		}
	}

	public int countNodes(TreeNode root) {
		if (root == null)
			return 0;
		TreeNode left = root.left;
		int countL = 1;
		while (left != null) {
			left = left.left;
			countL++;
		}
		TreeNode right = root.right;
		int countR = 1;
		while (right != null) {
			right = right.right;
			countR++;
		}
		if (countL == countR)
			return (int) (Math.pow(2, countL) - 1);
		return 1 + countNodes(root.left) + countNodes(root.right);

	}

	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null)
			return null;
		if (root.val == val)
			return root;
		Queue<TreeNode> q = new LinkedList<MaxDepthBinTree.TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode a = q.poll();
				if (a.val == val)
					return a;
				if (a.left != null)
					q.add(a.left);
				if (a.right != null)
					q.add(a.right);
			}
		}
		return null;
	}

	private void search(TreeNode left, int val) {

	}

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		if (left > right)
			return left + 1;
		else
			return right + 1;
	}

	public boolean isValidBST(TreeNode root) {
		return check(root, null, null);
	}

	public boolean check(TreeNode node, Integer lower, Integer upper) {
		if (node == null)
			return true;
		if (lower != null && node.val <= lower)
			return false;
		if (upper != null && node.val >= upper)
			return false;
		if (!check(node.left, lower, node.val))
			return false;
		if (!check(node.right, node.val, upper))
			return false;
		return true;
	}

	public boolean isSymmetric(TreeNode root) {
		return checkSymmetry(root, root);
	}

	private boolean checkSymmetry(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		return root1.val == root2.val && checkSymmetry(root1.left, root2.right)
				&& checkSymmetry(root1.right, root2.left);
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> a = new ArrayList<List<Integer>>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (root != null)
			q.add(root);
		while (!q.isEmpty()) {
			Integer size = q.size();
			List<Integer> k = new ArrayList<Integer>();
			while (size > 0) {
				TreeNode ele = q.poll();
				k.add(ele.val);
				if (ele.left != null)
					q.add(ele.left);
				if (ele.right != null)
					q.add(ele.right);
				size--;
			}
			a.add(k);
		}
		return a;
	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		if (root.left == null && root.right == null)
			return root;
		invertTree(root.left);
		invertTree(root.right);
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		return root;
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		return sort(nums, 0, nums.length - 1);
	}

	private TreeNode sort(int[] nums, int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(nums[mid]);
		node.left = sort(nums, start, mid - 1);
		node.left = sort(nums, mid + 1, end);
		return node;
	}

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

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private long[] nl(int n) {
		long[] a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = nl();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private void tr(Object... o) {
		if (INPUT.length() > 0)
			System.out.println(Arrays.deepToString(o));
	}
}
