import java.util.Iterator;
import static java.lang.System.out;

public class Lab1Question4<Item> implements Iterable<Item>  //question 4 circular double linked list
{  //circular linked list
    //structure:  null<--1<-->2<-->3-->null
    //where 1 is first 3 is last
    //now  4<-->1<-->2<-->3<-->4<-->1
    //where 1 is first 4 is last
    private Node first = null;  //first node
    private Node last = null;  //last node
    private int size = 0;  //counter for the size of list

    class Node
    {
        Item item = null;
        Node next = null;
        Node before = null;
    }

    public boolean isEmpty()  //checks, if first == null, true else false
    {
        return ((first == null) || (last == null));
    }

    @Override
    public Iterator<Item> iterator()
    {
        return (new Lab1Question4Iterator());
    }

    private class Lab1Question4Iterator implements Iterator<Item>
    {
        private Node pointer = first;
        private int counter = 0;

        @Override
        public boolean hasNext()  //checks if there is a next node
        {
            return ((pointer != first) && (counter != 0));
        }

        public void remove() {
        }

        @Override
        public Item next() {
            Item item = pointer.item;
            pointer = pointer.next;
            counter++;
            return (item);
        }
    }

    public String draw()
    {
        int counter = size;
        String drawing = "";
        Node pointer = first;
        StringBuilder sb = new StringBuilder();

        while (counter > 0)
        {
            sb.append('[').append(pointer.item).append(']');
            if((counter - 1) != 0)
            {
                sb.append(", ");
                //out.println("test 1");
            }
            else
            {
                counter = 1;
                //out.println("test 2");
            }

            counter--;
            pointer = pointer.next;
        }

        drawing = sb.toString();

        return (drawing);
    }

    public void addLast(Item item)
    {
        Node oldlast = last;  //adds the last recent node to oldlast
        last = new Node();  //makes new node for this data
        last.item = item;  //puts data into the node
        last.next = first;  //we point the next part of this node to null
        size++;

        if (isEmpty())  //if this is the first ie. if first == null
        {
            first = last;//put this node into as first
            last.before = last;
            //System.out.println("first = last");
        }
        else {
            last.before = oldlast;
            oldlast.next = last;  //points the previous entry to the new one added
            //System.out.println("next");
        }
    }

    public void addFirst(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.before = last;
        size++;

        if (isEmpty())
        {
            last = first;
            first.next = first;
        }
        else
        {
            first.next = oldfirst;
            oldfirst.before = first;
        }
    }

    public Item removeLast()
    {
        Item item = last.item;  //grabs item from the oldest entry

        if (last == first)
        {
            //out.println("last first");
            last = null;
            first = null;
            //System.out.println("last");
        }
        else
        {
            last = last.before;  //cycles it for the next call
            last.next = first;
            first.before = last;
        }

        size--;
        return (item);  //returns the data inside
    }

    public Item removeFirst()
    {
        Item item = first.item;

        if (first == last)
        {
            //out.println("first last");
            first = null;
            last = null;
        }

        else
        {
            first = first.next;
            first.before = last;
            last.next = first;
        }

        size--;
        return (item);
    }

    public static void main(String [] args)
    {
        char testArr[] = {'a', 'b', 'c', 'd'};
        Lab1Question4<Character> linkedList = new Lab1Question4<Character>();

        out.println("Add first, remove first test:");

        for (int x = 0; x < testArr.length; x++)
        {
            out.printf("adding %d \n", x);
            linkedList.addFirst(testArr[x]);
        }

        for (int x = 0; x < testArr.length; x++)
        {
            out.printf("removing %d \n", x);
            out.print(linkedList.removeFirst());
            out.println();
        }

        out.println("Add last, remove last test:");

        for (int x = 0; x < testArr.length; x++)
        {
            out.printf("adding %d \n", x);
            linkedList.addLast(testArr[x]);
        }

        for (int x = 0; x < testArr.length; x++)
        {
            out.printf("removing %d \n", x);
            out.print(linkedList.removeLast());
            out.println();
        }

        out.println("Add first, add last, remove first, remove last test:");

        for (int x = 0; x < testArr.length; x++)
        {
            out.printf("adding %d \n", x);
            if ((x % 2) == 0)
            {
                linkedList.addFirst(testArr[x]);
            }
            else
            {
                linkedList.addLast(testArr[x]);
            }
        }

        for (int x = 0; x < testArr.length; x++)
        {
            out.printf("removing %d \n", x);
            if ((x % 2) == 0)
            {
                char c = linkedList.removeLast();
                out.print(c);
                out.println();
            }
            else
            {
                char c = linkedList.removeFirst();
                out.print(c);
                out.println();
            }
        }

        out.println("Draw test");

        for (int x = 0; x < testArr.length; x++)
        {
            linkedList.addFirst(testArr[x]);
            out.println(linkedList.draw());
        }

        for (int x = 0; x < testArr.length; x++)
        {
            linkedList.removeLast();
            out.println(linkedList.draw());
        }

        out.println("Iterator test: ");

        for (char x : testArr)
        {
            linkedList.addFirst(x);
        }

        for (char x : linkedList)
        {
            out.print(x);
            linkedList.removeFirst();
        }

    }
}