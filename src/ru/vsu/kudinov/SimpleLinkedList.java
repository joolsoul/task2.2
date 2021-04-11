package ru.vsu.kudinov;


import java.util.Iterator;

public class SimpleLinkedList<T extends Comparable<T>> implements Iterable<T>
{
    private static class ListNode<T>
    {
        public T value;
        public ListNode<T> next;

        public ListNode(T value, ListNode<T> next)
        {
            this.value = value;
            this.next = next;
        }
        public ListNode(T value)
        {
            this(value, null);
        }
    }

    private ListNode<T> head = null;
    private ListNode<T> tail = null;
    private int size = 0;

    public void addFirst(T value)
    {
        head = new ListNode<>(value, head);
        if (size == 0)
        {
            tail = head;
        }
        size++;
    }

    public void addLast(T value)
    {
        ListNode<T> newNode = new ListNode<>(value);
        if (size > 0)
        {
            tail.next = newNode;
        }
        else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    private void emptyListErrorCatcher() throws Exception
    {
        if (size == 0)
        {
            throw new Exception("List is empty");
        }
    }

    public T getFirst() throws Exception
    {
        emptyListErrorCatcher();
        return head.value;
    }

    public T getLast() throws Exception
    {
        emptyListErrorCatcher();
        return tail.value;
    }

    private ListNode<T> getNode(int index) throws Exception
    {
        if (index < 0 || index >= size)
        {
            throw new Exception("Wrong index");
        }
        ListNode<T> current = head;
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
        return current;
    }

    public T get(int index) throws Exception
    {
        return getNode(index).value;
    }

    public T removeFirst() throws Exception
    {
        T value = getFirst();
        head = head.next;
        size--;
        if (size == 0)
        {
            tail = null;
        }
        return value;
    }

    public T removeLast() throws Exception
    {
        T value = getLast();
        size--;
        if (size == 0)
        {
            head = tail = null;
        } else {
            tail = getNode(size - 1);
            tail.next = null;
        }
        return value;
    }

    public T remove(int index) throws Exception
    {
        if (index < 0 || index >= size)
        {
            throw new Exception("Wrong index");
        }

        T value;
        if (index == 0)
        {
            value = removeFirst();
        } else {
            ListNode<T> previous = getNode(index - 1);
            value = previous.next.value;
            previous.next = previous.next.next;
            if (previous.next == null)
            {
                tail = previous;
            }
        }
        size--;
        return value;
    }

    public void insert(int index, T value) throws Exception
    {
        if (index < 0 || index > size)
        {
            throw new Exception("Wrong index");
        }
        if (index == 0)
        {
            addFirst(value);
        } else {
            ListNode previous = getNode(index - 1);
            previous.next = new ListNode(value, previous.next);
            size++;
        }
    }

    public void clear()
    {
        head = tail = null;
        size = 0;
    }

    public int getSize()
    {
        return size;
    }


    @Override
    public Iterator<T> iterator()
    {
        class LinkedListIterator implements Iterator<T>
        {
            ListNode<T> curr;

            public LinkedListIterator(ListNode<T> head)
            {
                curr = head;
            }

            @Override
            public boolean hasNext()
            {
                return curr != null;
            }

            @Override
            public T next()
            {
                T result = curr.value;
                curr = curr.next;
                return result;
            }
        }
        return new LinkedListIterator(head);
    }
}
