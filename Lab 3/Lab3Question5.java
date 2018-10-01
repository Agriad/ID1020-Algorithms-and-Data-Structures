import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

/*README
shows how the hashes are spread out. Duplicate words are still put it so the hashes could be those or collision
 */

public class Lab3Question5
{
    public class hashCodeViewer  //hasher and puts it into a binary search symbol table
    {
        Lab3Question2Point1 lab = new Lab3Question2Point1();

        Lab3Question2Point1.BinarySearchST<Integer, Integer> st =
                lab.new BinarySearchST  <Integer, Integer>(100000);

        public int hash(String s, int i)  //hashes the thing and takes a value for frequency
        {

            int hash = s.hashCode();
            //st.put(hash, );
            if (st.contains(hash))  //if there is one already just increase the frequency
            {
                int amount = st.get(hash);
                st.put(hash, amount + 1);
                return (i);
            }
            else  //if it is a new word put it in with value 1
            {
                st.put(hash, 1);
                return (i + 1);
            }
        }

        public Comparable[] keys()  //gives the keys array
        {
            return (st.keys());
        }

        public Integer get(int hash)  //gets the frequency of it
        {
            return (st.get(hash));
        }

    }

    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        Lab3Question5 lab = new Lab3Question5();
        Lab3Question5.hashCodeViewer hashCode = lab.new hashCodeViewer();
        int inputAmount = 0;

        while (in.hasNext())  //if there is a next line
        {
            String line = in.nextLine();
            String[] lineArr = line.split("\\W+");  //splits the words

            for (int i = 0; i < lineArr.length; i++)  //goes through the words and puts it in
            {
                inputAmount = hashCode.hash(lineArr[i], inputAmount);  //puts it in. gives the amount of unique input
                //out.println(inputAmount);
            }
        }

       Comparable[] hashes = hashCode.keys();

        for (int i = 0; i < inputAmount; i++)  //for the unique inputs
        {
            out.println(hashes[i]);  //prints out the hashes
            Integer input = (Integer) (hashes[i]);
            int printNum = hashCode.get(input);
            for (int j = 0; j < printNum; j++)  //for the frequency print the same amount of '*'
            {
                out.print('*');
            }
            out.println('\n');
        }

        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.printf("Program time: %d ns\n", time);
    }
}
