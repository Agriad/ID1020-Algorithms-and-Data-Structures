import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class Lab4Question6
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

    public class Digraph
    {
        private final int V;
        private int E;
        private Bag<Integer>[] adj;
        public Digraph(int V)
        {
            this.V = V;
            this.E = 0;
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++)
                adj[v] = new Bag<Integer>();
        }
        public int V() { return V; }
        public int E() { return E; }
        public void addEdge(int v, int w)
        {
            adj[v].add(w);
            E++;
        }
        public Iterable<Integer> adj(int v)
        { return adj[v]; }
        public Digraph reverse()
        {
            Digraph R = new Digraph(V);
            for (int v = 0; v < V; v++)
                for (int w : adj(v))
                    R.addEdge(w, v);
            return R;
        }
    }

    public class Stack<Item> implements Iterable<Item>
    {
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

    public class DirectedCycle
    {
        private boolean[] marked;
        private int[] edgeTo;
        private Stack<Integer> cycle; // vertices on a cycle (if one exists)
        private boolean[] onStack; // vertices on recursive call stack
        public DirectedCycle(Digraph G)
        {
            onStack = new boolean[G.V()];
            edgeTo = new int[G.V()];
            marked = new boolean[G.V()];
            for (int v = 0; v < G.V(); v++)
                if (!marked[v]) dfs(G, v);
        }
        private void dfs(Digraph G, int v)
        {
            onStack[v] = true;
            marked[v] = true;
            for (int w : G.adj(v))
                if (this.hasCycle()) return;
                else if (!marked[w])
                { edgeTo[w] = v; dfs(G, w); }
                else if (onStack[w])
                {
                    cycle = new Stack<Integer>();
                    for (int x = v; x != w; x = edgeTo[x])
                        cycle.push(x);
                    cycle.push(w);
                    cycle.push(v);
                }
            onStack[v] = false;
        }
        public boolean hasCycle()
        { return cycle != null; }
        public Iterable<Integer> cycle()
        { return cycle; }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("OtherText.txt"));
        Lab4Question6 lab = new Lab4Question6();
        int limit = 13;
        String[] indexName = new String[limit];
        Lab4Question6.Digraph graph = lab.new Digraph(limit);
        int count = 0;
        int count2 = 0;
        int counter = 0;
        int state1 = 0;
        int state2 = 0;

        while (in.hasNext())
        {
            String[] inputs = in.nextLine().split("-");
            for (int i = 0; i < indexName.length; i++) {
                if (inputs[0].equals(indexName[i])) {
                    count++;
                }
                if (inputs[1].equals(indexName[i])) {
                    count2++;
                }
            }
            if (count == 0) {
                indexName[counter] = inputs[0];
                counter++;
            }
            if (count2 == 0) {
                indexName[counter] = inputs[1];
                counter++;
            }
            count2 = 0;
            count = 0;

            for (int i = 0; i < counter; i++) {
                if (inputs[0].equals(indexName[i])) {
                    state1 = i;
                }
                if (inputs[1].equals(indexName[i])) {
                    state2 = i;
                }
            }

            graph.addEdge(state1, state2);
        }

        Lab4Question6.DirectedCycle dc = lab.new DirectedCycle(graph);
        boolean dcAns = dc.hasCycle();

        if (!dcAns)
        {
            out.println("no cycle");
        }
        else
        {
            out.println("has cycle");
        }
    }
}

/*
TA-TB
TA-TF
TC-TA
TD-TF
TF-TE
TG-TE
TH-TG
TI-TH
TJ-TK
TJ-TL
TJ-TM
TL-TM
 */
