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
        { return size(root); }  //find it through the root first

        private int size(Node x)  //
        {
            if (x == null) return 0;  //if empty return 0
            else return x.N;  //returns the number of stuff under the node
        }

        public Value get(Key key)
        { return get(root, key); }  //send in through the root first

        private Value get(Node x, Key key)
        { // Return value associated with key in the subtree rooted at x;
            // return null if key not present in subtree rooted at x.
            if (x == null) return null;  //if doesn't exist return null
            int cmp = key.compareTo(x.key);  //get the comparison
            if (cmp < 0) return get(x.left, key);  //if less than go to the left
            else if (cmp > 0) return get(x.right, key);  //if bigger than go to the right
            else return x.val;  //return the value
        }

        // See page 399.
        public void put(Key key, Value val)
        { // Search for key. Update value if found; grow table if new.
            root = put(root, key, val);  //put it through the root first
        }

        private Node put(Node x, Key key, Value val)
        {// Change key’s value to val if key in subtree rooted at x.
            // Otherwise, add new node to subtree associating key with val.
            if (x == null) return new Node(key, val, 1);  //if empty put it there
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = put(x.left, key, val);  //if less than go to the left node
            else if (cmp > 0) x.right = put(x.right, key, val);  //if bigger go to the right node
            else x.val = val;  //if the same key overwrite the value
            x.N = size(x.left) + size(x.right) + 1;  //adds one and then it goes up and adds them up again
            return x;
        }

        public boolean contains(Key key)
        {
            //System.out.println("1");
            Node temp = null;

            if (root == null)
            {
                System.out.println("null access");
                return (false);
            }
            else
            {
                System.out.println("root access");
                temp = contains(root, key);  //send in through root
            }

            if (temp != null)
            {
                return (true);  //if found it exist
            }
            else
            {
                return (false);  //doesn't exist
            }
        }

        public Node contains(Node x, Key key)  //first get in through root
        {
            System.out.println(x.key);
            int compare = key.compareTo(x.key);

            if (compare < 0)  //if less than go to the left one
            {
                System.out.println("left access");
                contains(x.left, key);
            }
            else if (compare > 0)  //if larger than go to the right one
            {
                System.out.println("right access");
                contains(x.right, key);
            }
            else  //if equal return the node
            {
                System.out.println("equal or null access");
                return (x);
            }
            return (x);  //just return the node

            /*
            if (x == null)  //if null return the null node
            {
                return (x);
            }
            else if (compare < 0)  //if less than go to the left one
            {
                contains(x.left, key);
            }
            else if (compare > 0)  //if larger than go to the right one
            {
                contains(x.right, key);
            }
            else  //if equal return the node
            {
                return (x);
            }
            return (x);  //just return the node*/
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
        String[] filteredWords = new String[limit];

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
                    if (wordCounter < limit)
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

        /*
        boolean c = st.contains(filteredWords[0]);
        if (!c)
        {
            System.out.println("false c");
        }

        st.put(filteredWords[0], 1);
        st.put(filteredWords[0], 2);
        st.put(filteredWords[1], 1);

        boolean a = st.contains(filteredWords[0]);
        boolean b = st.contains(filteredWords[1]);

        if (a)
        {
            System.out.println("true a");
        }

        if (!a)
        {
            System.out.println("false a");
        }

        if (b)
        {
            System.out.println("true b");
        }*/


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

        /*
// Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);

        for (int x = 0; x < filteredWords.length; x++)
        {
            System.out.println((String) (filteredWords[x]) + ' ' + st.get((String) (filteredWords[x])));
            if (st.get((String) (filteredWords[x])) > st.get(max))
            {
                max = (String) (filteredWords[x]);
            }
        }

        System.out.println(max + " " + st.get(max));*/
    }
}
