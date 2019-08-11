package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.BinaryTreeParser;
import org.sansilab.leetcode.structure.TreeNode;

public class BinaryTreeMaximumPathSum extends AbstractProblem {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);

        return max;
    }

    public int dfs(TreeNode root){
        if (root == null) {
            return 0;
        }

        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);

        int val = root.val + Math.max(left, right);
        max = Math.max(root.val + left + right, max);
        return val;
    }

    @Override
    void init() {
        parserList.add(new BinaryTreeParser());
        addInput("[-3]");
        outputList.add(-3);

        addInput("[1,2,3]");
        outputList.add(6);

        addInput("[-10,9,20,null,null,15,7]");
        outputList.add(42);

        addInput("[10,9,20,null,null,15,7]");
        outputList.add(54);
    }

    @Override
    Object execute(Object[] input) {
        return maxPathSum((TreeNode) input[0]);
    }

    public static void main(String[] args) {
        new BinaryTreeMaximumPathSum().runTest();
    }
}
