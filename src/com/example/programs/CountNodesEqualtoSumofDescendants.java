package com.example.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CountNodesEqualtoSumofDescendants {
    static void main() {
        CountNodesEqualtoSumofDescendants obj = new CountNodesEqualtoSumofDescendants();
        int[][] t1 = new int[][]{{10, 3}, {10, 4}, {3, 2}, {3, 1}};
        //int[][] t2 = new int[][]{{0, 0}, {0, 0}}; - for this need to key map using some index
        int[][] t3 = new int[][]{{6, 3}, {3, 2}, {2, 1}, {1, 0}};
        int[][] t4 = new int[][]{{5, 2}, {5, 1}, {2, 4}};
        HashSet<Integer> children = new HashSet<>();
        HashMap<Integer, List<Integer>> map = obj.getTree(t4, children);
        int root = obj.getRoot(map, children);
        int count = obj.callCount(map, root);
        System.out.println(count);
    }

    private int getRoot(HashMap<Integer, List<Integer>> map, HashSet<Integer> children) {
        for (Integer key : map.keySet()) {
            if (!children.contains(key))
                return key;
        }
        return -1;
    }

    private int callCount(HashMap<Integer, List<Integer>> map, int root) {
        return dfs(root, map)[1];
    }

    private int[] dfs(Integer root, HashMap<Integer, List<Integer>> map) {
        int sum = 0;
        int count = 0;
        if (!map.get(root).isEmpty()) {
            for (Integer child : map.get(root)) {
                int[] res = dfs(child, map);
                sum += res[0];
                count += res[1];
            }
        }
        int tot = sum + root;
        if (sum == root) {
            return new int[]{tot, count + 1};
        } else
            return new int[]{tot, count};
    }

    private HashMap<Integer, List<Integer>> getTree(int[][] t1, HashSet<Integer> children) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t1.length; i++) {
            if (!map.containsKey(t1[i][0])) {
                map.put(t1[i][0], new ArrayList<Integer>());
            }
            if (!map.containsKey(t1[i][1])) {
                map.put(t1[i][1], new ArrayList<Integer>());
            }
            map.get(t1[i][0]).add(t1[i][1]);
            children.add(t1[i][1]);
        }
        return map;
    }
}
