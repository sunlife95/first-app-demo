package com.imooc.data.structure.list.train3_1;

/**
 * @author sunlife
 * @date 2019/4/22 11:54
 */
public class SingleList<AnyType> {
    private int size;
    private Node<AnyType> head;

    public SingleList(Node<AnyType> head) {
        this.head = new Node<>(null,null);
    }

    public int size() {
        return size;
    }

    public void print() {
        Node<AnyType> node = head;
        if (size() > 0) {
            while (head.next != null) {
                node = node.next;
                System.out.println(node.data);
            }
        }
    }

    public boolean contains(AnyType x) {
        Node<AnyType> node = head;
        if (size() > 0) {
            while (head.next != null) {
                node = node.next;
                if (node.data.equals(x)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void add(AnyType x) {
        Node<AnyType> node = head;
        if (size() > 0) {
            while (head.next != null) {
                node = node.next;
            }
        }
        Node<AnyType> newNode = new Node<>(x, null);
        node.next = newNode;
        size++;
    }

    public void noContainsAndAdd(AnyType x) {
        if (!contains(x))
            add(x);
    }

    class Node<AnyType> {
        private AnyType data;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }
}
