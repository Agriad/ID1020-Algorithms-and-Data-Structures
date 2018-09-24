import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Lab3Question1
{
    public static void textFilter(String text) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(text));
        String line = "";
        FileWriter fr = new FileWriter("TextModified.txt");

        while ((line = br.readLine()) != null)
        {
            System.out.println("1");
            int x = 0;
            int limit = line.length();

            while (x < limit)
            {
                Character c = line.charAt(x);
                x++;

                if (!((Character.isAlphabetic(c)) || (c == ' ')))
                {
                    fr.append(' ');
                }
                else
                {
                    fr.append(c);
                }
            }

            fr.append("\n");
        }

        fr.flush();
        fr.close();
    }

    public static void main(String [] args) throws IOException
    {
        Lab3Question1 filter = new Lab3Question1();
        String text = "Text.txt";

        filter.textFilter(text);
    }
}
