import java.util.Iterator;
import static java.lang.System.out;

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

    public void enqueue(Item item)
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

    public Item remove(int position)
    {
        Item item = null;
        Node pointer = first;

        for (int x = 1; x < position; x++)
        {
            pointer = pointer.next;
        }

        item = pointer.item;

        if (position == 1)
        {
            first = pointer.next;
        }
        else if(position == size)
        {
            last = pointer.before;
        }

        if (size == 1)
        {
            pointer.next = null;
            pointer.before = null;
        }
        else
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
