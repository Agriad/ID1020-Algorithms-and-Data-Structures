import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Lab3Question3
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
            if (x == null)
            {
                //System.out.println("null");
                return (x);
            }
            else
            {
                int check = key.compareTo(x.key);

                if (check < 0)
                {
                    //System.out.println("left");
                    x = contains(x.left, key);
                }
                else if (check > 0)
                {
                    //System.out.println("right");
                    x = contains(x.right, key);
                }
            }

            //System.out.println("out");
            return (x);
        }

        public Key[] keys()
        {
            Key[] temp = (Key[]) new Comparable[root.N];



            return (temp);
        }

        private Key[]

// See page 399.
// See page 407 for min(), max(), floor(), and ceiling().
// See page 409 for select() and rank().
// See page 411 for delete(), deleteMin(), and deleteMax().
// See page 413 for keys().
    }


    public static void main(String [] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question3 lab = new Lab3Question3();
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
        Lab3Question3.BST<String, Integer> st =
                lab.new BST<String, Integer>();

        for (int x = 0; x < filteredWords.length; x++)
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
        String max = "";
        st.put(max, 0);

        for (int x = 0; x < filteredWords.length; x++)
        {
            //System.out.println(filteredWords[x] + ' ' + st.get(filteredWords[x]));
            if (st.get(filteredWords[x]) > st.get(max))
            {
                max = filteredWords[x];
            }
        }

        System.out.println(max + " " + st.get(max));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.printf("Program time: %d ns\n", time);

        String[][] sorted = new String[limit / 2][];
        int putCounter = 1;
        int counterSort = 0;
        int counterInnerSort = 0;

        for (int x = sorted.length; x >= 1; x--)
        {
            String[] temp = new String[limit];
            for (int y = 0; y < limit; y++)
            {
                if (st.get(filteredWords[y]) == x)  //issue is filtered words contain multiple
                {
                    temp[counterInnerSort] = filteredWords[y];
                    sorted[putCounter] = temp;
                    counterSort = 1;
                    System.out.println(x);
                    System.out.println(Arrays.toString(temp));
                    counterInnerSort++;
                }
            }

            counterInnerSort = 0;

            if (counterSort == 1)
            {
                counterSort = 0;
                putCounter++;
            }
        }

        //System.out.println(Arrays.toString(sorted));
        //System.out.println(Arrays.toString(sorted[1]));
        //System.out.println(Arrays.toString(sorted[2]));

        Scanner inType = new Scanner(System.in);
        int loop = 0;
        while (loop == 0)
        {
            System.out.println("To exit type abc");
            System.out.println("Type 2 numbers \"N X\" to check for the Nth to the Xth most used word");
            String input = inType.nextLine();

            if (input.equals("abc"))
            {
                loop = 1;
            }
            else
            {
                String[] output = new String[limit];
                String[] numbers = input.split("\\W+");

                System.out.println(numbers[0]);
                System.out.println(numbers[1]);
                int number1 = Integer.parseInt(numbers[0]);
                int number2 = Integer.parseInt(numbers[1]);
                int outputCounter = 0;
                max = "";
                st.put(max, 0);
                for (int x = 1; x < putCounter; x++)
                {
                    for (int y = 0; y < sorted[x].length; y++)
                    {
                        if ((number1 <= x) && (x <= number2))
                        {
                            if(sorted[x][y] != null)
                            {
                                output[outputCounter] = sorted[x][y];
                                outputCounter++;
                            }
                        }
                    }
                }

                for (String s : output)
                {
                    if (s != null)
                    {
                        int number = st.get(s);
                        System.out.printf("%s %d\n", s, number);
                    }
                }
            }
        }

    }
}

