import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Lab3Question2Point2
{

    public class BST<Key extends Comparable<Key>, Value>
    {
        private Node root; // root of BST
        private class Node
        {
            private Key key; // key
            private Value val; // associated value
            private Node left, right; // links to subtrees
            private int N; // # nodes in subtree rooted here
            public Node(Key key, Value val, int N)
            { this.key = key; this.val = val; this.N = N; }
        }

        public int size()
        { return size(root); }

        private int size(Node x)
        {
            if (x == null) return 0;
            else return x.N;
        }

        public Value get(Key key)
        { return get(root, key); }

        private Value get(Node x, Key key)
        { // Return value associated with key in the subtree rooted at x;
            // return null if key not present in subtree rooted at x.
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) return get(x.left, key);
            else if (cmp > 0) return get(x.right, key);
            else return x.val;
        }

        // See page 399.
        public void put(Key key, Value val)
        { // Search for key. Update value if found; grow table if new.
            root = put(root, key, val);
        }

        private Node put(Node x, Key key, Value val)
        {// Change key’s value to val if key in subtree rooted at x.
            // Otherwise, add new node to subtree associating key with val.
            if (x == null) return new Node(key, val, 1);
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = put(x.left, key, val);
            else if (cmp > 0) x.right = put(x.right, key, val);
            else x.val = val;
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }

        public boolean contains(Key key)
        {}

        public Key[] keys()
        {
            Key[] temp = (Key[]) new Comparable[size()];
        }

// See page 399.
// See page 407 for min(), max(), floor(), and ceiling().
// See page 409 for select() and rank().
// See page 411 for delete(), deleteMin(), and deleteMax().
// See page 413 for keys().
    }


    public static void main(String [] args) throws IOException
    {
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question2Point2 lab = new Lab3Question2Point2();
        int limit = 100;
        String word = in.nextLine();
        int wordCounter = 0;
        int letterCounter = 0;
        int spaceCounter = 0;
        int anotherCounter = 0;
        String[] filteredWords = new String[100];

        while (wordCounter < limit)
        {
            StringBuilder sb = new StringBuilder();
            int stringLimit = word.length();
            while (letterCounter < stringLimit)
            {
                if (word.charAt(letterCounter) == ' ' && anotherCounter != 0)
                {
                    spaceCounter++;
                }
                else if (word.charAt(letterCounter) != ' ')
                {
                    sb.append(word.charAt(letterCounter));
                    anotherCounter++;
                    if (letterCounter == stringLimit - 1)
                    {
                        spaceCounter++;
                    }
                }
                if (spaceCounter != 0)
                {
                    //System.out.println(sb.toString());  //replace with inserting
                    if (wordCounter < 100)
                    {
                        //System.out.println(sb.toString());
                        filteredWords[wordCounter] = sb.toString();
                    }
                    int delete = sb.length();
                    sb.delete(0, delete);
                    anotherCounter = 0;
                    spaceCounter = 0;
                    wordCounter++;
                }

                letterCounter++;
            }

            letterCounter = 0;
            word = in.nextLine();
        }
        Lab3Question2Point2.BST<String, Integer> st =
                lab.new BST<String, Integer>();

        for (int x = 0; x < filteredWords.length; x++)
        { // Build symbol table and count frequencies.
            String wordInput = filteredWords[x];

            if (!st.contains(wordInput))
            {
                st.put(wordInput, 1);
            }
            else
            {
                st.put(wordInput, st.get(wordInput) + 1);
            }
        }
// Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        /*for (String wordOutput : (String[]) (st.keys()))
            if (st.get(wordOutput) > st.get(max))
                max = wordOutput;*/
        Comparable[] outputArray = st.keys();

        for (int x = 0; x < outputArray.length; x++)
        {
            System.out.println((String) (outputArray[x]) + ' ' + st.get((String) (outputArray[x])));
            if (st.get((String) (outputArray[x])) > st.get(max))
            {
                max = (String) (outputArray[x]);
            }
        }

        System.out.println(max + " " + st.get(max));
    }
}
