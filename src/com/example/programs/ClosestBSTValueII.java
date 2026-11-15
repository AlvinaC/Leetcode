package com.example.programs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class TreeNode {
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

public class ClosestBSTValueII {
    class Sorter implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return Double.compare(b.diff, a.diff);
        }
    }

    static void main() {
        ClosestBSTValueII obj = new ClosestBSTValueII();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        double target = 3.714286;
        int k = 3;
        //method 1 - Queue
        /*
        time - O(nlogk)
        space - O(k)
        */
        //obj.closestK1(root, target, k);
        /*
        method2 - inorder + binary search + 2 pointer
        time - O(n) + O(nlogn) + O(k)
        space - O(n) +O(k)
        */
        obj.closestK2(root, target, k);
    }

    private void closestK2(TreeNode root, double target, int k) {
        List<Integer> a = new ArrayList<>();
        inorder(a, root);
        int[] nums = new int[a.size()];
        for (int i = 0; i < nums.length; i++)
            nums[i] = a.get(i);
        int index = search(target, nums);
        int i = index >= nums.length ? index - 1 : (index == 0 ? index + 1 : index);
        int j = i - 1;
        List<Integer> res = new ArrayList<>();
        while (i < nums.length && j >= 0) {
            double diff1 = Math.abs(target - nums[i]);
            double diff2 = Math.abs(target - nums[j]);
            if (diff1 < diff2) {
                res.add(nums[i]);
                i++;
            } else {
                res.add(nums[j]);
                j--;
            }
            if (res.size() == k)
                break;
        }
        if (res.size() < k) {
            while (i < nums.length) {
                res.add(nums[i]);
                i++;
                if (res.size() == k)
                    break;
            }
            while (j >= 0) {
                res.add(nums[j]);
                j--;
                if (res.size() == k)
                    break;
            }
        }
        for (int x = 0; x < res.size(); x++)
            System.out.print(" " + res.get(x));
    }

    private int search(double target, int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                high = mid;
            else low = mid + 1;
        }
        return low;
    }

    private void inorder(List<Integer> a, TreeNode root) {
        if (root == null)
            return;
        inorder(a, root.left);
        a.add(root.val);
        inorder(a, root.right);
    }

    class Node {
        int val;
        double diff;

        Node(int v, double d) {
            val = v;
            diff = d;
        }
    }

    private void closestK1(TreeNode root, double target, int k) {
        PriorityQueue<Node> q = new PriorityQueue<Node>(new Sorter());
        dfs(root, q, k, target);
        while (!q.isEmpty()) {
            System.out.print(" " + q.poll().val);
        }
    }

    private void dfs(TreeNode root, PriorityQueue<Node> q, int k, double target) {
        if (root == null)
            return;
        double diff = Math.abs(target - root.val);
        q.add(new Node(root.val, diff));
        if (q.size() > k)
            q.poll();
        dfs(root.left, q, k, target);
        dfs(root.right, q, k, target);
    }
}
