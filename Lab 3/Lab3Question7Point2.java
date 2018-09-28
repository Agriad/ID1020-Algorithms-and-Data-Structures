import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Lab3Question7Point2
{
    public class LinearProbingHashST<Key, Value>
    {
        private int N; // number of key-value pairs in the table
        private int M = 16; // size of linear-probing table
        private Key[] keys; // the keys
        private Value[] vals; // the values
        public LinearProbingHashST()
        {
            keys = (Key[]) new Object[M];
            vals = (Value[]) new Object[M];
        }
        private int hash(Key key)
        { return (key.hashCode() & 0x7fffffff) % M; }

        private void resize(int cap)  // See page 474.
        {
            LinearProbingHashST<Key, Value> t;
            t = new LinearProbingHashST<Key, Value>(cap);
            for (int i = 0; i < M; i++)
                if (keys[i] != null)
                    t.put(keys[i], vals[i]);
            keys = t.keys;
            vals = t.vals;
            M = t.M;
        }

        public void put(Key key, Value val)
        {
            if (N >= M/2) resize(2*M); // double M (see text)
            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) % M)
                if (keys[i].equals(key)) { vals[i] = val; return; }
            keys[i] = key;
            vals[i] = val;
            N++;
        }

        public Value get(Key key)
        {
            for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
                if (keys[i].equals(key))
                    return vals[i];
            return null;
        }

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
    }

    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question7Point2 lab = new Lab3Question7Point2();
        Lab3Question7Point2.LinearProbingHashST<String, Integer> LBHash =
                lab.new LinearProbingHashST<String, Integer>();
        int inputAmount = 0;

        String[] filteredWords = new String[100];
        int counter = 0;

        long startAlgoTime = System.nanoTime();
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] lineArr = line.split("\\W+");

            for (int i = 0; i < lineArr.length && counter < 100; i++) {
                if (!LBHash.contains(lineArr[i])) {
                    LBHash.put(lineArr[i], 1);
                    filteredWords[counter] = lineArr[i];
                    counter++;
                    //out.println("success");
                } else {
                    int val = LBHash.get(lineArr[i]);
                    LBHash.put(lineArr[i], val + 1);
                }
            }
        }

        String max = "";  //add this as a benchmark
        LBHash.put(max, 0);

        for (int x = 0; x < counter; x++)  //for all the input check the highest amount of entry
        {
            //System.out.println(filteredWords[x] + ' ' + st.get(filteredWords[x]));
            if (LBHash.get(filteredWords[x]) > LBHash.get(max))  //if bigger than
            {
                max = filteredWords[x];
            }
        }

        System.out.println(max + " " + LBHash.get(max));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        long time1 = endTime - startAlgoTime;
        System.out.printf("Program time: %d ns\n", time);
        System.out.printf("Program algorithm time: %d ns", time1);
    }
}
