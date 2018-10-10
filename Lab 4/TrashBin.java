/*
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;

public class Lab4Question1
{
    public class Bag<Item> implements Iterable<Item>
    {
        private Node first; // first node in list
        private class Node
        {
            Item item;
            Node next;
        }
        public void add(Item item)
        { // same as push() in Stack
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
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
    }


    public class Graph
    {
        private final int V; // number of vertices
        private int E; // number of edges
        private Bag<Integer>[] adj; // adjacency lists
        public Graph(int V)
        {
            this.V = V; this.E = 0;
            adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
            for (int v = 0; v < V; v++) // Initialize all lists
                adj[v] = new Bag<Integer>(); // to empty.
        }

        public int V() { return V; }
        public int E() { return E; }

        public void add(String in)
        {
            String[] inputs = in.split(" ");

        }

        public void addEdge(int v, int w)
        {

            adj[v].add(w); // Add w to v’s list.
            adj[w].add(v); // Add v to w’s list.
            E++;
        }
        public Iterable<Integer> adj(int v)
        { return adj[v]; }
    }

    /*
    public class DepthFirstPaths
    {
        private boolean[] marked; // Has dfs() been called for this vertex?
        private int[] edgeTo; // last vertex on known path to this vertex
        private final int s; // source
        public DepthFirstPaths(Graph G, int s)
        {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            this.s = s;
            dfs(G, s);
        }
        private void dfs(Graph G, int v)
        {
            marked[v] = true;
            for (int w : G.adj(v))
                if (!marked[w])
                {
                    edgeTo[w] = v;
                    dfs(G, w);
                }
        }
        public boolean hasPathTo(int v)
        { return marked[v]; }
        public Iterable<Integer> pathTo(int v)
        {
            if (!hasPathTo(v)) return null;
            Stack<Integer> path = new Stack<Integer>();
            for (int x = v; x != s; x = edgeTo[x])
                path.push(x);
            path.push(s);
            return path;
        }
    }*/
/*
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Text.txt"));
        Lab4Question1 lab = new Lab4Question1();
        int limit = 107;
        int prime = 151;
        int[] testStates = new int[limit];
        Lab4Question1.Graph graph = lab.new Graph(limit);

        while (in.hasNext())
        {
            String[] states = in.nextLine().split(" ");
            int state1 = states[0].hashCode();
            int state2 = states[1].hashCode();

            for (int i = state1; testStates[i] != 0; i = ((state1 + 1) % prime))
            {
                state1
            }
        }
    }
}

/*
public void GraphIn(String in)
        {

        this.E = E++; // Read E.
        for (int i = 0; i < E; i++)
        { // Add an edge.
        int v = in.readInt(); // Read a vertex,
        int w = in.readInt(); // read another vertex,
        addEdge(v, w); // and add edge connecting them.
        }
        }*/

/*
/*
    public class Graph
    {
        private final int V; // number of vertices
        private int E; // number of edges
        private Bag<EdgeWeight>[] adj; // adjacency lists
        public Graph(int V)
        {
            this.V = V; this.E = 0;
            adj = (Bag<EdgeWeight>[]) new Bag[V]; // Create array of lists.
            for (int v = 0; v < V; v++) // Initialize all lists
            {
                adj[v] = new Bag<EdgeWeight>(); // to empty.
            }
        }

        public int V() { return V; }
        public int E() { return E; }

        public void addEdge(int v, int w, int weight)
        {
            EdgeWeight weightV = new EdgeWeight(v, w, weight);
            EdgeWeight weightW = new EdgeWeight(w, v, weight);
            adj[v].add(weightV); // Add w to v’s list.
            adj[w].add(weightW); // Add v to w’s list.
            E++;
        }
        public Iterable<EdgeWeight> adj(int v)
        { return adj[v]; }
    }*/

/*
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
            int temp2 = pq[place];
            pq[place] = pq[n];
            pq[n] = temp2;
            Key temp3 = keys[place];
            keys[place] = keys[n];
            keys[n] = temp3;
        }

        private void sink(int n)
        {
            int place = n*2;
            int temp1 = qp[place];
            qp[place] = qp[n];
            qp[n] = temp1;
            int temp2 = pq[place];
            pq[place] = pq[n];
            pq[n] = temp2;
            Key temp3 = keys[place];
            keys[place] = keys[n];
            keys[n] = temp3;
        }

        private void exch(int i, int j)
        {
            int temp = qp[i];
            int temp1 = pq[i];
            Key temp2 = keys[i];
            qp[i] = qp[j];
            pq[i] = pq[j];
            keys[i] = keys[j];
            pq[j] = temp;
            qp[j] = temp1;
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

        public void change(int i, Key d)
        {
            keys[i] = d;
        }

        public int delMin() {
            int indexOfMin = pq[1];
            exch(1, N--);
            sink(1);
            keys[pq[N + 1]] = null;
            qp[pq[N + 1]] = -1;
            return indexOfMin;
        }
 */