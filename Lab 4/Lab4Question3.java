import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;

import static java.lang.System.out;

public class Lab4Question3
{
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

    public class EdgeWeight
    {
        private int v;  //node v
        private int w;  //node w
        private double weight;  //weight of the connection

        public EdgeWeight(int v, int w, double weight)  //constructor
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public Integer v()
        {
            return (this.v);
        }

        public Integer w()
        {
            return (this.w);
        }

        public double weight()
        {
            return (weight);
        }

        public int either()
        { return v; }

        public int other(int vertex)
        {
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new RuntimeException("Inconsistent edge");
        }
    }

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
            EdgeWeight vwWeight = new EdgeWeight(v, w, weight);
            EdgeWeight wvWeight = new EdgeWeight(v, w, weight);
            adj[v].add(vwWeight); // Add w to v’s list.
            adj[w].add(wvWeight); // Add v to w’s list.
            E++;
        }

        public Iterable<EdgeWeight> adj(int v)
        { return adj[v]; }
    }

    public class Queue<Item> implements Iterable<Item>
    {  // oldest(first)-->next-->next-->newest(last)-->null
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

    public class Stack<Item> implements Iterable<Item>
    {  //newest(first)-->next-->next-->null
        private Node first; // top of stack (most recently added node)
        private int N; // number of items
        private class Node
        { // nested class to define nodes
            Item item;
            Node next;
        }
        public boolean isEmpty() { return first == null; } // Or: N == 0.
        public int size() { return N; }
        public void push(Item item)
        { // Add item to top of stack.
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }
        public Item pop()
        { // Remove item from top of stack.
            Item item = first.item;
            first = first.next;
            N--;
            return item;
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
// See page 155 for iterator() implementation.
// See page 147 for test client main().
    }

    /*
    public class BreadthFirstPaths
    {
        private boolean[] marked; // Is a shortest path to this vertex known?
        private int[] edgeTo; // last vertex on known path to this vertex
        private final int s; // source
        public BreadthFirstPaths(Graph G, int s)
        {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            this.s = s;
            bfs(G, s);
        }
        private void bfs(Graph G, int s)
        {
            Queue<Integer> queue = new Queue<Integer>();
            marked[s] = true; // Mark the source
            queue.enqueue(s); // and put it on the queue.
            while (!queue.isEmpty())
            {
                int v = queue.dequeue(); // Remove next vertex from the queue.
                for (EdgeWeight w : G.adj(v))
                    if (!marked[w.w()]) // For every unmarked adjacent vertex,
                    {
                        edgeTo[w.w()] = v; // save last edge on a shortest path,
                        marked[w.w()] = true; // mark it because path is known,
                        queue.enqueue(w.w()); // and add it to the queue.
                    }
            }
        }
        public boolean hasPathTo(int v)
        { return marked[v]; }

        public Iterable<Integer> pathTo(int v)
        {
            if (!hasPathTo(v)) return null;
            Queue<Integer> path = new Queue<Integer>();
            for (int x = v; x != s; x = edgeTo[x])
                path.enqueue(x);
            path.enqueue(s);
            return path;
        }
// Same as for DFS (see page 536).
    }*/

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
            int temp2 = pq[place];
            pq[place] = pq[n];
            pq[n] = temp2;
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
    }

    public class DijkstraSP
    {
        private EdgeWeight[] edgeTo;
        private double[] distTo;
        private IndexMinPQ<Double> pq;
        public DijkstraSP(Graph G, int s)
        {
            edgeTo = new EdgeWeight[G.V()];
            distTo = new double[G.V()];
            pq = new IndexMinPQ<Double>(G.V());
            for (int v = 0; v < G.V(); v++)
                distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;
            pq.insert(s, 0.0);
            while (!pq.isEmpty())
                relax(G, pq.delMin());
        }
        private void relax(Graph G, int v)
        {
            for(EdgeWeight e : G.adj(v))
            {
                int w = e.other(v);
                if (distTo[w] > distTo[v] + e.weight())
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w)) pq.change(w, distTo[w]);
                    else pq.insert(w, distTo[w]);
                }
            }
        }

        //public double distTo(int v) // standard client query methods
        public boolean hasPathTo(int v) // for SPT implementatations
        {
            if (edgeTo[v] == null)
            {
                return (false);
            }
            else
            {
                return (true);
            }
        }
        public Iterable<EdgeWeight> pathTo(int v) // (See page 649.)
        {
            if (!hasPathTo(v))
            {
                return (null);
            }
            Stack<EdgeWeight> path = new Stack<>();
            for (EdgeWeight x = edgeTo[v]; x != null; x = edgeTo[x.either()])
            {
                path.push(x);
            }
            return (path);
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Text.txt"));  //input file
        Lab4Question3 lab = new Lab4Question3();  //instantiate class
        int limit = 49;  //limit of input for array
        String[] indexName = new String[limit];  //array of states with index as input (symbol table)
        Lab4Question3.Graph graph = lab.new Graph(limit);  //instantiate inner class
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
                if (inputs[0].equals(indexName[i]))
                {
                    count++;
                }
                if (inputs[1].equals(indexName[i]))
                {
                    count2++;
                }
            }
            if (count == 0)
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

            for (int i = 0; i < counter; i++)
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

            graph.addEdge(state1, state2, weight);
            weight++;
        }

        Iterable[] iter = new Iterable[limit];
        int edgeCounter = 0;
        int stateCounter = 0;

        for (int i = 0; i < limit; i++)
        {
            iter[i] = graph.adj(i);
        }

        for (Iterable i : iter)
        {
            out.println("Borders of: " + indexName[stateCounter]);
            for (Object j : i)
            {
                Integer k = ((EdgeWeight) j).w();
                if (k == stateCounter)
                {
                    k = ((EdgeWeight) j).v();
                }
                out.println(indexName[k]);
                edgeCounter++;
            }
            stateCounter++;
            out.println();
        }
        out.println("vertices " + graph.V());
        out.println("edges " + graph.E());

        //out.println(edgeCounter);

        Scanner sc = new Scanner(System.in);
        out.println("Shortest state state search");
        out.println("Input state x state y with a space in the middle");
        String input = sc.nextLine();
        String[] inputSplit = input.split(" ");

        for (int i = 0; i < limit; i++)
        {
            if (inputSplit[0].equals(indexName[i]))
            {
                state1 = i;
            }
            if (inputSplit[1].equals(indexName[i]))
            {
                state2 = i;
            }
        }

        //Lab4Question3.BreadthFirstPaths bfp = lab.new BreadthFirstPaths(graph, state1);

        //Iterable path = bfp.pathTo(state2);
        Lab4Question3.DijkstraSP dsp = lab.new DijkstraSP(graph, state1);

        Iterable path = dsp.pathTo(state2);

        for (Object i : path)
        {
            out.println(indexName[(Integer) (i)]);
        }

    }
}



