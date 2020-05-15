package com.example.programs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

//https://leetcode.com/explore/featured/card/top-interview-questions-easy/93/linked-list/603/

public class LinkedListEasy {
	InputStream is;
	PrintWriter out;
	String INPUT = "";

	ListNode head;

	public static void main(String[] args) throws Exception {
		new LinkedListEasy().run();
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

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	void solve() {
		for (int T = ni(); T > 0; T--) {
			head = null;
			ListNode one = new ListNode(1);
			ListNode two = new ListNode(2);
			ListNode three = new ListNode(3);
			ListNode four = new ListNode(4);
			head = one;
			one.next = two;
			two.next = three;
			three.next = null;
			four.next = null;

			ListNode head1 = null;
			ListNode oner = new ListNode(5);
			ListNode twor = new ListNode(5);
			ListNode threer = new ListNode(6);
			ListNode fourr = new ListNode(4);
			head1 = oner;
			oner.next = null;
			twor.next = null;
			threer.next = null;
			fourr.next = null;

			hasCycle(head1);
		}
	}

	public void deleteNode(ListNode node) {

	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		int i = 0;
		ListNode temp = head;
		ListNode remove = head;
		while (i != n) {
			i++;
			temp = temp.next;
		}
		if (temp == null) {
			head = head.next;
		} else {
			while (temp.next != null) {
				temp = temp.next;
				remove = remove.next;
			}
			remove.next = remove.next.next;
		}
		return head;
	}

	public ListNode reverseList(ListNode head) {
		ListNode one = head;
		ListNode two = null;
		if (head != null)
			two = head.next;
		if (two != null) {
			head.next = null;
			while (two != null) {
				one = two.next;
				two.next = head;
				head = two;
				two = one;
			}
			one = head;
			while (one != null) {
				out.println(one.val);
				one = one.next;
			}
		}
		return head;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = null;
		ListNode last = null;
		while (l1 != null && l2 != null) {
			if (l1.val == l2.val) {
				ListNode a = new ListNode(l1.val);
				ListNode b = new ListNode(l2.val);
				a.next = b;
				if (head == null)
					head = a;
				if (last != null)
					last.next = a;
				last = b;
				l1 = l1.next;
				l2 = l2.next;
				continue;
			} else if (l1.val < l2.val) {
				ListNode a = new ListNode(l1.val);
				if (last != null)
					last.next = a;
				last = a;
				l1 = l1.next;
				if (head == null)
					head = a;
			} else {
				ListNode a = new ListNode(l2.val);
				if (last != null)
					last.next = a;
				last = a;
				l2 = l2.next;
				if (head == null)
					head = a;
			}
		}
		if (l1 != null) {
			while (l1 != null) {
				ListNode a = new ListNode(l1.val);
				if (head == null)
					head = a;
				if (last != null)
					last.next = a;
				last = a;
				l1 = l1.next;
			}
		}
		if (l2 != null) {
			while (l2 != null) {
				ListNode a = new ListNode(l2.val);
				if (head == null)
					head = a;
				if (last != null)
					last.next = a;
				last = a;
				l2 = l2.next;
			}
		}
		if (last != null)
			last.next = null;
		return head;
	}

	public boolean isPalindrome(ListNode head) {
		ListNode fast = null;
		ListNode slow = null;
		try {
			fast = head.next.next;
			slow = head;
		} catch (Exception e) {
			if (head == null)
				return true;
			else if (head.next == null) {
				return true;
			} else {
				if (head.val == head.next.val)
					return true;
				else
					return false;
			}
		}

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode temphead = null;
		if (fast == null) {
			temphead = slow.next;
		} else
			temphead = slow.next.next;
		slow.next = null;
		ListNode one = temphead.next;
		ListNode two = null;
		temphead.next = null;
		while (one != null) {
			two = one.next;
			one.next = temphead;
			temphead = one;
			one = two;
		}
		while (temphead != null && head != null) {
			if (temphead.val == head.val) {
				temphead = temphead.next;
				head = head.next;
			} else {
				out.println(false);
				return false;
			}
		}
		out.println(true);
		return true;
	}

	public boolean hasCycle(ListNode head) {
		ListNode fast = null;
		ListNode slow = null;
		try {
			fast = head.next.next;
			slow = head;
		} catch (Exception e) {
			return false;
		}
		try {
			while (slow != fast) {
				slow = slow.next;
				fast = fast.next.next;
			}
			return true;
		} catch (Exception e) {
			return false;
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
