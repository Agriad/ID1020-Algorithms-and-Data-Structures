import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Lab3Question5
{
    public class hashCodeViewer
    {
        Lab3Question2Point1 lab = new Lab3Question2Point1();

        Lab3Question2Point1.BinarySearchST<Integer, Integer> st =
                lab.new BinarySearchST  <Integer, Integer>(100000);

        public int hash(String s, int i)
        {

            int hash = s.hashCode();
            //st.put(hash, );
            if (st.contains(hash))
            {
                int amount = st.get(hash);
                st.put(hash, amount + 1);
                return (i);
            }
            else
            {
                st.put(hash, 1);
                return (i + 1);
            }
        }

        public Comparable[] keys()
        {
            return (st.keys());
        }

        public Integer get(int hash)
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

        while (in.hasNext())
        {
            String line = in.nextLine();
            String[] lineArr = line.split("\\W+");

            for (int i = 0; i < lineArr.length; i++)
            {
                inputAmount = hashCode.hash(lineArr[i], inputAmount);
                //out.println(inputAmount);
            }
        }

       Comparable[] hashes = hashCode.keys();

        for (int i = 0; i < inputAmount; i++)
        {
            out.println(hashes[i]);
            Integer input = (Integer) (hashes[i]);
            int printNum = hashCode.get(input);
            for (int j = 0; j < printNum; j++)
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
