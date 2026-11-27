package com.example.programs;

import java.util.*;

public class CheckTwoExpTreesEqual {
    class Node {
        char val;
        Node left;
        Node right;

        Node(char c) {
            val = c;
        }

        Node(char c, Node l, Node r) {
            val = c;
            left = l;
            right = r;
        }
    }

    /*
    To check if two trees with +, -, *, and / are equal, turn each tree into a single big fraction
     (Numerator / Denominator), then cross-multiply to check if they match.

    Step 1: Turn every leaf into a fraction

    Start at the bottom of the tree (the leaves) and treat every single variable or number as a fraction over 1:
    • The variable a becomes \(\frac{a}{1}\)
    • The number 5 becomes \(\frac{5}{1}\)

    Step 2: Combine them moving up the tree

    As you move up from the leaves to the root, merge the left and right fractions using basic fraction math:
    • Adding (+): Find a common denominator and add the tops \(\rightarrow \frac{A}{B} + \frac{C}{D} = \frac{AD + BC}{BD}\)
    • Subtracting (-): Find a common denominator and subtract the tops \(\rightarrow \frac{A}{B} - \frac{C}{D} = \frac{AD - BC}{BD}\)
    • Multiplying (*): Multiply straight across \(\rightarrow \frac{A}{B} \times \frac{C}{D} = \frac{AC}{BD}\)
    • Dividing (/): Flip the second fraction and multiply \(\rightarrow \frac{A}{B} \div \frac{C}{D} = \frac{AD}{BC}\)

    Step 3: Expand and sort the terms

    Whenever you multiply terms (like \(a \times (b + c)\)), expand them out (\(ab + ac\)). Always sort the letters in each term alphabetically (so \(b \times a\) is saved as \(ab\)). That way, like terms automatically combine or cancel out (e.g., \(ab - ba = 0\)).

    Step 4: Cross-multiply to compare the two trees

    At the root, Tree 1 becomes one big fraction \(\frac{\text{Top}_{1}}{\text{Bottom}_{1}}\) and Tree 2 becomes \(\frac{\text{Top}_{2}}{\text{Bottom}_{2}}\).
    Instead of trying to simplify or reduce the fractions, just cross-multiply:
    \(\text{Top}_{1}\times \text{Bottom}_{2}==\text{Top}_{2}\times \text{Bottom}_{1}\)
    If both sides have the exact same terms and numbers, the two trees are equivalent.
     */
    static void main() {
        CheckTwoExpTreesEqual obj = new CheckTwoExpTreesEqual();
        // Tree 1: (a + b) / c
        Node t1 = obj.new Node('/',
                obj.new Node('+', obj.new Node('a'), obj.new Node('b')),
                obj.new Node('c')
        );

        // Tree 2: (a / c) + (b / c)
        Node t2 = obj.new Node('+',
                obj.new Node('/', obj.new Node('a'), obj.new Node('c')),
                obj.new Node('/', obj.new Node('b'), obj.new Node('c'))
        );

        // Tree 1: (a + b) / c
        Node t3 = obj.new Node('/',
                obj.new Node('+', obj.new Node('a'), obj.new Node('b')),
                obj.new Node('c')
        );

        // Tree 2: a + (b / c)
        Node t4 = obj.new Node('+',
                obj.new Node('a'),
                obj.new Node('/', obj.new Node('b'), obj.new Node('c'))
        );

        System.out.println(obj.checkEquivalence(t3, t4)); // true
    }

    private boolean checkEquivalence(Node t1, Node t2) {
        Poly a = this.evaluate(t1);
        Poly b = this.evaluate(t2);
        Map<List<Character>, Long> d;
        Map<List<Character>, Long> n;
        d = this.mul(a.den, b.num);
        n = this.mul(a.num, b.den);
        return d.equals(n);
    }

    private Poly evaluate(Node t1) {
        if (t1.val != '+' && t1.val != '-' && t1.val != '*' && t1.val != '/') {
            Map<List<Character>, Long> num = Map.of(List.of(t1.val), 1l);
            Map<List<Character>, Long> den = Map.of(List.of(), 1l);
            return new Poly(num, den);
        }
        Poly left = this.evaluate(t1.left);
        Poly right = this.evaluate(t1.right);
        Map<List<Character>, Long> d;
        Map<List<Character>, Long> n;
        switch (t1.val) {
            case '+':
                d = this.mul(left.den, right.den);
                n = this.add(this.mul(left.num, right.den), this.mul(right.num, left.den), 1);
                return new Poly(n, d);

            case '-':
                d = this.mul(left.den, right.den);
                n = this.add(this.mul(left.num, right.den), this.mul(right.num, left.den), -1);
                return new Poly(n, d);

            case '*':
                d = this.mul(left.den, right.den);
                n = this.mul(left.num, right.num);
                return new Poly(n, d);

            case '/':
                d = this.mul(left.den, right.num);
                n = this.mul(left.num, right.den);
                return new Poly(n, d);

            default:
                throw new IllegalArgumentException("Unknown operator: " + t1.val);
        }
    }

    private HashMap<List<Character>, Long> mul(Map<List<Character>, Long> a, Map<List<Character>, Long> b) {
        HashMap<List<Character>, Long> res = new HashMap<>();
        for (Map.Entry<List<Character>, Long> e1 : a.entrySet()) {
            for (Map.Entry<List<Character>, Long> e2 : b.entrySet()) {
                List<Character> l = new ArrayList<>();
                l.addAll(e1.getKey());
                l.addAll(e2.getKey());
                Collections.sort(l);
                long val = e1.getValue() * e2.getValue();
                long update = res.getOrDefault(l, 0l) + val;
                if (update == 0l) {
                    res.remove(l);
                } else res.put(l, update);
            }
        }
        return res;
    }

    private HashMap<List<Character>, Long> add(Map<List<Character>, Long> a, Map<List<Character>, Long> b, int sign) {
        HashMap<List<Character>, Long> res = new HashMap<>(a);
        for (Map.Entry<List<Character>, Long> e2 : b.entrySet()) {
            List<Character> l = e2.getKey();
            long update = res.getOrDefault(l, 0l) + (sign * e2.getValue());
            if (update == 0l) {
                res.remove(l);
            } else res.put(l, update);
        }

        return res;
    }

    class Poly {
        Map<List<Character>, Long> num;
        Map<List<Character>, Long> den;

        Poly(Map<List<Character>, Long> a, Map<List<Character>, Long> b) {
            num = a;
            den = b;
        }
    }
}
