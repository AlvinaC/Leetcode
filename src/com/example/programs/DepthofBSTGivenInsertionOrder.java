package com.example.programs;

public class DepthofBSTGivenInsertionOrder {
    class TreeNode {
        int val;
        int depth = 0;
        TreeNode left;
        TreeNode right;

        TreeNode(int val, int d) {
            this.val = val;
            this.depth = d;
        }
    }

    static void main() {
        DepthofBSTGivenInsertionOrder obj = new DepthofBSTGivenInsertionOrder();
        int[] t1 = new int[]{4, 2, 6, 1, 3, 5, 7};
        int[] t2 = new int[]{1, 2, 3, 4, 5};
        int[] t3 = new int[]{5, 4, 3, 2, 1};
        int[] t4 = new int[]{3, 1, 4, 2};
        int depth = 0;
        DepthofBSTGivenInsertionOrder.TreeNode root = obj.new TreeNode(t4[0], 0);
        for (int i = 1; i < t4.length; i++) {
            depth = Math.max(obj.insert(t4[i], root, 0), depth);
        }
        System.out.println(depth);
    }

    private int insert(int i, TreeNode root, int d) {
        if (i > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(i, d + 1);
                return d+1;
            }
            else
                return insert(i, root.right, d + 1);
        } else {
            if (root.left == null) {
                root.left = new TreeNode(i, d + 1);
                return d+1;
            }
            else
                return insert(i, root.left, d + 1);
        }
    }
}


