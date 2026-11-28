package com.example.programs;

import java.util.ArrayList;
import java.util.Stack;

public class BinarySearchTreeIteratorII {
    static class TreeNode {
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

    Stack<TreeNode> s;
    ArrayList<TreeNode> p;
    int index = -1;

    public BinarySearchTreeIteratorII(TreeNode root) {
        s = new Stack<>();
        p = new ArrayList<>();
        this.move(root);
    }

    private void move(TreeNode root) {
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return p.size() >= index && !s.isEmpty();
    }

    public int next() {
        index++;
        if (index == p.size()) {
            TreeNode a = s.pop();
            p.add(a);
            this.move(a.right);
        }
        return p.get(index).val;
    }

    public boolean hasPrev() {
        return index - 1 >= 0 && p.size() >= index - 1;
    }

    public int prev() {
        index--;
        return p.get(index).val;
    }

    public static void main(String[] args) {
        // Builds BST:
        //      7
        //     / \
        //    3   15
        //       /  \
        //      9    20
        TreeNode root = new TreeNode(
                7,
                new TreeNode(3),
                new TreeNode(15, new TreeNode(9), new TreeNode(20))
        );
        BinarySearchTreeIteratorII it = new BinarySearchTreeIteratorII(root);

        assert it.next() == 3;
        assert it.next() == 7;
        assert it.prev() == 3;
        assert it.next() == 7;
        assert it.hasNext();
        assert it.next() == 9;
        assert it.next() == 15;
        assert it.next() == 20;
        assert !it.hasNext();
        assert it.hasPrev();
        assert it.prev() == 15;
        System.out.println("All assertions passed!");

        testSingleNode();
        testLeftSkewedTree();
        testRightSkewedTree();
        testNegativeAndZeroValues();
        testRepeatedDirectionSwitches();
        System.out.println("All test cases passed!");
    }

    // 1. Single node tree: [10]
    private static void testSingleNode() {
        TreeNode root = new TreeNode(10);
        BinarySearchTreeIteratorII it = new BinarySearchTreeIteratorII(root);

        assert !it.hasPrev();
        assert it.hasNext();
        assert it.next() == 10;
        assert !it.hasNext();
        assert !it.hasPrev(); // Only 1 element total, so no previous element exists
    }

    // 2. Left-skewed tree: 4 -> 3 -> 2 -> 1
    private static void testLeftSkewedTree() {
        TreeNode root = new TreeNode(4,
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(1), null), null), null);
        BinarySearchTreeIteratorII it = new BinarySearchTreeIteratorII(root);

        assert it.next() == 1;
        assert it.next() == 2;
        assert it.next() == 3;
        assert it.next() == 4;
        assert !it.hasNext();

        assert it.prev() == 3;
        assert it.prev() == 2;
        assert it.prev() == 1;
        assert !it.hasPrev();
    }

    // 3. Right-skewed tree: 1 -> 2 -> 3 -> 4
    private static void testRightSkewedTree() {
        TreeNode root = new TreeNode(1, null,
                new TreeNode(2, null,
                        new TreeNode(3, null,
                                new TreeNode(4))));
        BinarySearchTreeIteratorII it = new BinarySearchTreeIteratorII(root);

        assert it.next() == 1;
        assert !it.hasPrev();
        assert it.next() == 2;
        assert it.prev() == 1;
        assert it.next() == 2;
        assert it.next() == 3;
        assert it.next() == 4;
        assert !it.hasNext();
    }

    // 4. Negative numbers and zero: [-10, -5, 0, 5]
    private static void testNegativeAndZeroValues() {
        TreeNode root = new TreeNode(0,
                new TreeNode(-5, new TreeNode(-10), null),
                new TreeNode(5));
        BinarySearchTreeIteratorII it = new BinarySearchTreeIteratorII(root);

        assert it.next() == -10;
        assert it.next() == -5;
        assert it.next() == 0;
        assert it.prev() == -5;
        assert it.next() == 0;
        assert it.next() == 5;
        assert !it.hasNext();
    }

    // 5. Zig-zagging back and forth on a balanced tree
    private static void testRepeatedDirectionSwitches() {
        //      4
        //     / \
        //    2   6
        //   / \ / \
        //  1  3 5  7
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(6, new TreeNode(5), new TreeNode(7)));
        BinarySearchTreeIteratorII it = new BinarySearchTreeIteratorII(root);

        assert it.next() == 1;
        assert it.next() == 2;
        assert it.next() == 3;
        assert it.prev() == 2;
        assert it.prev() == 1;
        assert !it.hasPrev();
        assert it.next() == 2;
        assert it.next() == 3;
        assert it.next() == 4;
        assert it.prev() == 3;
        assert it.next() == 4;
        assert it.next() == 5;
        assert it.next() == 6;
        assert it.next() == 7;
        assert !it.hasNext();
    }
}
