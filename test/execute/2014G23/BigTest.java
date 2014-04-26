/* This is a valid test which executes with the standard grammar and extensions
 * Implements a graph data structure and BFS for shortest path between two nodes
 * Correct output should be:
 *     4
 *     1
 *     0
 */

class Test {
    public static void main(String[] args) {
        Graph graph;
        int[] visited;
        Queue queue;
        int size;
        int i;
        int u; // start vertex
        int v; // end vertex
        int t; // tmp vertex
        boolean found;

        graph = new Graph();
        size = graph.init();
        visited = new int[size];
        queue = new Queue().init(size);
        found = false;
        
        u = 0;
        v = 4;

        // Initialize visited
        i = 0;
        while(i < size) {
            visited[i] = size;
            i = i + 1;
        }

        if( !(u<0) && !(v<0) && u<size && v<size && queue.enqueue(u) ) {
            // Find shortest path between node u and v using BFS

            // visited[v]<size means end node has been found
            while( !(queue.size() < 1) && !(visited[v]<size) ) {
                t = queue.dequeue();
                if ( !(t<v) && t<v+1 ) {
                    // Done
                } else {
                        i = 0;
                        while(i<size) {
                            if( !(graph.getVertex(t).getEdges()[i]<1)
                                && !(visited[i]<size) && queue.enqueue(i) ) {
                                visited[i] = t;
                            } else {}
                            i = i + 1;
                        }
                }
            }

        } else {}

        // Print found path
        visited[u] = size;
        while( visited[v] < size ) {
            System.out.println(v);
            v = visited[v];
        }
        System.out.println(u);

    }
}

// Simple queue class for enumerables
class Queue {
    int[] q;
    int size;

    public Queue init(int n) {
        size = 0;
        q = new int[n];
        while(!(n < 1)) {
            n = n - 1;
            q[n] = 0;
        }
        return this;
    }

    public boolean enqueue(int i) {
        boolean b;
        if( !(i < 0) && i < q.length ) {
            b = true;
            size = size + 1;
            q[i] = size;
            // only in real Java System.out.println("enqueued " + i);
        } else {
            b = false;
        }
        return b;
    }

    public int dequeue() {
        int i;
        int n;
        i = 0;
        n = size;
        if ( ! (size<1) ) {
            while(i < q.length) {
                if( ! (q[i] < 1) ) {

                    if( !(q[i]<1) && q[i]<2 ) {
                        n = i;
                    } else {}

                    q[i] = q[i] - 1;
                } else {}
                i = i + 1;
            }
            size = size - 1;
        } else {}
        // only in real Java System.out.println("dequeued " + n);
        return n;
    }

    public int size() {
        return size;
    }
}


/**
 * A class representing a graph.
 * Implemented using kind of an adjecency matrix
 * where the rows are a linked list
 * (basically the opposite of adjecency lists)
 **/

class Graph {
    Vertex start;
    int size;

    public int init() {
        int i;
        boolean b; // dummy variable
        Vertex last;

        size = 5;
        start = new Vertex();
        i = 1;
        start = new Vertex();
        b = start.init(size);
        last = start;


        while(i < size) {
            b = last.setNext(new Vertex());
            b = last.getNext().init(size);
            last = last.getNext();
            i = i + 1;
        }

        b = this.getVertex(0).addEdge(1);
        b = this.getVertex(1).addEdge(0);

        b = this.getVertex(0).addEdge(2);
        b = this.getVertex(2).addEdge(0);

        b = this.getVertex(1).addEdge(3);
        b = this.getVertex(3).addEdge(1);

        b = this.getVertex(2).addEdge(4);
        b = this.getVertex(4).addEdge(2);

        b = this.getVertex(1).addEdge(4);
        b = this.getVertex(4).addEdge(1);

        b = this.getVertex(3).addEdge(3);
        b = this.getVertex(4).addEdge(4);

        return size;
    }

    public Vertex getVertex(int n) {
        Vertex v;
        v = start;
        while( ! (n < 1) ) {
            v = v.getNext();
            n = n - 1;
        }
        return v;
    }
}

class Vertex {
    Vertex next;
    boolean last;
    int[] edges;

    public boolean init(int numv) {
        int i;

        last = true;
        edges = new int[numv];

        i = 0;
        while(i<numv) {
            edges[i] = 0;
            i = i + 1;
        }
        return last;
    }

    public boolean addEdge(int v) {
        edges[v] = 1;
        return true;
    }

    public boolean setNext(Vertex v) {
        last = false;
        next = v;
        return true;
    }

    public int[] getEdges() {
        return edges;
    }

    public Vertex getNext() {
        return next;
    }

    public boolean isLast() {
        return last;
    }
}
