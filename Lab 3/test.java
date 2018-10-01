import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test {

    public class LinearProbingHashST<Key, Value> {
        private static final int INIT_CAPACITY = 4;

        private int n;           // number of key-value pairs in the symbol table
        private int m;           // size of linear probing table
        private Key[] keys;      // the keys
        private Value[] vals;    // the values

        public LinearProbingHashST() {
            this(INIT_CAPACITY);
        }

        public LinearProbingHashST(int capacity) {
            m = capacity;
            n = 0;
            keys = (Key[])   new Object[m];
            vals = (Value[]) new Object[m];
        }

        /*
        public int size() {
            return n;
        }

        public boolean isEmpty() {
            return size() == 0;
        }*/

        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        // hash function for keys - returns value between 0 and M-1
        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }

        // resizes the hash table to the given capacity by re-hashing all of the keys
        private void resize(int capacity) {
            LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
            for (int i = 0; i < m; i++) {
                if (keys[i] != null) {
                    temp.put(keys[i], vals[i]);
                }
            }
            keys = temp.keys;
            vals = temp.vals;
            m    = temp.m;
        }

        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("first argument to put() is null");

            if (val == null) {
                delete(key);
                return;
            }

            // double table size if 50% full
            if (n >= m/2) resize(2*m);

            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
                if (keys[i].equals(key)) {
                    vals[i] = val;
                    return;
                }
            }
            keys[i] = key;
            vals[i] = val;
            n++;
        }

        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to get() is null");
            for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
                if (keys[i].equals(key))
                    return vals[i];
            return null;
        }

        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            if (!contains(key)) return;

            // find position i of key
            int i = hash(key);
            while (!key.equals(keys[i])) {
                i = (i + 1) % m;
            }

            // delete key and associated value
            keys[i] = null;
            vals[i] = null;

            // rehash all keys in same cluster
            i = (i + 1) % m;
            while (keys[i] != null) {
                // delete keys[i] an vals[i] and reinsert
                Key   keyToRehash = keys[i];
                Value valToRehash = vals[i];
                keys[i] = null;
                vals[i] = null;
                n--;
                put(keyToRehash, valToRehash);
                i = (i + 1) % m;
            }

            n--;

            // halves size of array if it's 12.5% full or less
            if (n > 0 && n <= m/8) resize(m/2);

        }
    }

    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        test lab = new test();
        test.LinearProbingHashST<String, Integer> LBHash =
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
