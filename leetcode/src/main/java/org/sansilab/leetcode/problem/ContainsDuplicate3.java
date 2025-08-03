package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.BooleanParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @ClassName：ContainsDuplicate3
 * @Description：#220  https://leetcode.cn/problems/contains-duplicate-iii/description/?difficulty=HARD&page=3
 * @Author： TEMU
 * @Date 2025/8/3 10:26
 * @Copyright: Copyright (c) 2022 - 2025 Temu.
 */
public class ContainsDuplicate3 extends AbstractProblem {
    public static void main(String[] args) {
        new ContainsDuplicate3().runTest();
    }

    @Answer
    public boolean solve(int[] nums, int indexDiff, int valueDiff) {
        for (int i = 0; i < nums.length - indexDiff; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = i; j <= i + indexDiff; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
            }
            if (max - min <= valueDiff) {
                return true;
            }
        }
        return false;
    }

    @Override
    void init() {
        addInputParsers(new ArrayParser(new IntParser()));
        addInputParsers(new IntParser());
        addInputParsers(new IntParser());
        setOutputParser(new BooleanParser());

        addInput("[1,2,3,1]", "3", "0");
        addOutput("true");

        addInput("[1,5,9,1,5,9]", "2", "3");
        addOutput("false");
    }
}
