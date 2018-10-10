package com.imooc.data.structure.list.linkedList;

public class LinkedList<T> {

    private Node<T> first;
    private Node<T> last;

    private int size;

    public int size(){
        return this.size;
    }

    public void linkLast(T t){
        Node<T> l = last;
        Node<T> p = new Node<>(t,null);
        last = p;
        if (this.size==0){
            first = p ;
        }else {
            l.next = p;
        }
        size++;
    }


}
class Node<T>{
    T obj;
    Node<T> next;

    public Node(T obj, Node<T> next) {
        this.obj = obj;
        this.next = next;
    }

}
