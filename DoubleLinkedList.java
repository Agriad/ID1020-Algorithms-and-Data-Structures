public class DoubleLinkedList<Item>
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
        return(first == null);
    }

    public boolean hasNext()  //checks if there is a next node
    {
        return(!(first == null));
    }

    public Node next()  //returns the next node in the queue
    {
        if(hasNext())
        {
            return(last.next);
        }
        else
        {
            return(null);
        }
    }

    public void enqueue(Item item)
    {
        last.before = last;  //points the new node to the older node backwards
        Node oldlast = last;  //adds the last recent node to oldlast
        last = new Node();  //makes new node for this data
        last.item = item;  //puts data into the node
        last.next = null;  //we point the next part of this node to null

        if(isEmpty())  //if this is the first ie. if first == null
        {
            last.before = null;  //if the first data entry points backwards to null
            first = last;  //put this node into as first
            //System.out.println("first = last");
        }
        else
        {
            oldlast.next = last;  //points the previous entry to the new one added
            //System.out.println("next");
        }
    }

    public Item dequeue()
    {
        Item item = first.item;  //grabs item from the oldest entry
        first = first.next;  //cycles it for the next call

        if(isEmpty())  //if at the end null
        {
            last = null;
            //System.out.println("last");
        }

        return(item);  //returns the data inside
    }
}
