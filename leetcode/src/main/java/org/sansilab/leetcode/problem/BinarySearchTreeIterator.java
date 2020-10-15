package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.BinaryTreeParser;
import org.sansilab.leetcode.structure.TreeNode;

import java.util.Stack;

public class BinarySearchTreeIterator {

    public static void main(String[] args) {

        TreeNode root = (TreeNode) new BinaryTreeParser().parse("[7,3,15,null,null,9,20]");
        BSTIterator iterator = new BSTIterator(root);

        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }
}

class BSTIterator {

    private Stack<TreeNode> path;
    private TreeNode cur;

    public BSTIterator(TreeNode root) {
        path = new Stack<>();
        if (root == null) {
            return;
        }
        cur = root;
        while(cur.left != null) {
            path.push(cur);
            cur = cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        int ret = cur.val;
        if (cur.right != null) {
            cur = cur.right;
            while(cur.left != null) {
                path.push(cur);
                cur = cur.left;
            }
        } else {
            if (path.isEmpty()) {
                cur = null;
            } else {
                TreeNode p = path.pop();
                while (p.val < cur.val) {
                    p = path.pop();
                }
                cur = p;
            }
        }
        return ret;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur!=null;
    }
}
