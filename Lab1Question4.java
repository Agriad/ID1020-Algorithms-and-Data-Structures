import java.util.Iterator;

public class Lab1Question4<Item> implements Iterable<Item>  //question 4
{  //circular linked list
    //structure:  1-->2-->3-->null
    //where 1 is the first data entered
    private Node first = null;  //oldest node
    private Node last = null;  //newest node

    class Node
    {
        Item item = null;
        Node next = null;
    }

    public boolean isEmpty()  //checks, if first == null, true else false
    {
        return (first == null);
    }

    @Override
    public Iterator<Item> iterator()
    {
        return (new Lab1Question4Iterator());
    }

    private class Lab1Question4Iterator implements Iterator<Item>
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
        public Item next() {  //TODO careful
            Item item = pointer.item;
            pointer = pointer.next;
            return (item);
        }
    }
    public void addLast(Item item)
    {
        Node oldlast = last;  //adds the last recent node to oldlast
        last = new Node();  //makes new node for this data
        last.item = item;  //puts data into the node
        last.next = first;  //we point the next part of this node to null

        if (isEmpty())  //if this is the first ie. if first == null
        {
            first = last;  //put this node into as first
            //System.out.println("first = last");
        }
        else {
            oldlast.next = last;  //points the previous entry to the new one added
            //System.out.println("next");
        }
    }

    public void addFirst(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        last.next = first;

        if (isEmpty())
        {
            first = last;
        }
        else
        {

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
}
