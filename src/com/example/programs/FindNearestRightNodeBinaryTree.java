package com.example.programs;

import java.util.LinkedList;
import java.util.Queue;

public class FindNearestRightNodeBinaryTree {
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

    static void main() {
        FindNearestRightNodeBinaryTree obj = new FindNearestRightNodeBinaryTree();
        // 1. Create the target node u and its neighbors first so we keep references
        TreeNode u = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        // 2. Build the middle level
        TreeNode node2 = new TreeNode(2, null, u);     // left is null, right is 4
        TreeNode node3 = new TreeNode(3, node5, node6); // left is 5, right is 6

        // 3. Connect to root
        TreeNode root1 = new TreeNode(1, node2, node3);

        // Run test:
        TreeNode ans1 = obj.findNearestRightNode(root1, u);
        System.out.println(ans1 != null ? ans1.val : "null"); // Output: 5

        TreeNode u2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4, u2, null);
        TreeNode root2 = new TreeNode(3, null, node4);

        // Run test:
        TreeNode ans2 = obj.findNearestRightNode(root2, u2);

        System.out.println(ans2 != null ? ans2.val : "null"); // Output: 5
        TreeNode root3 = new TreeNode(1);

        // Run test:
        TreeNode ans3 = obj.findNearestRightNode(root3, root3);
        System.out.println(ans3 != null ? ans3.val : "null"); // Output: null
    }

    private TreeNode findNearestRightNode(TreeNode root1, TreeNode u) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root1);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode n = q.poll();
                size--;
                if (n == u) {
                    if (size > 0)
                        return q.peek();
                    else return null;
                }
                if(n.left != null)
                    q.add(n.left);
                if(n.right != null)
                    q.add(n.right);
            }
        }
        return null;
    }
}
