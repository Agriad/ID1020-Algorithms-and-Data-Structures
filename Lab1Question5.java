import java.util.Iterator;
import static java.lang.System.out;

public class Lab1Question5<Item> implements Iterable<Item>
{
    //structure:  null<--1<-->2<-->3-->null
    //where 3 is the first data entered
    private Node first = null;  //newest node
    private Node last = null;  //oldest node
    private int size = 0;

    private class Node
    {
        Item item = null;  //data storage
        Node next = null;  //pointer to the next node
        Node before = null;  //pointer to the previous node
    }

    public boolean isEmpty()  //checks, if first == null, true else false
    {
        return (last == null);
    }

    @Override
    public Iterator<Item> iterator()
    {
        return (new Lab1Question5Iterator());
    }

    private class Lab1Question5Iterator implements Iterator<Item>
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
        Node oldfirst = first;
        first = new Node();
        first.item = item;  //puts data into the node
        first.before = null;  //we point the next part of this node to null

        if (isEmpty())  //if this is the first ie. if first == null
        {
            first.next = null;  //
            last = first;  //
        }
        else {
            first.next = oldfirst;  //
            oldfirst.before = first;  //
        }
        size++;
    }

    public Item remove(int position)
    {
        Node oldfirst = first;
        Node oldFirstNext = null;
        Node oldFirstBefore = null;
        Item item = null;

        if (size == 1)
        {
            item = first.item;
            last = null;
            first = null;
            oldfirst = null;
        }
        else
        {
            for (int x = 0; x < position; x++)
            {
                first = first.next;
            }
            //might need to use x.next.next
            item = first.item;
            oldFirstBefore = first.before;
            oldFirstNext = first.next;
            oldFirstNext.next = first.next;
            oldFirstBefore.before = first.before;
            first.next = null;
            first.before = null;
            first = oldfirst;
        }

        size--;
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

    public static void main(String [] args)
    {
        char testArr[] = {'a', 'b', 'c', 'd'};
        Lab1Question5<Character> linkedList = new Lab1Question5<Character>();
        out.println("Add, remove test: ");

        for (int x = 0; x < testArr.length; x++)
        {
            out.printf("adding %d \n", x);
            linkedList.enqueue(testArr[x]);
        }

        //out.println(linkedList.remove(2));
        //out.println(linkedList.remove(2));


        for (int x = 0; x < testArr.length; x++)
        {
            if (x < 2)
            {
                out.println(linkedList.remove(2));
            }
            else
            {
                out.println(linkedList.remove(1));
            }
        }
    }
}
