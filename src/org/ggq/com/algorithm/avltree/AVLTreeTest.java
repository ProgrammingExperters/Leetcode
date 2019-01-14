package org.ggq.com.algorithm.avltree;

import org.ggq.com.algorithm.avltree.entity.AVLTree;

/**
 * AVL 树测试
 * @author ggq Date: 2019-01-10 Time: 17:46
 * @version $Id$
 */
public class AVLTreeTest {

    private static int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};

    public static void main(String[] args) {
        int i;
        AVLTree<Integer> tree = new AVLTree<Integer>();
        AVLTreeUtil treeUtil = new AVLTreeUtil();
        
        System.out.printf("== 依次添加: ");
        for(i=0; i<arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            tree.setmRoot(treeUtil.insertNode(tree.getmRoot(), arr[i]));
        }

        System.out.println(tree);

        System.out.printf("\n== 前序遍历: ");
        treeUtil.preOrder(tree.getmRoot());

        System.out.printf("\n== 中序遍历: ");
        treeUtil.inOrder(tree.getmRoot());

        System.out.printf("\n== 后序遍历: ");
        treeUtil.postOrder(tree.getmRoot());
        System.out.printf("\n");


        System.out.printf("== 高度: %d\n", tree.getmRoot().getHeight());
//        System.out.printf("== 最小值: %d\n", treeUtil.minimum(tree.getmRoot()));
//        System.out.printf("== 最大值: %d\n", treeUtil.maximum(tree.getmRoot()));
//        System.out.printf("== 树的详细信息: \n");
//        treeUtil.print(tree.getmRoot(), tree.getmRoot().getKey(), 0);
    }
}
