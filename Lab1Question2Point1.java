import static java.lang.System.out;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Lab1Question2Point1  //Question 1
{
    public static void main(String [] args)
    {
        out.println("Enter input: ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        recursive(s, 0);  //calls the recursive function. 0 there to act as a counter instead of using global variable
    }

    public static void recursive(String s, int counter)
    {
        if(counter < s.length())  //checks if counter goes over the length
        {
            counter++;  //adds one to the counter
            recursive(s, counter);  //recursive but now with +1 from before in the counter
            counter--;  //removes one from the counter so that the counter is the same when it was originally called
            out.print(s.charAt(counter));
        }

        //counter--;  //if here causes issues with counter number and position
        //out.print(s.charAt(counter));
        //out.print(counter);
    }
}
