package org.ggq.com.algorithm.avltree.entity;

/**
 * 叶子节点
 * @author ggq Date: 2019-01-10 Time: 14:22
 * @version $Id$
 */
public class AVLTreeNode<T extends Comparable<T>> {

    private T key;                // 关键字(键值)
    private int height;         // 高度
    private AVLTreeNode<T> left;    // 左孩子
    private AVLTreeNode<T> right;    // 右孩子

    public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.height = 0;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode<T> left) {
        this.left = left;
    }

    public AVLTreeNode<T> getRight() {
        return right;
    }

    public void setRight(AVLTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AVLTreeNode{");
        sb.append("key=").append(key);
        sb.append(", height=").append(height);
        sb.append(", left=").append(left);
        sb.append(", right=").append(right);
        sb.append('}');
        return sb.toString();
    }
}
