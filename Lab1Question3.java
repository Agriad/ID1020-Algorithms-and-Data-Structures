import static java.lang.System.out;
import java.util.Scanner;
import java.util.LinkedList;

public class Lab1Question3
{
    public static void main(String [] args)
    {
        String input;
        Scanner in = new Scanner(System.in);
        out.println("Enter input: ");
        input = in.nextLine();

        char[] testArray = {'a', 'b', 'c'};
        DoubleLinkedList<Character> linkedList;
        linkedList = new DoubleLinkedList<Character>();

        for(int i = 0; i < testArray.length; i++)
        {
            linkedList.enqueue(testArray[i]);
        }

        for(int i = 0; i < testArray.length; i++)
        {
            temp = linkedList.next();
        }

        for(int i = 0; i < testArray.length; i++)
        {
            char temp = linkedList.dequeue();
            out.print(temp);
        }
    }
}
