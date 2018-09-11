import java.util.Stack;
import java.util.Scanner;
import static java.lang.System.out;
import java.util.Scanner;

/*
README
This program takes in terminal inputs and reverses them.
As this is done by a stack implemented using a static array size the limit is 10 characters.
*/

public class Lab1Question2  //Question 2
{
    private char arr[] = new char[10];  //the stack memory
    private int last = -1;  //pointer of the stack

    public void push(char c)
    {
        last++;  //adds before so it doesn't cause array out of bounds exception
        arr[last] = c;  //adds the char to the array
        //out.println(last);
    }

    public char pop()
    {
        char c = arr[last];  //grabs the char in the array
        //out.println(last);
        if (last > -1)  //checks if the pointer should be reduced
        {
            last--;
        }

        return (c);  //returns the character
    }

    public String draw()
    {
        int pointer = last;  //takes in the pointer value
        String drawing = "";
        StringBuilder sb = new StringBuilder();

        while (pointer != -1)  //while still in the array
        {
            sb.append('[').append(arr[pointer]).append(']');
            if (pointer > 0)  //if at the end don't add ','
            {
                sb.append(", ");
            }
            pointer--;
        }

        //sb.append(last);
        drawing = sb.toString();

        return (drawing);
    }

    public static void main(String [] args)
    {
        Lab1Question2 stack = new Lab1Question2();
        out.println("Enter input: ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();  //takes in the next input

        //out.println(s.charAt(3));  //debug code

        for (int i = 0; i < s.length(); i++)  //iterates through the input and puts them into the stack
        {
            stack.push(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++)  //iterates through the stack and takes them out
        {
            out.print(stack.pop());
        }

        out.println(stack.draw());
        out.println("\nDrawing test:");


        for (int i = 0; i < s.length(); i++)  //iterates through the input and puts them into the stack
        {
            out.println(stack.draw());
            stack.push(s.charAt(i));
        }


        for (int i = 0; i < s.length(); i++)  //iterates through the stack and takes them out
        {
            out.println(stack.draw());
            stack.pop();
        }
    }
}

/*
TODO
1 done
2 done
2.1 done (add drawing, use ADT)
3 done (add iterable, add comment)
4 done (fix iterator)
5 done (add comment)
6 not done

*/