import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Lab4Question4
{
    public class Edge implements Comparable<Edge>
    {
        private final int v; // one vertex
        private final int w; // the other vertex
        private final double weight; // edge weight
        public Edge(int v, int w, double weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        public double weight()
        { return weight; }
        public int either()
        { return v; }
        public int other(int vertex)
        {
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new RuntimeException("Inconsistent edge");
        }
        public int compareTo(Edge that)  //compares 2 edges
        {
            if (this.weight() < that.weight()) return -1;
            else if (this.weight() > that.weight()) return +1;
            else return 0;
        }
        public String toString()
        { return String.format("%d-%d %.2f", v, w, weight); }
    }

    public class Bag<Item> implements Iterable<Item>
    {
        private Node first; // first node in list
        private class Node  // uses node to store items in bag
        {
            Item item;  // stores the item
            Node next;  // what the next item is so it can iterate
        }
        public void add(Item item)
        { // same as push() in Stack
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
        public Iterator<Item> iterator()  //basic iterator
        { return new ListIterator(); }
        private class ListIterator implements Iterator<Item>  //specific iterator for this type
        {
            private Node current = first;
            public boolean hasNext()
            { return current != null; }
            public void remove() { }
            public Item next()
            {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public class Graph
    {
        private final int V; // number of vertices
        private int E; // number of edges
        private Bag<Edge>[] adj; // adjacency lists
        public Graph(int V)  //constructor
        {
            this.V = V; this.E = 0;
            adj = (Bag<Edge>[]) new Bag[V]; // Create array of lists.
            for (int v = 0; v < V; v++) // Initialize all lists
                adj[v] = new Bag<Edge>(); // to empty.
        }

        public int V() { return V; }
        public int E() { return E; }

        public void addEdge(Edge e)  //add's an edge
        {
            int v = e.either();
            int w = e.other(v);
            adj[v].add(e); // Add w to v’s list.
            adj[w].add(e); // Add v to w’s list.
            E++;
        }
        public Iterable<Edge> adj(int v)
        { return adj[v]; }

        public Iterable<Edge> edges()
        {
            Bag<Edge> b = new Bag<Edge>();
            for (int v = 0; v < V; v++)
                for (Edge e : adj[v])
                    if (e.other(v) > v) b.add(e);
            return b;
        }
    }

    public class Queue<Item> implements Iterable<Item>
    {
        private Node first; // link to least recently added node
        private Node last; // link to most recently added node
        private int N; // number of items on the queue
        private class Node
        { // nested class to define nodes
            Item item;
            Node next;
        }
        public boolean isEmpty() { return first == null; } // Or: N == 0.
        public int size() { return N; }
        public void enqueue(Item item)
        { // Add item to the end of the list.
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldlast.next = last;
            N++;
        }
        public Item dequeue()
        { // Remove item from the beginning of the list.
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        public Iterator<Item> iterator()
        { return new ListIterator(); }
        private class ListIterator implements Iterator<Item>
        {
            private Node current = first;
            public boolean hasNext()
            { return current != null; }
            public void remove() { }
            public Item next()
            {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
// See page 155 for iterator() implementation.
// See page 150 for test client main().
    }

    public class IndexMinPQ<Key extends Comparable<Key>>
    {
        private int N; // number of elements on PQ
        private int[] pq; // binary heap using 1-based indexing.
        private int[] qp; // inverse: qp[pq[i]] = pq[qp[i]] = i. priority with left being smallest
        private Key[] keys; // items with priorities

        public IndexMinPQ(int maxN) {
            keys = (Key[]) new Comparable[maxN + 1];
            pq = new int[maxN + 1];
            qp = new int[maxN + 1];
            for (int i = 0; i <= maxN; i++) qp[i] = -1;  //makes it "zero"
        }

        public boolean isEmpty()
        {
            return N == 0;
        }

        public boolean contains(int k)
        {
            return qp[k] != -1;
        }

        private void swim(int n)
        {
            int place = n/2;
            int temp1 = qp[place];
            qp[place] = qp[n];
            qp[n] = temp1;
        }

        private void sink(int n)
        {
            int place = n*2;
            int temp1 = qp[place];
            qp[place] = qp[n];
            qp[n] = temp1;
        }

        private void exch(int i, int j)
        {
            int temp = qp[i];
            //int temp1 = pq[i];
            Key temp2 = keys[i];
            qp[i] = qp[j];
            //pq[i] = pq[j];
            keys[i] = keys[j];
            pq[j] = temp;
            //qp[j] = temp1;
            keys[j] = temp2;
        }

        public void insert(int k, Key key)
        {
            N++;
            qp[k] = N;
            pq[N] = k;
            keys[k] = key;
            swim(N);
        }

        public Key min()
        {
            return keys[pq[1]];
        }

        public int delMin() {
            int indexOfMin = pq[1];
            exch(1, N--);
            sink(1);
            keys[pq[N + 1]] = null;
            qp[pq[N + 1]] = -1;
            return indexOfMin;
        }
    }
/*
    public class LazyPrimMST
    {
        private boolean[] marked; // MST vertices
        private Queue<Edge> mst; // MST edges
        private IndexMinPQ<Edge> pq; // crossing (and ineligible) edges
        public LazyPrimMST(Graph G)
        {
            pq = new IndexMinPQ<Edge>();
            marked = new boolean[G.V()];
            mst = new Queue<Edge>();
            visit(G, 0); // assumes G is connected (see Exercise 4.3.22)
            while (!pq.isEmpty())
            {
                Edge e = pq.delMin(); // Get lowest-weight
                int v = e.either(), w = e.other(v); // edge from pq.
                if (marked[v] && marked[w]) continue; // Skip if ineligible.
                mst.enqueue(e); // Add edge to tree.
                if (!marked[v]) visit(G, v); // Add vertex to tree
                if (!marked[w]) visit(G, w); // (either v or w).
            }
        }
        private void visit(Graph G, int v)
        { // Mark v and add to pq all edges from v to unmarked vertices.
            marked[v] = true;
            for (Edge e : G.adj(v))
                if (!marked[e.other(v)]) pq.insert(e);
        }
        public Iterable<Edge> edges()
        { return mst; }
        public double weight() // See Exercise 4.3.31.
    }*/

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Text.txt"));  //input file
        Lab4Question4 lab = new Lab4Question4();  //instantiate class
        int limit = 49;  //limit of input for array
        String[] indexName = new String[limit];  //array of states with index as input (symbol table)
        Lab4Question4.Graph graph = lab.new Graph(limit);  //instantiate inner class
        int count = 0;  //left input marker
        int count2 = 0;  //right input marker
        int counter = 0;  //array counter
        int state1 = 0;  //int for the left input state
        int state2 = 0;  //int for the right input state
        int weight = 1;

        while (in.hasNext())
        {
            String[] inputs = in.nextLine().split(" ");

            for (int i = 0; i < indexName.length; i++)
            {
                if (inputs[0].equals(indexName[i]))  //checks for duplicate
                {
                    count++;
                }
                if (inputs[1].equals(indexName[i]))
                {
                    count2++;
                }
            }

            if (count == 0)  //if unique put into array
            {
                indexName[counter] = inputs[0];
                //state1 = counter;
                counter++;
            }
            if (count2 == 0)
            {
                indexName[counter] = inputs[1];
                //state2 = counter;
                counter++;
            }
            count2 = 0;
            count = 0;

            for (int i = 0; i < counter; i++)  //finds index with corresponding name
            {
                if (inputs[0].equals(indexName[i]))
                {
                    state1 = i;
                }
                if (inputs[1].equals(indexName[i]))
                {
                    state2 = i;
                }
            }

            Lab4Question4.Edge edge = lab.new Edge(state1, state2, weight);
            graph.addEdge(edge);  //put into graph
            weight++;
        }
    }
}
