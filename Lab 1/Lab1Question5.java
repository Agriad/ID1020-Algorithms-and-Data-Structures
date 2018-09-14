import java.util.Iterator;
import static java.lang.System.out;

/*
README
This is an implementation of a generic iterable circular double linked list where the data can be loaded from the start
of the list but can be unloaded from any position.
It has the structure:
4<-->1<-->2<-->3<-->4<-->1
Where 1 is first 4 is last.
The structure of the linked list is heavily inspired from the Algorithms 4th ed
*/

public class Lab1Question5<Item> implements Iterable<Item>
{
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
        return (new Lab1Question5Iterator());
    }

    private class Lab1Question5Iterator implements Iterator<Item>
    {
        private Node pointer = first;
        private int counter = 0;

        @Override
        public boolean hasNext()  //checks if there is a next node
        {
            return (counter < size);
        }

        public void remove() {
        }

        @Override
        public Item next() {  //gives item and goes to the next node
            Item item = pointer.item;
            pointer = pointer.next;
            counter++;
            return (item);
        }
    }

    public String draw()  //makes a String interpretation of the way the data is stored
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

    public void enqueue(Item item)  //adds the item to the "first" node and links it
    {
        Node oldfirst = first;  //priming new node for the new item
        first = new Node();
        first.item = item;
        first.before = last;
        size++;

        if (isEmpty())  //if the first addition then point both ways to self
        {
            last = first;
            first.next = first;
        }
        else  //points the 2 nodes to each other
        {
            first.next = oldfirst;
            oldfirst.before = first;
        }
    }

    public Item remove(int position)  //removes the node from the "nth" node and returns the item
    {
        Item item = null;
        Node pointer = first;

        for (int x = 1; x < position; x++)  //iterates to the nth position
        {
            pointer = pointer.next;
        }

        item = pointer.item;

        if (position == 1)  //moves the first and last so that it works for the next call
        {
            first = pointer.next;
        }
        else if(position == size)
        {
            last = pointer.before;
        }

        if (size == 1)  //if the only node remove the pointers
        {
            pointer.next = null;
            pointer.before = null;
            first = null;
            last = null;
        }
        else  //removes the pointers from and to other nodes
        {
            pointer.before.next = pointer.next;
            pointer.next.before = pointer.before;
            pointer.before = null;
            pointer.next = null;
        }

        size--;
        return (item);  //returns the data inside
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

        out.println("Draw test: ");

        for (int x = 0; x < testArr.length; x++)
        {
            linkedList.enqueue(testArr[x]);
            out.println(linkedList.draw());
        }

        for (int x = 0; x < testArr.length; x++)
        {
            linkedList.remove(1);
            out.println(linkedList.draw());
        }

        out.println("Iterator test: ");

        for (int x = 0; x < testArr.length; x++)
        {
            linkedList.enqueue(testArr[x]);
        }

        for (char x : linkedList)
        {
            out.print(x);
        }

    }
}
