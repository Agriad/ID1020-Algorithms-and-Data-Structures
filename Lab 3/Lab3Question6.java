import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.out;

public class Lab3Question6
{
    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        Scanner in = new Scanner(new File("TextModified.txt"));
        Scanner sc = new Scanner(System.in);
        out.println("Word: ");
        String input = sc.nextLine();
        int indexCounter = 0;
        int inputCounter = 0;
        int[] index = new int[10000];
        StringBuilder sb = new StringBuilder();

        while (in.hasNext())
        {
            String sentence = in.nextLine();
            for (int i = 0; i < sentence.length(); i++)
            {
                char c = sentence.charAt(i);
                //out.println(sb.toString());
                if (c != ' ')
                {
                    sb.append(c);
                    //out.println(sb.toString());
                }
                else
                {
                    String badWord = sb.toString();
                    int deleteBW = badWord.length();
                    sb.delete(0, deleteBW);
                }

                String word = sb.toString();

                if (word.equals(input))
                {
                    index[inputCounter] = indexCounter - word.length();
                    inputCounter++;
                    int deleteAmount = word.length();
                    sb.delete(0, deleteAmount);
                }

                //out.println(inputCounter);
                indexCounter++;
            }

            String line = sb.toString();
            int deleteLine = line.length();
            sb.delete(0, deleteLine);
        }

        /*
        while (in.hasNext())
        {
            String line = in.nextLine();
            String[] lineArr = line.split("\\W+");

            for (int i = 0; i < lineArr.length; i++)
            {
                for (int j = 0; j < l)

                //out.println(lineArr[i] + i);
                if (lineArr[i].equals(input))
                {
                    index[inputCounter] = indexCounter;
                    inputCounter++;
                }

                //indexCounter++;
            }
        }*/

        out.println("The word " + input + " are in indexes:");

        for (int i = 0; i < inputCounter; i++)
        {
            out.println(index[i] + " ");
        }

        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.printf("Program time: %d ns\n", time);
    }
}
