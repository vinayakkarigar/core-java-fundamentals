package com.modern.refresh;

public class Tree {
    private String key;
    private int value;

    private Tree left;
    private Tree right;

    public Tree(String key, int value, Tree left, Tree right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public static class TreeProcessor {
        public static int lookup(String k, int defaultValue, Tree tree) {
            if(tree == null) return defaultValue;
            if(tree.getKey() == k) return tree.getValue();
            return lookup(k, defaultValue, k.compareTo(tree.getKey()) < 0 ? tree.left : tree.right);
        }

        public static Tree update(String k, int v, Tree t) {
            if(t == null)
                t = new Tree(k, v, null, null);
            else if(t.getKey() == k)
                t.value = v;
            else if (k.compareTo(t.key) < 0)
                t.left = update(k, v, t.left);
            else
                t.right = update(k, v, t.right);
            return t;
        }

        public static Tree fupdate(String k, int v, Tree t) {
            return (t == null) ? new Tree(k, v, null, null)
                    : (k == t.key) ? new Tree(t.key, v, t.left, t.right) :
                    (k.compareTo(t.getKey()) < 0) ? fupdate(k, v, t.left)
                            : fupdate(k, v, t.right);
        }
    }
}
