package com.example.programs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SerializeDeserializeNaryTree {
    class Node {
        char val;
        List<Node> children = new ArrayList<>();

        Node(char c) {
            val = c;
        }
    }

    static void main() {
        SerializeDeserializeNaryTree obj = new SerializeDeserializeNaryTree();
        Node root = obj.createDummyTree();
        StringBuilder sb = new StringBuilder();
        obj.serialize(root,sb);
        obj.deserialize(sb);
    }

    private Node deserialize(StringBuilder sb) {
        Stack<Node> st = new Stack<>();
        Node last = null;
        for(int i=0;i<sb.length();i++)
        {
            char c = sb.charAt(i);
            if(c == ')')
            {
                last = st.pop();
            }
            else {
                Node a = new Node(c);
                if(!st.empty())
                {
                    st.peek().children.add(a);
                }
                st.push(a);
            }
        }
        return last;
    }

    private void serialize(Node root, StringBuilder sb) {
       sb.append(root.val);
       if(root.children.isEmpty())
       {
           sb.append(')');
           return;
       }
       for(Node child : root.children)
       {
           serialize(child,sb);
       }
        sb.append(')');
    }

    Node createDummyTree() {
        Node root = new Node('A');
        root.children.add(new Node('B'));
        root.children.add(new Node('C'));
        root.children.add(new Node('D'));
        root.children.get(0).children.add(new Node('E'));
        root.children.get(0).children.add(new Node('F'));
        root.children.get(2).children.add(new Node('G'));
        root.children.get(2).children.add(new Node('H'));
        root.children.get(2).children.add(new Node('I'));
        root.children.get(2).children.add(new Node('J'));
        root.children.get(0).children.get(1).children.add(new Node('K'));
        return root;
    }
}
