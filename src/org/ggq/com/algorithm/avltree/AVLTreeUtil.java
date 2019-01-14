package org.ggq.com.algorithm.avltree;

import java.util.Objects;
import org.ggq.com.algorithm.avltree.entity.AVLTree;
import org.ggq.com.algorithm.avltree.entity.AVLTreeNode;

/**
 * AVL 平衡二叉树
 * AVL树中任何节点的两个子树的高度最大差别为1
 * @author ggq Date: 2019-01-09 Time: 20:54
 * @version $Id$
 */
public class AVLTreeUtil {

    /**
     * 获取树的高度
     * 
     * @param tree 树 指向跟节点
     * @return 树高
     */
    public int height(AVLTreeNode tree) {
        if (tree != null)
            return tree.getHeight();
        return 0;
    }

    /**
     * 比较两个值(高度)大小获取最大高度
     * 
     * @param one 高度一
     * @param two 高度二
     * @return 获得最大高度
     */
    private int max(int one, int two) {
        return one > two ? one : two; 
    }

    /**
     * AVL 树涉及到的旋转 LL LR RL RR
     */

    /**
     * LL：左左对应的情况(左单旋转)。
     *
     * @param oldRoot 当前根节点
     * @return 新的根节点
     */
    private AVLTreeNode leftLeftRotation(AVLTreeNode oldRoot) {
        AVLTreeNode newRoot;

        newRoot = oldRoot.getLeft();
        oldRoot.setLeft(newRoot.getRight());
        newRoot.setRight(oldRoot);

        oldRoot.setHeight(max(height(oldRoot.getLeft()), height(oldRoot.getRight())) + 1);
        newRoot.setHeight(max(height(newRoot.getLeft()), oldRoot.getHeight()) + 1);

        return newRoot;
    }


    /**
     * RR：右右对应的情况(右单旋转)。
     *
     * @param oldRoot 原根节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode rightRightRotation(AVLTreeNode oldRoot) {
        AVLTreeNode newRoot;

        newRoot = oldRoot.getRight();
        oldRoot.setRight(newRoot.getLeft());
        newRoot.setLeft(oldRoot);

        oldRoot.setHeight(max(height(oldRoot.getLeft()), height(oldRoot.getRight())) + 1);
        newRoot.setHeight(max(height(newRoot.getRight()), oldRoot.getHeight()) + 1);

        return newRoot;
    }


    /**
     * LR：左右对应的情况(左双旋转)。
     *
     * @param oldRoot 老的根节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode leftRightRotation(AVLTreeNode oldRoot) {
        oldRoot.setLeft(rightRightRotation(oldRoot.getLeft()));
        return leftLeftRotation(oldRoot);
    }

    /**
     * RL：右左对应的情况(右双旋转)。
     *
     * @param oldRoot 老的根节点
     * @return 旋转后的根节点
     */
    private AVLTreeNode rightLeftRotation(AVLTreeNode oldRoot) {
        
        oldRoot.setRight(leftLeftRotation(oldRoot.getRight()));

        return rightRightRotation(oldRoot);
    }


    /**
     * 将结点插入到AVL树中，并返回根节点
     *
     * 参数说明：
     *  @param tree AVL树的根结点
     *  @param key 插入的结点的键值
     *  @return 根节点
     */
    public <T extends Comparable<T>> AVLTreeNode<T> insertNode(AVLTreeNode<T> tree, T key) {
        if (Objects.isNull(tree)) {
            // 新建节点
            tree = new AVLTreeNode<T>(key, null, null);
        } else {
            int cmp = key.compareTo(tree.getKey());

            if (cmp < 0) {
                // 应该将key插入到"tree的左子树"的情况
                tree.setLeft(insertNode(tree.getLeft(), key));
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(tree.getLeft()) - height(tree.getRight()) == 2) {
                    if (key.compareTo(tree.getLeft().getKey()) < 0) {
                        tree = leftLeftRotation(tree);
                    } else {
                        tree = leftRightRotation(tree);
                    }
                }
            } else if (cmp > 0) {
                // 应该将key插入到"tree的右子树"的情况
                tree.setRight(insertNode(tree.getRight(), key));
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(tree.getRight()) - height(tree.getLeft()) == 2) {
                    if (key.compareTo(tree.getRight().getKey()) > 0) {
                        tree = rightRightRotation(tree);
                    } else {
                        tree = rightLeftRotation(tree);
                    }
                }
            } else {
                // cmp==0  重复更新
                tree.setKey(key);
            }
        }

        tree.setHeight(max(height(tree.getLeft()), height(tree.getRight())) + 1);

        return tree;
    }


    /**
     * 删除结点(z)，返回根节点
     *
     * 参数说明：
     *     tree AVL树的根结点
     *     z 待删除的结点
     * 返回值：
     *     根节点
     */
    private <T extends Comparable<T>> AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (tree==null || z==null)
            return null;

        int cmp = z.getKey().compareTo(tree.getKey());
        if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
            tree.setLeft(remove(tree.getLeft(), z));
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.getRight()) - height(tree.getLeft()) == 2) {
                AVLTreeNode<T> r =  tree.getRight();
                if (height(r.getLeft()) > height(r.getRight()))
                    tree = rightLeftRotation(tree);
                else
                    tree = rightRightRotation(tree);
            }
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            tree.setRight(remove(tree.getRight(), z));
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.getLeft()) - height(tree.getRight()) == 2) {
                AVLTreeNode<T> leftNode =  tree.getLeft();
                if (height(leftNode.getRight()) > height(leftNode.getLeft()))
                    tree = leftRightRotation(tree);
                else
                    tree = leftLeftRotation(tree);
            }
        } else {    
            // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((tree.getLeft()!=null) && (tree.getRight()!=null)) {
                if (height(tree.getLeft()) > height(tree.getRight())) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> max = maximum(tree.getLeft());
                    tree.setKey(max.getKey());
                    tree.setLeft(remove(tree.getLeft(), max));
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> min = minimum(tree.getRight());
                    tree.setKey(min.getKey());
                    tree.setRight(remove(tree.getRight(), min));
                }
            } else {
                AVLTreeNode<T> tmp = tree;
                tree = (tree.getLeft()!=null) ? tree.getLeft() : tree.getRight();
                tmp = null;
            }
        }

        return tree;
    }

    public <T extends Comparable<T>> void removeNode(AVLTreeNode<T> root, T key) {
        AVLTreeNode<T> z;

        if ((z = search(root, key)) != null)
            root = remove(root, z);
    }


    /*
     * 销毁AVL树
     */
    private void destroy(AVLTreeNode tree) {
        if (tree==null)
            return ;

        if (tree.getLeft() != null)
            destroy(tree.getLeft());
        if (tree.getRight() != null)
            destroy(tree.getRight());

        tree = null;
    }


    /*
     * 打印"二叉查找树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    public <T extends Comparable<T>> void print(AVLTreeNode<T> tree, T key, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.getKey(), key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.getKey(), key, direction==1?"right" : "left");

            print(tree.getLeft(), tree.getKey(), -1);
            print(tree.getRight(), tree.getKey(),  1);
        }
    }

    /**
     * 查找最小结点：返回tree为根结点的AVL树的最小结点。
     */
    public  <T extends Comparable<T>> AVLTreeNode<T> minimum(AVLTreeNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.getLeft() != null)
            tree = tree.getLeft();
        return tree;
    }

    /**
     * 查找最大结点：返回tree为根结点的AVL树的最大结点。
     */
    public <T extends Comparable<T>> AVLTreeNode<T> maximum(AVLTreeNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.getRight() != null)
            tree = tree.getRight();
        return tree;
    }

    /*
     * (递归实现)查找"AVL树x"中键值为key的节点
     */
    public <T extends Comparable<T>> AVLTreeNode<T> search(AVLTreeNode<T> x, T key) {
        if (x==null)
            return x;
        int cmp = key.compareTo(x.getKey());
        if (cmp < 0)
            return search(x.getLeft(), key);
        else if (cmp > 0)
            return search(x.getRight(), key);
        else
            return x;
    }

    /*
     * (非递归实现)查找"AVL树x"中键值为key的节点
     */
    private <T extends Comparable<T>> AVLTreeNode<T> iterativeSearch(AVLTreeNode<T> x, T key) {
        while (x!=null) {
            int cmp = key.compareTo(x.getKey());

            if (cmp < 0)
                x = x.getLeft();
            else if (cmp > 0)
                x = x.getRight();
            else
                return x;
        }

        return x;
    }
    
    /*
     * 前序遍历"AVL树"
     */
    public void preOrder(AVLTreeNode tree) {
        if(tree != null) {
            System.out.print(tree.getKey()+" ");
            preOrder(tree.getLeft());
            preOrder(tree.getRight());
        }
    }


    /**
     * 中序遍历"AVL树"
     */
    public void inOrder(AVLTreeNode tree) {
        if(tree != null)
        {
            inOrder(tree.getLeft());
            System.out.print(tree.getKey()+" ");
            inOrder(tree.getRight());
        }
    }

    /**
     * 后序遍历"AVL树"
     */
    public void postOrder(AVLTreeNode tree) {
        if(tree != null) {
            postOrder(tree.getLeft());
            postOrder(tree.getRight());
            System.out.print(tree.getKey()+" ");
        }
    }

}
