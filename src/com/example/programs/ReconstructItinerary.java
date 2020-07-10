package com.example.programs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class ReconstructItinerary {
	InputStream is;
	PrintWriter out;
	String INPUT = "";

	public static void main(String[] args) throws Exception {
		new ReconstructItinerary().run();
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
			ArrayList<List<String>> l = new ArrayList<List<String>>();
			l.add(Arrays.asList("EZE", "AXA"));
			l.add(Arrays.asList("TIA", "ANU"));
			l.add(Arrays.asList("ANU", "JFK"));
			l.add(Arrays.asList("JFK", "ANU"));
			l.add(Arrays.asList("ANU", "EZE"));
			l.add(Arrays.asList("TIA", "ANU"));
			l.add(Arrays.asList("AXA", "TIA"));
			l.add(Arrays.asList("TIA", "JFK"));
			l.add(Arrays.asList("ANU", "TIA"));
			l.add(Arrays.asList("JFK", "TIA"));
			findItinerary(l);
		}
	}

	public List<String> findItinerary(List<List<String>> tickets) {
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, PriorityQueue<String>> adjList = new HashMap<String, PriorityQueue<String>>();
		for (List<String> l : tickets) {
			adjList.putIfAbsent(l.get(0), new PriorityQueue<String>());
			adjList.get(l.get(0)).add(l.get(1));
		}
		Stack<String> st = new Stack<String>();
		st.push("JFK");
		while (!st.isEmpty()) {
			PriorityQueue<String> l = adjList.get(st.peek());
			if (l != null) {
				String item = l.poll();
				if (item != null)
					st.push(item);
				else
					list.add(0, st.pop());
			} else
				list.add(0, st.pop());
		}
		return list;
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
