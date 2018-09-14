import static java.lang.System.out;
import java.util.Scanner;
import java.util.Arrays;

public class Lab2Question4
{

    public static boolean less(Comparable a, Comparable b)
    {
        return (a.compareTo(b) < 0);  //if a less b returns -1
    }  // if -1 less than 0

    public static void exchange(Comparable[] input, int x, int y)
    {
        Comparable temp = input[x];  //take the data from x index
        input[x] = input[y];  //put y index data into x index
        input[y] = temp;  //put the x data into the y index
    }

    public static void sort(Comparable[] input)
    {
        int length = input.length;
        int counter = 0;

        out.println(Arrays.toString(input));
        for (int x = 1; x < length; x++)  //iterates through the array from index 1
        {
            for (int y = x; y > 0 && less(input[y], input[y - 1]); y--)  //iterates through the array that has not been
            {  //iterated. checks if index y - 1 item is bigger than index y if true and goes in
                exchange(input, y, y - 1);
                out.println(Arrays.toString(input));
                counter++;
                //out.printf("Swap number: %d\n", counter);
            }
        }

        out.printf("Amount of swap(s): %d\n", counter);

        for (int x = 0; x < (input.length / 2); x++)
        {
            exchange(input, x, input.length - (x + 1));
        }

        out.println(Arrays.toString(input));
    }

    public static void inversion(Comparable[] input)
    {
        StringBuilder sb = new StringBuilder();

        for (int x = 1; x < input.length; x++)
        {
            for (int y = x; y < input.length; y++)
            {
                if (less(input[y], input[y - 1]));
                {
                    sb.append("[").append(x - 1).append(',').append(input[x - 1]).append(']');
                    sb.append("[").append(y).append(',').append(input[y]).append(']');
                    sb.append(' ');
                }
            }
            //out.println(sb.toString());
            //sb.delete(0,11);
            sb.append('\n');
        }
        out.println(sb.toString());
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

        int[] test = {1, 2 ,3};

        for (int x = 0; x < input.length(); x++)
        {
            intArray[x] = Character.getNumericValue(input.charAt(x));
        }

        inversion(intArray);

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }

        Lab2Question4 sorting = new Lab2Question4();
        sorting.sort(intArray);

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }
    }
}

