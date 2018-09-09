import java.util.Stack;
import java.util.Scanner;
import static java.lang.System.out;
import java.util.Scanner;


public class Lab1Question2
{
    public static void main(String [] args)
    {
        Stack<Character> stack;  //initialises stack as a variable of type Stack and generic Character
        stack = new Stack<Character>();  //creates a stack with generic Character

        out.println("Enter input: ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();  //takes in the next input

        //out.println(s.charAt(3));  //debug code

        for(int i = 0; i < s.length(); i++)  //iterates through the input and puts them into the stack
        {
            stack.push(s.charAt(i));
        }

        for(int i = 0; i < s.length(); i++)  //iterates through the stack and takes them out
        {
            out.print(stack.pop());
        }

    }
}

/*
TODO
1 done
2 done
3 not done
4 not done

*/