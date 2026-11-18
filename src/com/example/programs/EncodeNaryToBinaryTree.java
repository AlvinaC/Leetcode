package com.example.programs;

import java.util.ArrayList;
import java.util.List;

public class EncodeNaryToBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    class Node {
        int val;
        List<Node> children;

        Node(int x) {
            val = x;
            children = new ArrayList<Node>();
        }
    }
    //time - O(n)
    //space -O(n)
    static void main() {
        EncodeNaryToBinaryTree obj = new EncodeNaryToBinaryTree();
        Node root = obj.createDummyTree();
        TreeNode broot = obj.encode(root);
        obj.printTree(broot);
    }
    public  void printTree(TreeNode root)
    {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    private TreeNode encode(Node root) {
        if (root == null)
            return null;
        TreeNode n = new TreeNode(root.val);
        if (!root.children.isEmpty())
            n.left = encode(root.children.get(0));
        TreeNode cur = n;
        for (int i = 1; i < root.children.size(); i++) {
            cur.right = encode(root.children.get(i));
            cur = cur.right;
        }
        return n;
    }

    private Node createDummyTree() {
        Node root = new Node(1);
        root.children.add(new Node(2));
        root.children.add(new Node(3));
        root.children.add(new Node(4));
        root.children.add(new Node(5));

        root.children.get(0).children.add(new Node(6));
        root.children.get(0).children.add(new Node(7));

        root.children.get(3).children.add(new Node(8));
        root.children.get(3).children.add(new Node(9));
        return root;
    }
}
