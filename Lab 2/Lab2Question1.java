import static java.lang.System.out;
import java.util.Scanner;

public class Lab2Question1
{

    public static boolean less(Comparable a, Comparable b)
    {
        return (a.compareTo(b) < 0);
    }

    public static void exchange(Comparable[] input, int x, int y)
    {
        Comparable temp = input[x];
        input[x] = input[y];
        input[y] = temp;
    }

    public static void sort(Comparable[] input)
    {
        int length = input.length;
        StringBuilder sb = new StringBuilder();
        String picture = "";

        for (int x = 0; x < input.length; x++)
        {
        }

        for (int x = 1; x < length; x++)
        {
            for (int y = x; y > 0 && less(input[y], input[y - 1]); y--)
            {
                exchange(input, y, y - 1);
            }
        }
    }

    public static void main(String [] args)
    {
        Comparable[] intArray = null;
        out.println("Input array size: ");
        Scanner in = new Scanner(System.in);
        String size = in.nextLine();
        int s = Integer.parseInt(size);
        intArray = new Comparable[s];
        out.println("Input array items without space: ");
        String input = in.nextLine();

        for (int x = 0; x < input.length(); x++)
        {
            intArray[x] = Character.getNumericValue(input.charAt(x));
        }

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }

        Lab2Question1 sorting = new Lab2Question1();
        sorting.sort(intArray);

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }
    }
}
