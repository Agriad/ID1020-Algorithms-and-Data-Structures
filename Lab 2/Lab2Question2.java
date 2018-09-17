import static java.lang.System.out;
import java.util.Scanner;
import java.util.Arrays;

/*
README
Insertion sort
with rearrange in the end
ex:
3214
takes 1 checks with 3 and 2
swaps with 3 and 2
1324
takes 2 checks with 3 and 1
swaps with 3
1234
 */

public class Lab2Question2
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

        out.println(Arrays.toString(input));
        for (int x = 1; x < length; x++)  //iterates through the array from index 1
        {
            for (int y = x; y > 0 && less(input[y], input[y - 1]); y--)  //iterates through the array that has not been
            {  //iterated. checks if index y - 1 item is bigger than index y if true and goes in
                exchange(input, y, y - 1);
                out.println(Arrays.toString(input));
            }
        }

        for (int x = 0; x < (input.length / 2); x++)  //iterates through half the array and swap with opposite end
        {
            exchange(input, x, input.length - (x + 1));
        }

        out.println(Arrays.toString(input));
    }

    public static void main(String [] args)
    {
        Comparable[] intArray = null;
        out.println("Input array size: ");  //takes in input as array size
        Scanner in = new Scanner(System.in);
        String size = in.nextLine();
        int s = Integer.parseInt(size);
        intArray = new Comparable[s];
        out.println("Input array items without space: ");  //takes in input as array items
        String input = in.nextLine();

        for (int x = 0; x < input.length(); x++)
        {
            intArray[x] = Character.getNumericValue(input.charAt(x));  //put input into array
        }

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }

        Lab2Question2 sorting = new Lab2Question2();
        sorting.sort(intArray);  //puts into sorting algorithm

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }
    }
}

