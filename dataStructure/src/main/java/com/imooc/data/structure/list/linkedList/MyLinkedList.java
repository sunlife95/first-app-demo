package com.imooc.data.structure.list.linkedList;

import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author sunlife
 * @date 2019/4/15 17:45
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public AnyType get(int index) {
        return getNode(index).data;
    }

    public Node<AnyType> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    public Node<AnyType> getNode(int index, int lower, int upper) {
        Node<AnyType> p;

        if (index < lower || index > upper)
            throw new IndexOutOfBoundsException();

        if (index < size() / 2) {
            p = beginMarker;
            for (int i = 0; i < index; i++)
                p = p.next;
        } else {
            p = endMarker;
            for (int i = size(); i > index; i--)
                p = p.prev;
        }
        return p;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public boolean add(int index, AnyType x) {
        addBefore(getNode(index, 0, size()), x);
        return true;
    }

    public void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;

        theSize++;
        modCount++;
    }

    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    public AnyType remove(int index) {
        return remove(getNode(index));
    }

    public AnyType set(int index, AnyType x) {
        Node<AnyType> p = getNode(index);
        AnyType oldVal = p.data;
        p.data = x;
        return oldVal;
    }

    public void removeAll(Iterable<? extends AnyType> items) {
        AnyType item,element;
        Iterator<? extends AnyType> iterItems = items.iterator();

        while (iterItems.hasNext()){
            item = iterItems.next();
            Iterator<? extends AnyType> iterList = iterator();
            while ( iterList.hasNext()){
                element = iterList.next();
                if (element.equals(item))
                    iterList.remove();
            }
        }
    }


    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    @NotNull
    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }

        public boolean contains(AnyType x) {
            Node<AnyType> p = beginMarker.next;
            while (p != endMarker && !p.data.equals(x)) {
                p = p.next;
            }
            return p != endMarker;
        }
    }
}
