import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;

import static java.lang.System.out;

public class Lab4Question1
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

    public class Graph
    {
        private final int V; // number of vertices
        private int E; // number of edges
        private Bag<Integer>[] adj; // adjacency lists
        public Graph(int V)  //constructor
        {
            this.V = V; this.E = 0;
            adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
            for (int v = 0; v < V; v++) // Initialize all lists
                adj[v] = new Bag<Integer>(); // to empty.
        }

        public int V() { return V; }
        public int E() { return E; }

        public void addEdge(int v, int w)
        {
            adj[v].add(w); // Add w to v’s list.
            adj[w].add(v); // Add v to w’s list.
            E++;  //add e
        }
        public Iterable<Integer> adj(int v)  //returns list of adjacent nodes
        { return adj[v]; }
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

    public class DepthFirstPaths
    {
        private boolean[] marked; // Has dfs() been called for this vertex?
        private int[] edgeTo; // last vertex on known path to this vertex
        private final int s; // source
        public DepthFirstPaths(Graph G, int s)
        {
            marked = new boolean[G.V()];  //vertices in graph
            edgeTo = new int[G.V()];  //vertices in graph
            this.s = s;
            dfs(G, s);  //call dfs from "root"/start
        }
        private void dfs(Graph G, int v)
        {
            marked[v] = true;  //mark "root" as true
            for (int w : G.adj(v))  //for the adjacent vertices
                if (!marked[w])  //if not visited
                {
                    edgeTo[w] = v;  //parent is previous node
                    dfs(G, w);  //send kid node as parent node
                }
        }
        public boolean hasPathTo(int v)
        { return marked[v]; }
        public Iterable<Integer> pathTo(int v)  //from "root" to v
        {
            if (!hasPathTo(v))  //if no path
            {
                return null;
            }
            Stack<Integer> path = new Stack<Integer>();
            for (int x = v; x != s; x = edgeTo[x])  //x is v, if x not root,
            {
                path.push(x);  //add x
            }
            path.push(s);  //add the root

            return path;
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Text.txt"));  //input file
        Lab4Question1 lab = new Lab4Question1();  //instantiate class
        int limit = 49;  //limit of input for array
        String[] indexName = new String[limit];  //array of states with index as input (symbol table)
        Lab4Question1.Graph graph = lab.new Graph(limit);  //instantiate inner class
        int count = 0;  //left input marker
        int count2 = 0;  //right input marker
        int counter = 0;  //array counter
        int state1 = 0;  //int for the left input state
        int state2 = 0;  //int for the right input state

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

            graph.addEdge(state1, state2);  //put into graph
        }

        Iterable[] iter = new Iterable[limit];
        int edgeCounter = 0;
        int stateCounter = 0;

        for (int i = 0; i < limit; i++)  //gets all of the adjacency list
        {
            iter[i] = graph.adj(i);
        }

        for (Iterable i : iter)  //for each adjacency list
        {
            out.println("Borders of: " + indexName[stateCounter]);
            for (Object j : i)  //for each nodes in adjacency list
            {
                //out.println(j.toString());
                //out.println(indexName[Integer.getInteger(j.toString())]);

                out.println(indexName[(Integer) (j)]);  //symbol table conversion
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

        for (int i = 0; i < limit; i++)  //finds input in state index and finds corresponding number
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

        Lab4Question1.DepthFirstPaths dfp = lab.new DepthFirstPaths(graph, state1);

        Iterable path = dfp.pathTo(state2);

        for (Object i : path)
        {
            out.println(indexName[(Integer) (i)]);
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