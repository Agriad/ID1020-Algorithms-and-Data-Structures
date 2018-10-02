import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

/*README
Program from algorithm book
A red black tree so that it becomes balanced always. Takes the first 100 words and finds how much of each word is used.
 */

public class Lab3Question4Test
{
    public class RedBlackBST<Key extends Comparable<Key>, Value>
    {
        private Node root;
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private class Node  // BST node with color bit (see page 433)
        {
            Key key; // key
            Value val; // associated data
            Node left, right; // subtrees
            int N; // # nodes in this subtree
            boolean color; // color of link from
            // parent to this node
            Node(Key key, Value val, int N, boolean color)  //constructor
            {
                this.key = key;
                this.val = val;
                this.N = N;
                this.color = color;
            }
        }

        private boolean isRed(Node x)  // See page 433.
        {//checks if it is red
            if (x == null) return false;
            return x.color == RED;
        }

        private Node rotateLeft(Node h) // See page 434.
        {  //changes the right (x) red node to black and makes it the "parent" to the now red left (h) one
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            x.N = h.N;
            h.N = 1 + size(h.left) + size(h.right);
            return x;
        }

        private Node rotateRight(Node h) // See page 434.
        {  //changes the left (x) red node to black and makes it the "parent" to the now red right (h) one
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            x.N = h.N;
            h.N = 1 + size(h.left) + size(h.right);
            return x;
        }

        private void flipColors(Node h) // See page 436.
        {
            h.color = RED;
            h.left.color = BLACK;
            h.right.color = BLACK;
        }

        private int size() // See page 398.
        {//gets the size of the whole tree
            return size(root);
        }

        private int size(Node x)
        {//returns the size from the root since they already added up from the leaves all the way to almost root
            if (x == null) return 0;
            else return x.N;
        }

        public void put(Key key, Value val)
        { // Search for key. Update value if found; grow table if new.
            root = put(root, key, val);  //put it through the root
            root.color = BLACK;  //root is always black
        }

        private Node put(Node h, Key key, Value val)
        {
            if (h == null) // Do standard insert, with red link to parent.
            {
                return new Node(key, val, 1, RED);  //if empty make it red like a 3 node
            }

            int cmp = key.compareTo(h.key);  //compare to see left right "middle"
            if (cmp < 0)
            {
                h.left = put(h.left, key, val);  //if less for left
            }
            else if (cmp > 0)
            {
                h.right = put(h.right, key, val);  //if more go right
            }
            else
            {
                h.val = val;  //if equal update value
            }

            //after inserting check if there needs adjustment to keep balance
            if (isRed(h.right) && !isRed(h.left))  //if right red and left black
            {  //should be left leaning bias
                h = rotateLeft(h);
            }
            if (isRed(h.left) && isRed(h.left.left))  //if left red and the left of the left red
            {  //change order as there should not be a 4 node
                h = rotateRight(h);
            }
            if (isRed(h.left) && isRed(h.right))   //if left red and right red
            {  //change color to black as there should not be a four node
                flipColors(h);
            }

            h.N = size(h.left) + size(h.right) + 1;  //when adding add up slowly up and add 1 for the new node
            return h;
        }

        public Value get(Key key)  //gets the value
        {
            Value h = get(root, key);

            /*if (h == null)
            {
                return (null);
            }*/

            return (h);
        }

        private Value get(Node h, Key key)
        {
            if (h == null)  //if empty and doesn't exist return null
            {
                return (null);
            }
            else
            {
                int check = key.compareTo(h.key);

                if (check < 0)  //if less get the value of the left node
                {
                    return (get(h.left, key));
                }
                else if (check > 0)  //if bigger get the value of the right node
                {
                    return (get(h.right, key));
                }
                else  //if equal return the value
                {
                    return (h.val);
                }
            }
        }

        public boolean contains(Key key)
        {
            Node h = contains(root, key);

            if (h != null)
            {
                //System.out.println("there");
                return (true);  //if found it exist
            }
            else
            {
                //System.out.println("not there");
                return (false);  //doesn't exist
            }
        }

        private Node contains(Node h, Key key)
        {
            if (h == null)  //if nothing return null
            {
                //System.out.println("null");
                return (h);
            }
            else
            {
                int check = key.compareTo(h.key);

                if (check < 0)  //if less go left
                {
                    //System.out.println("left");
                    h = contains(h.left, key);
                }
                else if (check > 0)  //if more go right
                {
                    //System.out.println("right");
                    h = contains(h.right, key);
                }
            }

            //System.out.println("out");
            return (h);  //just return the node
        }
    }

    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question4Test lab = new Lab3Question4Test();
        int inputCounter = 0;
        String[] filteredWords = new String[1000000];
        StringBuilder sb = new StringBuilder();

        while (in.hasNext())
        {
            String line = in.nextLine();
            String[] lineArr = line.split("\\W+");

            for (int i = 0; i < lineArr.length; i++)
            {
                //out.println(lineArr[i]);
                filteredWords[inputCounter] = lineArr[i];
                inputCounter++;
            }
        }

        long startAlgoTime = System.nanoTime();
        Lab3Question4Test.RedBlackBST<String, Integer> st =
                lab.new RedBlackBST<String, Integer>();

        for (int x = 0; x < inputCounter; x++)  //for the words in the array put it in
        { // Build symbol table and count frequencies.
            String wordInput = filteredWords[x];

            if (!st.contains(wordInput))
            {
                //System.out.printf("input 1: %s, %d\n" ,wordInput , x);
                st.put(wordInput, 1);
            }
            else
            {
                //System.out.printf("input 2: %s, %d\n" ,wordInput , x);
                st.put(wordInput, st.get(wordInput) + 1);
            }
        }

// Find a key with the highest frequency count.
        long outputTime = System.nanoTime();
        String max = "";
        st.put(max, 0);

        for (int x = 0; x < inputCounter; x++)  //for all the input check the highest amount of entry
        {
            //System.out.println(filteredWords[x] + ' ' + st.get(filteredWords[x]));
            if (st.get(filteredWords[x]) > st.get(max))  //if bigger than
            {
                max = filteredWords[x];
            }
        }

        System.out.println(max + " " + st.get(max));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        long time1 = endTime - startAlgoTime;
        long time2 = endTime - outputTime;
        System.out.printf("Program time: %d ns\n", time);
        System.out.printf("Program algorithm time: %d ns\n", time1);
        System.out.printf("Program algorithm output time: %d ns", time2);
    }
}

