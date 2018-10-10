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
            System.out.println("weight");
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

    public class IndexMinPQ//<Item> //Question 3
    {
        //structure:  null<--1<-->2<-->3-->null
        //where 1 is the smallest data entered and 3 is the largest
        private Node first = null;  //smallest value
        private Node last = null;  //largest value
        private int size = 0;

        private class Node {
            //Item item = null;  //data storage
            int item;
            double distance;
            Node next = null;  //pointer to the next node
            Node before = null;  //pointer to the previous node
        }

        public boolean isEmpty()  //checks, if first == null, true else false
        {
            return (first == null);
        }

        public void insert(int item, double length)  //adds the item to the "last" node and links it
        {
            System.out.println("insert insert");
            Node temp = new Node();  //makes a new node for the new data
            temp.item = item;
            temp.distance = length;

            if (size == 0)  //if empty add as first one
            {
                last = temp;
                first = last;
                out.println("first");
            }
            else {
                System.out.println("insert else");
                Node pointer = last;
                int check1 = (int) (item);
                int check2 = (int) (pointer.item);

                while ((check1 < check2) && (pointer.before != null))  //from last if the number added before if bigger go
                {  //to the before node
                    pointer = pointer.before;
                }

                if (pointer == first)  //if it is the largest make it first
                {
                    pointer.before = temp;
                    temp.next = pointer;
                    first = temp;
                } else if (pointer == last)  //if it is the smallest make it last
                {
                    pointer.next = temp;
                    temp.before = pointer;
                    last = temp;
                } else  //splice it in between the 2 nodes
                {
                    pointer.next.before = temp;
                    temp.next = pointer.next.before;
                    pointer.next = temp;
                    temp.before = pointer;

                }
            }

            size++;
        }

        public int delMin()  //removes the node from the "first" node and returns the item
        {
            int item = first.item;  //grabs item from the oldest entry
            first = first.next;  //cycles it for the next call

            Node pointer  = first;
            while (pointer != null)
            {
                if (pointer.item == item)
                {
                    break;
                }
                pointer = pointer.next;
            }

            if (pointer != null)
            {
                remove(pointer.item);
            }

            size--;
            return (item);  //returns the data inside
        }

        public boolean contains(int item)
        {
            System.out.println("contains");
            Node pointer  = first;
            for (int i = 0; i < size; i++)
            {
                System.out.println("contains loop");
                if (pointer == null)
                {
                    return (false);
                }
                if (pointer.item == item)
                {
                    return (true);
                }
                pointer = pointer.next;
            }
            return (false);

            /*
            System.out.println("contains");
            Node pointer  = first;
            while (pointer != null)
            {
                System.out.println("contains loop");
                if (pointer.item == item)
                {
                    return (true);
                }
                pointer = pointer.next;
            }
            return (false);*/
        }

        public void remove(int item)
        {
            Node pointer  = first;
            while (pointer != null)
            {
                if (pointer.item == item)
                {
                    break;
                }
                pointer = pointer.next;
            }

            if (size == 1)
            {
                first = null;
                last = null;
                size--;
            }
            else if (size == 2)
            {
                if (pointer.next == null)
                {
                    last = pointer.before;
                    pointer.before.next = null;
                    pointer.before = null;
                }
                else
                {
                    first = pointer.next;
                    pointer.next.before = null;
                    pointer.next = null;
                }
                size--;
            }
            else if (pointer.before == null)
            {
                pointer.next = first;
                pointer.next.before = null;
                pointer.next = null;
                size--;
            }
            else if (pointer.next == null)
            {
                pointer.before = last;
                pointer.before.next = null;
                pointer.before = null;
                size--;
            }
            else
            {
                pointer.next.before = pointer.before.before;
                pointer.before.next = pointer.next.next;
                pointer.next = null;
                pointer.before = null;
                size--;
            }
        }

        public void change(int item, double length)
        {
            remove(item);
            insert(item, length);
        }

        public String toString()  //makes a String interpretation of the way the data is stored
        {
            String drawing = "";
            Node pointer = first;
            StringBuilder sb = new StringBuilder();

            while (pointer != null)  //if a node exist
            {
                sb.append('[').append(pointer.item).append(']');  //adds the item
                if (pointer.next != null) {
                    sb.append(", ");
                }
                pointer = pointer.next;  //go to next node
            }

            drawing = sb.toString();

            return (drawing);
        }
    }

    public class DijkstraSP
    {
        private EdgeWeight[] edgeTo;
        private double[] distTo;
        //private IndexMinPQ<Double> pq;
        private IndexMinPQ pq;
        public DijkstraSP(Graph G, int s)
        {
            edgeTo = new EdgeWeight[G.V()];
            distTo = new double[G.V()];
            //pq = new IndexMinPQ<Double>(G.V());
            pq = new IndexMinPQ();
            for (int v = 0; v < G.V(); v++)
                distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;
            pq.insert(s, 0.0);
            while (!pq.isEmpty())
            {
                System.out.println("out");
                relax(G, pq.delMin());
            }
        }
        private void relax(Graph G, int v)
        {
            for(EdgeWeight e : G.adj(v))
            {
                System.out.println("out1");
                int w = e.other(v);
                if (distTo[w] > distTo[v] + e.weight())
                {
                    System.out.println("inside if");
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w))
                    {
                        System.out.println("change");
                        pq.change(w, distTo[w]);
                    }
                    else
                    {
                        System.out.println("insert");
                        pq.insert(w, distTo[w]);
                    }
                }

                /*
                w = e.either();
                if (distTo[w] > distTo[v] + e.weight())
                {
                    System.out.println("inside if");
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w))
                    {
                        System.out.println("change");
                        pq.change(w, distTo[w]);
                    }
                    else
                    {
                        System.out.println("insert");
                        pq.insert(w, distTo[w]);
                    }
                }*/
            }
        }

        //public double distTo(int v) // standard client query methods
        public boolean hasPathTo(int v) // for SPT implementatations
        {
            return distTo[v] < Double.POSITIVE_INFINITY;
        }
        public Iterable<EdgeWeight> pathTo(int v) // (See page 649.)
        {
            System.out.println("path to");
            if (!hasPathTo(v))
            {
                System.out.println("no path");
                return (null);
            }
            Stack<EdgeWeight> path = new Stack<>();
            for (EdgeWeight x = edgeTo[v]; x != null; x = edgeTo[x.either()])
            {
                System.out.println("putting stuff");
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
                out.println(((EdgeWeight) j).weight());
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

        Lab4Question3.DijkstraSP dsp1 = lab.new DijkstraSP(graph, state2);

        Iterable path1 = dsp1.pathTo(state1);
        if (path == null)
        {
            for (Object i : path1)
            {
                EdgeWeight ew = (EdgeWeight) i;
                out.println(indexName[ew.either()]);
                out.println(indexName[ew.other(ew.either())]);
            }
        }
        else
        {
            for (Object i : path)
            {
                EdgeWeight ew = (EdgeWeight) i;
                out.println(indexName[ew.either()]);
                out.println(indexName[ew.other(ew.either())]);
            }
        }

    }
}



