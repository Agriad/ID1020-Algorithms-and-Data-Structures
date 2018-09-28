import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

public class Lab3Question7Point1
{
    public class SequentialSearchST<Key, Value>
    {
        private Node first; // first node in the linked list
        private class Node
        { // linked-list node
            Key key;
            Value val;
            Node next;
            public Node(Key key, Value val, Node next)
            {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }
        public Value get(Key key)
        { // Search for key, return associated value.
            for (Node x = first; x != null; x = x.next)
                if (key.equals(x.key))
                    return x.val; // search hit
            return null; // search miss
        }
        public void put(Key key, Value val)
        { // Search for key. Update value if found; grow table if new.
            for (Node x = first; x != null; x = x.next)
                if (key.equals(x.key))
                { x.val = val; return; } // Search hit: update val.
            first = new Node(key, val, first); // Search miss: add new node.
        }

    }

    public class SeparateChainingHashST<Key, Value>
    {
        private int N; // number of key-value pairs
        private int M; // hash table size
        private SequentialSearchST<Key, Value>[] st; // array of ST objects

        public SeparateChainingHashST()
        { this(997); }

        public SeparateChainingHashST(int M)
        { // Create M linked lists.
            this.M = M;
            st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
            for (int i = 0; i < M; i++)
                st[i] = new SequentialSearchST();
        }
        private int hash(Key key)
        { return (key.hashCode() & 0x7fffffff) % M; }

        public Value get(Key key)
        { return (Value) st[hash(key)].get(key); }

        public void put(Key key, Value val)
        { st[hash(key)].put(key, val); }

        public boolean contains(Key key)
        {
            Value ans = get(key);
            if (ans == null)
            {
                return (false);
            }
            else
            {
                return (true);
            }
        }
        //public Iterable<Key> keys()
// See Exercise 3.4.19.
    }

    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question7Point1 lab = new Lab3Question7Point1();
        Lab3Question7Point1.SeparateChainingHashST<String, Integer> SCHash =
                lab.new SeparateChainingHashST<String, Integer>();
        int inputAmount = 0;
        Lab3Question2Point1 lab1 = new Lab3Question2Point1();
        //Lab3Question2Point1.BinarySearchST Bsst = lab1.new BinarySearchST(100000);

        String[] filteredWords = new String[100];
        int counter = 0;

        long startAlgoTime = System.nanoTime();
        while (in.hasNext())
        {
            String line = in.nextLine();
            String[] lineArr = line.split("\\W+");

            for (int i = 0; i < lineArr.length && counter < 100; i++)
            {
                if (!SCHash.contains(lineArr[i]))
                {
                    SCHash.put(lineArr[i], 1);
                    filteredWords[counter] = lineArr[i];
                    counter++;
                    //out.println("success");
                }
                else
                {
                    int val = SCHash.get(lineArr[i]);
                    SCHash.put(lineArr[i], val + 1);
                }
            }
        }

        String max = "";  //add this as a benchmark
        SCHash.put(max, 0);

        for (int x = 0; x < counter; x++)  //for all the input check the highest amount of entry
        {
            //System.out.println(filteredWords[x] + ' ' + st.get(filteredWords[x]));
            if (SCHash.get(filteredWords[x]) > SCHash.get(max))  //if bigger than
            {
                max = filteredWords[x];
            }
        }

        System.out.println(max + " " + SCHash.get(max));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        long time1 = endTime - startAlgoTime;
        System.out.printf("Program time: %d ns\n", time);
        System.out.printf("Program algorithm time: %d ns", time1);
    }
}
