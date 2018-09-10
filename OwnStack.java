import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.out;

public class OwnStack<Item> implements Iterable<Item>  //Question 1
{
    private Node first;  //oldest node
    private Node last;  //newest node

    private class Node
    {
        Item item;  //data storage
        Node next;  //pointer to the next node
        Node before;  //pointer to the previous node
    }

    public boolean isEmpty()  //checks, if first == null, true else false
    {
        return(first == null);
    }

    public Iterator<Item> iterator()
    {
        return (new StackIterator());
    }

    public class StackIterator implements Iterator<Item>
    {
        private Node pointer = first;

        @Override
        public boolean hasNext()
        {
            return (pointer != null);
        }

        @Override
        public Item next()
        {
            Item item = pointer.item;
            pointer = pointer.next;
            return (item);
        }
    }

    public void push(Item item)
    {
        Node oldlast = last;  //adds the last recent node to oldlast
        last = new Node();  //makes new node for this data
        last.item = item;  //puts data into the node
        last.next = null;  //we point the next part of this node to null

        if (isEmpty())  //if this is the first ie. if first == null
        {
            last.before = null;  //if the first data entry points backwards to null
            first = last;  //put this node into as first
            //System.out.println("first = last");
        }
        else
        {
            last.before = oldlast;  //points the new node to the older node backwards
            oldlast.next = last;  //points the previous entry to the new one added
            //System.out.println("next");
        }
    }

    public Item pop()
    {
        Item item = last.item;  //grabs item from the oldest entry
        if (! (first == last ))last = last.before;  //cycles it for the next call

        if (isEmpty())  //if at the end null
        {
            first = null;
            last = null;
            System.out.println("last");
        }

        return (item);  //returns the data inside
    }

    public String draw()
    {
        Node pointer = first;
        String drawing;
        StringBuilder sb = new StringBuilder();

        while (pointer != null)
        {
            sb.append('[').append(pointer.item).append(']');
            if (pointer.next != null)
            {
                sb.append(',');
            }
        }

        drawing = sb.toString();

        return (drawing);
    }

    public static void main(String [] args)
    {
        OwnStack<Character> stack;  //initialises stack as a variable of type Stack and generic Character
        stack = new OwnStack<Character>();  //creates a stack with generic Character

        out.println("Enter input: ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();  //takes in the next input

        //out.println(s.charAt(3));  //debug code

        for (int i = 0; i < s.length(); i++)  //iterates through the input and puts them into the stack
        {
            stack.push(s.charAt(i));
        }

        for (int i = 0; i < s.length()+1; i++)  //iterates through the stack and takes them out
        {
            out.print(stack.pop());
        }


        out.println("\nDrawing test:");

        /*
        for (int i = 0; i < s.length(); i++)  //iterates through the input and puts them into the stack
        {
            stack.push(s.charAt(i));
        }


        for (int i = 0; i < s.length(); i++)  //iterates through the stack and takes them out
        {
            out.println(stack.draw());
            stack.pop();
        }*/
    }
}
