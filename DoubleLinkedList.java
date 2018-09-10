import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.out;

public class DoubleLinkedList<Item> implements Iterable<Item>  //Question 3
{
    //structure:  1-->2-->3-->null
    //where 1 is the first data entered
    //now null<-->1<-->2<-->3<-->null
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
        return (first == null);
    }

    @Override
    public Iterator<Item> iterator()
    {
        return (new DoubleLinkedListIterator());
    }

    private class DoubleLinkedListIterator implements Iterator<Item>
    {
        private Node pointer = first;

        @Override
        public boolean hasNext()  //checks if there is a next node
        {
            return (pointer != null);
        }

        public void remove() {
        }

        @Override
        public Item next() {
            Item item = pointer.item;
            pointer = pointer.next;
            return (item);
        }
    }


    public void enqueue(Item item)
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
        else {
            last.before = oldlast;  //points the new node to the older node backwards
            oldlast.next = last;  //points the previous entry to the new one added
            //System.out.println("next");
        }
    }

    public Item dequeue()
    {
        Item item = first.item;  //grabs item from the oldest entry
        first = first.next;  //cycles it for the next call

        if (isEmpty())  //if at the end null
        {
            last = null;
            //System.out.println("last");
        }

        return (item);  //returns the data inside
    }

    public String draw()
    {
        String drawing = "";
        Node pointer = first;
        StringBuilder sb = new StringBuilder();

        while (pointer != null)
        {
            sb.append('[').append(pointer.item).append(']');
            if(pointer.next != null)
            {
                sb.append(", ");
            }
            pointer = pointer.next;
        }

        drawing = sb.toString();

        return (drawing);
    }

    public static void main(String[] args)
    {
        String input;
        Scanner in = new Scanner(System.in);
        //out.println("Enter input: ");
        //input = in.nextLine();

        char[] testArray = {'a', 'b', 'c'};
        //char[] testArray = {'a'};
        DoubleLinkedList<Character> linkedList;
        linkedList = new DoubleLinkedList<Character>();
        out.println("Enqueue and dequeue test: ");

        for (int i = 0; i < testArray.length; i++)
        {
            linkedList.enqueue(testArray[i]);
        }

        for (int i = 0; i < testArray.length; i++)
        {
            char temp = linkedList.dequeue();
            out.print(temp);
        }

        out.println("\nIterator test: ");

        for (char x : testArray)
        {
            linkedList.enqueue(x);
        }

        for (char x : linkedList)
        {
            out.print(x);
            linkedList.dequeue();
        }

        out.println("\nDrawing test: ");

        for (char x : testArray)
        {
            linkedList.enqueue(x);
            out.println(linkedList.draw());
        }

        for (char x : linkedList)
        {
            out.println(linkedList.draw());
            linkedList.dequeue();
        }
    }
}