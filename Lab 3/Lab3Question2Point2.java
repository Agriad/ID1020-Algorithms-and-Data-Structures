import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*README
Program from algorithm book
Takes in first 100 words and finds the most used word. Does so by storing in a binary search tree.
 */

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

        public boolean contains(Key key)  //is ok i think
        {
            Node temp = contains(root, key);  //send in through root

            if (temp != null)
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

        private Node contains(Node x, Key key)  //first get in through root
        {
            if (x == null)  //if nothing return null
            {
                //System.out.println("null");
                return (x);
            }
            else
            {
                int check = key.compareTo(x.key);

                if (check < 0)  //if less go left
                {
                    //System.out.println("left");
                    x = contains(x.left, key);
                }
                else if (check > 0)  //if more go right
                {
                    //System.out.println("right");
                    x = contains(x.right, key);
                }
            }

            //System.out.println("out");
            return (x);  //just return the node
    }

// See page 399.
// See page 407 for min(), max(), floor(), and ceiling().
// See page 409 for select() and rank().
// See page 411 for delete(), deleteMin(), and deleteMax().
// See page 413 for keys().
    }


    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question2Point2 lab = new Lab3Question2Point2();
        int limit = 100;
        String word = in.nextLine();
        int wordCounter = 0;  //counter for 100 words
        int letterCounter = 0;  //counter so that it stays in the line
        int spaceCounter = 0;  //marker for the space after the word
        int anotherCounter = 0;  //marker for starting to add letters of the word
        String[] filteredWords = new String[limit];  //array to store the extracted words

        while (wordCounter < limit)  //word separator
        {
            StringBuilder sb = new StringBuilder();
            int stringLimit = word.length();

            while (letterCounter < stringLimit)  //while still need new words
            {
                if (word.charAt(letterCounter) == ' ' && anotherCounter != 0)  //checks if there is a space after the
                {//word
                    spaceCounter++;  //marks a space if found after a word
                }
                else if (word.charAt(letterCounter) != ' ')  //if a letter add it
                {
                    sb.append(word.charAt(letterCounter));
                    anotherCounter++;  //marks that a word is found
                    if (letterCounter == stringLimit - 1)
                    {
                        spaceCounter++;  //if at the end of the line and a letter is there mark it so that it can write
                    }
                }
                if (spaceCounter != 0)  //if a space after the word is found make it into a string and put it in the
                {//array
                    //System.out.println(sb.toString());  //replace with inserting
                    if (wordCounter < limit)  //prevent overfilling the array at the last line
                    {
                        //System.out.println(sb.toString());
                        filteredWords[wordCounter] = sb.toString();
                    }
                    int delete = sb.length();  //reset for the next word
                    sb.delete(0, delete);
                    anotherCounter = 0;
                    spaceCounter = 0;
                    wordCounter++;
                }

                letterCounter++;  //increment the pointer for the line
            }

            letterCounter = 0;  //reset for the next line
            //System.out.println(wordCounter);
            if (in.hasNextLine())
            {
                word = in.nextLine();
            }
        }

        long startAlgoTime = System.nanoTime();
        Lab3Question2Point2.BST<String, Integer> st =
                lab.new BST<String, Integer>();

        for (int x = 0; x < filteredWords.length; x++)  //for the words in the array put it in
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

        for (int x = 0; x < filteredWords.length; x++)  //for all the input check the highest amount of entry
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
        System.out.printf("Program algorithm time: %d ns", time1);
        System.out.printf("Program algorithm output time: %d ns", time2);
    }
}
