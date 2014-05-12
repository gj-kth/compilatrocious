//EXT:ISC
//EXT:ICG
//EXT:JVM
//EXT:LONG
//EXT:IWE
//EXT:NBD
//EXT:CEQ

class Main {
    public static void main(String[] args) {
        long i;
        Test test;
        Tree t;
        i = 0L;
        test = new Test();
        t = new Tree();
        t = t.insert(100L);
        t = t.insert(20L);
        t = t.insert(200L);
        t = t.insert(1L);
        test = test.print1Or0(t.contains(20L));
        test = test.print1Or0(t.contains(30L));
        while (i < 50L) {
            t = t.insert(i);
            t = t.insert(i - 100L);
            i = i + 1L;
        }
        test = test.print1Or0(t.contains(20L));
        test = test.print1Or0(t.contains(300L));
        test = test.print1Or0(t.contains(0L));
    }
}
class Test {
    public Test print1Or0(boolean b) {
        if (b) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
        return this;
    }
}

class Tree {

    public boolean isEmpty() {
        return true;
    }

    public Tree insert(long l) {
        Node t;
        t = new Node();
        t = t.initLeaf(l);
        return t;
    }

    public boolean contains(long v) {
        return false;
    }
}

class Node extends Tree {
    long value;
    Tree left;
    Tree right;


    public Node initLeaf(long v) {
        value = v;
        left = new Tree();
        right = left;
        return this;
    }
    public Node initNode(long v, Tree l, Tree r) {
        value = v;
        left = l;
        right = r;
        return this;
    }


    public long getValue() {
        return value;
    }

    public Tree insert(long n) {
        Node result;
        if (n < this.getValue()) {
            result = new Node();
            result = result.initNode(value, left.insert(n), right);
        }
        else if (this.getValue() < n) {
            result = new Node();
            result = result.initNode(value, left, right.insert(n));
        }
        else {
            result = this;
        }
        return result;
    }

    public boolean contains(long v) {
        boolean result;
        if (v < this.getValue()) {
            result = left.contains(v);
        }
        else if (this.getValue() < v) {
            result = right.contains(v);
        }
        else {
            result = this.getValue() == v;
        }
        return result;
    }
}
