package com.mall.gateway;

import lombok.Data;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/25
 */
public class TreeTest {

    public static void main(String[] args) {
        /*
         *    1
         *  2   3
         * 4 5 6 7
         */
        Tree root = new Tree(1);
        root.setLeft(new Tree(2));
        root.setRight(new Tree(3));
        root.getLeft().setLeft(new Tree(4));
        root.getLeft().setRight(new Tree(5));
        root.getRight().setLeft(new Tree(6));
        root.getRight().setRight(new Tree(7));

        traverse(root);
    }

    private static void traverse(Tree tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree);
        traverse(tree.getLeft());
        traverse(tree.getRight());
    }

    @Data
    static class Tree {
        private Integer value;
        private Tree left;
        private Tree right;

        Tree(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Tree [value = " + value + "]";
        }
    }

}
