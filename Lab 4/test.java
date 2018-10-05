import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

public class test
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Text.txt"));
        String[] check = new String[107 * 2];
        int lineCount = 0;
        int counter = 0;
        int count = 0;
        int count2 = 0;

        while (in.hasNext())
        {
            lineCount++;
            String[] inputs = in.nextLine().split(" ");
            for (int i = 0; i < check.length; i++)
            {
                if (inputs[0].equals(check[i]))
                {
                    count++;
                }
                if (inputs[1].equals(check[i]))
                {
                    count2++;
                }
            }
            if (count == 0)
            {
                check[counter] = inputs[0];
                out.println("a" + counter);
                counter++;
            }
            if (count2 == 0)
            {
                check[counter] = inputs[1];
                out.println("b" + counter);
                counter++;
            }
            count2 = 0;
            count = 0;
        }

        //System.out.println(lineCount);

        for (int i = 0; i < counter; i++)
        {
            System.out.println(check[i] + " " + (check[i].hashCode() % 211));
        }

        System.out.println(counter);

        int ouch = 0;

        for (int i = 0; i < counter; i++)
        {
            for (int j = 0; j < counter; j++)
            {
                if (check[i].hashCode() == check[j].hashCode())
                {
                    ouch++;
                    //System.out.println(check[i] + " " + check[j]);
                }
            }
        }

        if (ouch != counter)
        {
            System.out.print("oof");
        }
    }
}