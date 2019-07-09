package com.imooc.data.structure.list.ArrayList;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author sunlife
 * @date 2019/4/12 16:04
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private AnyType[] theItems;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public AnyType get(int index) {
        if (index <0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[index];
    }

    public AnyType set(int index, AnyType newVal){
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType oldVal = theItems[index];
        theItems[index] = newVal;
        return oldVal;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;

        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }

    public void add(int index, AnyType newVal) {
        if (theItems.length == size())
            ensureCapacity(size() * 2 +1);
        for (int i = theSize;i > index ; i--)
            theItems[i] = theItems[i - 1];
        theItems[index] = newVal;

        theSize++;
    }
    public boolean add(AnyType newVal) {
        add(size(), newVal);
        return true;
    }

    public AnyType remove(int index) {
        AnyType removedItem = theItems[index];
        for (int i = 0 ; i < size() - 1 ; i++)
            theItems[i] = theItems[i + 1];
        theSize--;

        return removedItem;
    }

    @Override
    public Iterator<AnyType> iterator() {

        return new ArrayListIterator(this);
    }

    private class ArrayListIterator implements Iterator<AnyType> {
        private int current = 0;
        private MyArrayList<AnyType> list;

        public ArrayListIterator(MyArrayList<AnyType> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public AnyType next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return  list.theItems[current++];
        }

        @Override
        public void remove() {
            list.remove(--current);
        }

        public void addAll(Iterable<? extends AnyType> items) {
            Iterator<? extends AnyType> iterator = items.iterator();
            while (iterator.hasNext()) {
                add(iterator.next());
            }
        }
    }
}
