package com.imooc.data.structure.tree.binarytree;

import java.nio.BufferUnderflowException;
import java.util.Comparator;

/**
 * @author sunlife
 * @date 2019/7/9 16:45
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType>{
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;
    private Comparator<? super AnyType> cmp;

    public BinarySearchTree(){
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private int myCompare( AnyType lhs, AnyType rhs ) {
        if ( cmp != null ) {
            return cmp.compare( lhs, rhs );
        } else {
            return lhs.compareTo(rhs);
        }
    }

    private boolean contains(AnyType x,BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    public AnyType findMin() {
        if ( isEmpty() )
            throw new BufferUnderflowException();
        return findMin(root).element;
    }
    public AnyType findMax() {
        if ( isEmpty() )
            throw new BufferUnderflowException();
        return findMax(root).element;
    }
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t) {
        if ( t == null ) {
            return null;
        }
        if ( t.left == null ) {
            return t;
        }
        return findMin(t.left);
    }
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t) {
        if ( t != null ) {
            if ( t.right != null ) {
                t = t.right;
            }
        }
        return t;
    }

    public void insert( AnyType x ) {
        root = insert( x, root );
    }

    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t ) {
        if ( t == null )
            return new BinaryNode<>( x, null, null );
        int compareResult = x.compareTo( t.element );

        if ( compareResult < 0 ) {
            t.left = insert( x, t.left );
        } else if ( compareResult > 0 ) {
            t.right = insert( x, t.right );
        } else {
            ;
        }
        return t;
    }

    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t ) {
        if ( t == null ) {
            return t;
        }

        int compareResult = x.compareTo( t.element );

        if ( compareResult < 0 ) {
            // 小于当前，则从左分支往下找
            t.left = remove( x, t.left);
        } else if ( compareResult > 0 ) {
            // 小于当前，则从右分支往下找
            t.right = remove( x, t.right );
        } else if ( t.left != null && t.right != null ) {
            // 相等，并且有左右两个子节点，将当前节点的值修改为右子树的最小数据，并删除右子树的最小数据
            t.element = findMin(t.right).element;
            t.right = remove( t.element, t.right);
        } else {
            // 相等，只有一个子节点，返回子节点，上层递归会将该子节点作为其子节点，跳过要删除的元素
            t = (t.left != null) ? t.left : t.right;
        }
        return t;

    }

}
