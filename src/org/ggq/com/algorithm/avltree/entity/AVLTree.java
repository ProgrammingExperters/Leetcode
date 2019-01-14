package org.ggq.com.algorithm.avltree.entity;

/**
 * 分布式 系统不能使用单例模式 -> 多台服务器里面的数据不同
 * AVL 树对象 树 重复更新
 * @author guoqingg_1 Date: 2019-01-10 Time: 14:23
 * @version $Id$
 */
public class AVLTree<T extends Comparable<T>> {
    
    private AVLTreeNode<T> mRoot;    // 根结点

    public AVLTreeNode<T> getmRoot() {
        return mRoot;
    }

    public void setmRoot(AVLTreeNode<T> mRoot) {
        this.mRoot = mRoot;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AVLTree{");
        sb.append("mRoot=").append(mRoot);
        sb.append('}');
        return sb.toString();
    }
    
}
