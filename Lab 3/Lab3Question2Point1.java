import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Lab3Question2Point1
{
    //taken from the algorithm book
    public class BinarySearchST<Key extends Comparable<Key>, Value>
    {
        private Key[] keys;
        private Value[] vals;
        private int N;
        public BinarySearchST(int capacity)
        { // See Algorithm 1.1 for standard array-resizing code.
            keys = (Key[]) new Comparable[capacity];
            vals = (Value[]) new Object[capacity];
        }

        public boolean isEmpty()  //checks, if first == null, true else false
        {
            return (keys == null);
        }

        public int size()
        { return N; }

        public Value get(Key key)
        {
            if (isEmpty()) return null;
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) return vals[i];
            else return null;
        }

        public int rank(Key key)
        {
            int lo = 0, hi = N-1;
            while (lo <= hi)
            {
                int mid = lo + (hi - lo) / 2;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }
        // See page 381.

        public void put(Key key, Value val)
        { // Search for key. Update value if found; grow table if new.
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0)
            { vals[i] = val; return; }
            for (int j = N; j > i; j--)
            { keys[j] = keys[j-1]; vals[j] = vals[j-1]; }
            keys[i] = key; vals[i] = val;
            N++;
        }

        public boolean contains(Key key)
        {
            int size = size();

            for (int x = 0; x < size; x++)
            {
                if (key.equals(keys[x]))
                {
                    return (true);
                }
            }

            return (false);
        }

        public Key[] keys()
        {
            int tempLength = size();
            Key[] tempArray = (Key[]) new Comparable[tempLength];

            for (int x = 0; x < tempLength; x++)
            {
                tempArray[x] = this.keys[x];
            }

            return (tempArray);
        }
    }

    public static void main(String [] args) throws IOException
    {
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question2Point1 lab = new Lab3Question2Point1();
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
                    wordCounter++;
                    //System.out.println(sb.toString());  //replace with inserting
                    filteredWords[wordCounter] = sb.toString();
                    int delete = sb.length();
                    sb.delete(0, delete);
                    anotherCounter = 0;
                    spaceCounter = 0;
                }

                letterCounter++;
            }

            letterCounter = 0;
            word = in.nextLine();
        }
        Lab3Question2Point1.BinarySearchST<String, Integer> st =
                lab.new BinarySearchST<String, Integer>(100);

        for (int x = 0; x < filteredWords.length; x++)
        { // Build symbol table and count frequencies.
            String wordInput = filteredWords[x];

            if (!st.contains(word)) st.put(wordInput, 1);
            else st.put(wordInput, st.get(wordInput) + 1);
        }
// Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        for (String wordOutput : st.keys())
            if (st.get(wordOutput) > st.get(max))
                max = wordOutput;
        System.out.println(max + " " + st.get(max));
    }
}
