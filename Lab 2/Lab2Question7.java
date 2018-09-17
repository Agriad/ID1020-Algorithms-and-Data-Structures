import static java.lang.System.out;

import java.util.Iterator;
import java.util.Scanner;
import java.util.LinkedList;

/*
README
This is an implementation of a generic iterable FIFO queue with a double linked list with a structure of:
null<--1<-->2<-->3-->null
Where 3 is the newest data entered.
The structure of the linked list is heavily inspired from the Algorithms 4th ed
*/

public class Lab2Question7<Item> //Question 3
{
    //structure:  1-->2-->3-->null
    //where 1 is the first data entered
    //now null<--1<-->2<-->3-->null
    private Node first = null;  //oldest node and biggest value
    private Node last = null;  //newest node and smallest value
    private int size = 0;

    private class Node
    {
        Item item = null;  //data storage
        Node next = null;  //pointer to the next node
        Node before = null;  //pointer to the previous node
    }

    public boolean isEmpty()  //checks, if first == null, true else false
    {
        return (first == null);
    }

    public void enqueue(Item item)  //adds the item to the "last" node and links it
    {
        Node temp = new Node();
        temp.item = item;

        if (size == 0)
        {
            last = temp;
            first = last;
            //out.println("first");
        }
        else
        {
            Node pointer = last;
            int check1 = (int) (item);
            int check2 = (int) (pointer.item);

            while ((check1 > check2) && (pointer.before != null))
            {
                pointer = pointer.before;
            }

            if (pointer == first)
            {
                pointer.before = temp;
                temp.next = pointer;
                first = temp;
            }
            else if (pointer == last)
            {
                pointer.next = temp;
                temp.before = pointer;
                last = temp;
            }
            else
            {
                pointer.next.before = temp;
                temp.next = pointer.next.before;
                pointer.next = temp;
                temp.before = pointer;

            }
        }

        size++;
    }

    public Item dequeue()  //removes the node from the "first" node and returns the item
    {
        Item item = first.item;  //grabs item from the oldest entry
        first = first.next;  //cycles it for the next call

        if (isEmpty())  //if at the end null
        {
            last = null;
            //System.out.println("last");
        }

        size--;
        return (item);  //returns the data inside
    }

    public String toString()  //makes a String interpretation of the way the data is stored
    {
        String drawing = "";
        Node pointer = first;
        StringBuilder sb = new StringBuilder();

        while (pointer != null)  //if a node exist
        {
            sb.append('[').append(pointer.item).append(']');  //adds the item
            if(pointer.next != null)
            {
                sb.append(", ");
            }
            pointer = pointer.next;  //go to next node
        }

        drawing = sb.toString();

        return (drawing);
    }

    public static void main(String[] args)
    {
        int[] testArr = {1, 2, 3, 4, 4, 0};
        Lab2Question7 linkedList = new Lab2Question7();

        for (int x : testArr)
        {
            linkedList.enqueue(x);
            out.println(linkedList.toString());
        }
    }
}