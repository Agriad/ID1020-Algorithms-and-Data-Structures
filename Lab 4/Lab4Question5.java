import java.util.Iterator;

public class Lab4Question5
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


}
