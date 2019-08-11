package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Combinations extends AbstractProblem {
    public List<List<Integer>> combine(int n, int k) {
        if (n<k || k==0) {
            return new ArrayList<>();
        }
        List<List<Integer>> ret = new LinkedList();
        recursive(n, k, 1, new ArrayList<Integer>(), ret);
        return ret;
    }

    void recursive(int n, int k, int s, List<Integer> pre, List<List<Integer>> ret) {
        if (k == 0) {
            ret.add(pre);
        } else {
            int len = pre.size()+k;
            for (int i = s; i <= n-k+1; i++) {
                List<Integer> l = new ArrayList<>(len);
                l.addAll(pre);
                l.add(i);
                recursive(n, k-1, i+1, l, ret);
            }
        }
    }

    @Override
    void init() {
        parserList.add(new IntParser());
        parserList.add(new IntParser());
        setOutputParser(new TwoDimensionListParser(new IntParser()));

        addInput("4", "2");
        addOutput("[[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]");

        addInput("5", "3");
        addOutput("[[1,2,3],[1,2,4],[1,2,5],[1,3,4],[1,3,5],[1,4,5],[2,3,4],[2,3,5],[2,4,5],[3,4,5]]");

        addInput("5", "2");
        addOutput("[[1,2],[1,3],[1,4],[1,5],[2,3],[2,4],[2,5],[3,4],[3,5],[4,5]]");
    }

    @Override
    Object execute(Object[] input) {
        return combine((int)input[0], (Integer) input[1]);
    }

    public static void main(String[] args) {
        new Combinations().runTest();
    }
}
