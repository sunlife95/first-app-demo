package com.imooc.data.structure.list.ArrayList;

import java.util.Arrays;
import java.util.List;

//有序数组
public class ArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;
    private static Object[] defaultElementaData = {};
    private static int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    //新建
    //constructor

    public ArrayList() {
        this.elementData = defaultElementaData;
    }

    public ArrayList(int size) {
        this.elementData = new Object[size];
    }

    public boolean add(E e) {
        //Judge is can add
        if (size + 1 > MAX_ARRAY_SIZE) {
            return false;
        }
        confirmEnouggCapacity(size + 1);
        elementData[size++] = e;
        return true;
    }

    private void confirmEnouggCapacity(int minCapacity) {
        if (this.elementData.length < minCapacity) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity > MAX_ARRAY_SIZE) {
            newCapacity = hugeCapacity(minCapacity);
        }
        this.elementData = Arrays.copyOf(this.elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public void reverse() {
        int middle = size / 2;

        for (int i = 0; i < middle; i++) {
            E temp = (E) this.elementData[this.size - 1 - i];
            this.elementData[this.size - 1 - i] = this.elementData[i];
            this.elementData[i] = temp;
        }
    }

    public boolean insert(int pos, int value) {
        if (pos > size) {
            return false;
        }
        confirmEnouggCapacity(size + 1);
        for (int i = size - 1; i >= pos - 1; i--) {
            this.elementData[i + 1] = this.elementData[i];
        }
        this.elementData[pos - 1] = value;
        size++;
        return true;
    }

    public boolean sortInsert(int value) {
        confirmEnouggCapacity(size + 1);
        if (value >= (int) this.elementData[size - 1]) {
            this.elementData[size++] = value;
            return true;
        }
        for (int i = 0; i < size; i++) {
            if (value > (int) this.elementData[i] && value <= (int) this.elementData[i + 1]) {
                insert(i + 2, value);
                break;
            }
        }
        return true;
    }

    public int distinct() {
        if (size == 0) {
            return 0;
        }
        int currPos = 0;
        for (int i = 1; i < size; i++) {
            if (this.elementData[i] != this.elementData[currPos]) {
                this.elementData[++currPos] = this.elementData[i];
            }
        }
        return currPos+1;
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
//        System.out.println("----------------------");
//        list.reverse();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        System.out.println("----------------------");
        list.sortInsert(4);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("----------------------");
        list.distinct();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
