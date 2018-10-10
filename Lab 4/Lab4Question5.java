import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.out;

/*README
Takes in input file of the states in the united states and makes it a directed graph.
Uses breadth first path to check if there is a path between 2 states
 */

public class Lab4Question5
{
    public class Bag<Item> implements Iterable<Item>
    {  //puts stuff in and can only iterate through, it works like a linked list
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

    public class Digraph
    {  //directed graph
        private final int V;
        private int E;
        private Bag<Integer>[] adj;  // bag of integers for adjacent linking
        public Digraph(int V)  //constructor
        {
            this.V = V;
            this.E = 0;
            adj = (Bag<Integer>[]) new Bag[V];  //creates an array of bags for adjacency list
            for (int v = 0; v < V; v++)
                adj[v] = new Bag<Integer>();  //fills each array with an empty bag
        }
        public int V() { return V; }
        public int E() { return E; }
        public void addEdge(int v, int w)  //adds a directed edge
        {
            adj[v].add(w);
            E++;
        }
        public Iterable<Integer> adj(int v)
        { return adj[v]; }
        public Digraph reverse()  //reverses all of the edges and returns that graph
        {
            Digraph R = new Digraph(V);
            for (int v = 0; v < V; v++)
                for (int w : adj(v))
                    R.addEdge(w, v);
            return R;
        }
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

    public class BreadthFirstPaths
    {
        private boolean[] marked; // Is a shortest path to this vertex known?
        private int[] edgeTo; // last vertex on known path to this vertex
        private final int s; // source
        public BreadthFirstPaths(Digraph G, int s)
        {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            this.s = s;
            bfs(G, s);
        }
        private void bfs(Digraph G, int s)
        {
            Queue<Integer> queue = new Queue<Integer>();
            marked[s] = true; // Mark the source
            queue.enqueue(s); // and put it on the queue.
            while (!queue.isEmpty())
            {
                int v = queue.dequeue(); // Remove next vertex from the queue.
                for (int w : G.adj(v))
                    if (!marked[w]) // For every unmarked adjacent vertex,
                    {
                        edgeTo[w] = v; // save last edge on a shortest path,
                        marked[w] = true; // mark it because path is known,
                        queue.enqueue(w); // and add it to the queue.
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
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Text.txt"));
        Lab4Question5 lab = new Lab4Question5();
        int limit = 49;
        String[] indexName = new String[limit];
        Lab4Question5.Digraph graph = lab.new Digraph(limit);
        int count = 0;
        int count2 = 0;
        int counter = 0;
        int state1 = 0;
        int state2 = 0;

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
                counter++;
            }
            if (count2 == 0)
            {
                indexName[counter] = inputs[1];
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

            graph.addEdge(state1, state2);
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
                //out.println(j.toString());
                //out.println(indexName[Integer.getInteger(j.toString())]);

                out.println(indexName[(Integer) (j)]);
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

        Lab4Question5.BreadthFirstPaths bfp = lab.new BreadthFirstPaths(graph, state1);
        boolean path = bfp.hasPathTo(state2);
        if (path)
        {
            out.println("exist");
        }
        else
        {
            out.println("does not exist");
        }

        /*
        Lab4Question5.BreadthFirstPaths bfp = lab.new BreadthFirstPaths(graph, state1);

        Iterable path = bfp.pathTo(state2);

        if (path != null)
        {
            out.println("exist");
        }
        else
        {
            out.println("does not exist");
        }*/

    }

}
