package com.example.programs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;

public class SurroundedRegions {
	InputStream is;
	PrintWriter out;
	String INPUT = "";

	public static void main(String[] args) throws Exception {
		new SurroundedRegions().run();
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
//			solve(new char[][] { { 'X', 'O', 'X', 'X', 'X', 'X' }, { 'X', 'O', 'X', 'X', 'O', 'X' },
//					{ 'X', 'X', 'X', 'O', 'O', 'X' }, { 'O', 'X', 'X', 'X', 'X', 'X' },
//					{ 'X', 'X', 'X', 'O', 'X', 'O' }, { 'O', 'O', 'X', 'O', 'O', 'O' } });
			solve(new char[][] {});
		}
	}

	public void solve(char[][] board) {
		if (board.length == 0)
			return;
		int m = board.length;
		int n = board[0].length;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (board[i][j] == 'O')
					board[i][j] = '-';
		for (int i = 0; i < m; i++)
			if (board[i][0] == '-')
				fill(board, i, 0, '-', 'O');
		for (int i = 0; i < n; i++)
			if (board[0][i] == '-')
				fill(board, 0, i, '-', 'O');
		for (int i = 0; i < n; i++)
			if (board[m - 1][i] == '-')
				fill(board, m - 1, i, '-', 'O');
		for (int i = 0; i < m; i++)
			if (board[i][n - 1] == '-')
				fill(board, i, n - 1, '-', 'O');
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (board[i][j] == '-')
					board[i][j] = 'X';
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				out.print(board[i][j]);
			out.println();
		}
	}

	public void fill(char[][] board, int x, int y, char old, char now) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[x].length)
			return;
		if (board[x][y] != old)
			return;
		board[x][y] = now;
		fill(board, x + 1, y, old, now);
		fill(board, x - 1, y, old, now);
		fill(board, x, y + 1, old, now);
		fill(board, x, y - 1, old, now);
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
