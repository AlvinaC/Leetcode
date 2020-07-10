package com.example.programs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/543/week-5-june-29th-june-30th/3376/
//Complexity =O(nm * nm) where n and m are the grid size
public class WordSearchII {
	InputStream is;
	PrintWriter out;
	String INPUT = "";

	public static void main(String[] args) throws Exception {
		new WordSearchII().run();
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
			findWords(new char[][] { { 'a', 'b' } }, new String[] { "ba" });
		}
	}

	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<String>();
		if (words.length == 0)
			return res;
		Trie root = new Trie('/');
		for (int i = 0; i < words.length; i++)
			root.insert(words[i]);
		int row = board.length;
		int col = board[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				solveDFS(root, i, j, board, res);
			}
		}
		return res;
	}

	private void solveDFS(Trie root, int i, int j, char[][] board, List<String> res) {
		int index = board[i][j] - 'a';
		if (board[i][j] == '$' || root.child[index] == null)
			return;
		root = root.child[index];
		if (root.end > 0) {
			res.add(root.word);
			root.end--;
		}
		char temp = board[i][j];
		board[i][j] = '$';
		if (j + 1 < board[0].length)// right
			solveDFS(root, i, j + 1, board, res);
		if (i + 1 < board.length)// down
			solveDFS(root, i + 1, j, board, res);
		if (j - 1 >= 0)// left
			solveDFS(root, i, j - 1, board, res);
		if (i - 1 >= 0)// up
			solveDFS(root, i - 1, j, board, res);
		board[i][j] = temp;
	}

	class Trie {
		char a;
		Trie[] child = new Trie[26];
		int end = 0;
		String word = "";

		public Trie(char temp) {
			a = temp;
			Arrays.fill(child, null);
		}

		public void insert(String temp) {
			Trie node = this;
			for (int i = 0; i < temp.length(); i++) {
				int index = temp.charAt(i) - 'a';
				if (node.child[index] == null) {
					node.child[index] = new Trie(temp.charAt(i));
				}
				node = node.child[index];
			}
			node.end++;
			node.word = temp;
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
