package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.*;

// https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/
public class SmallestRangeCoveringElementsFromKLists extends AbstractProblem{
	public static void main(String[] args) {
		new SmallestRangeCoveringElementsFromKLists().runTest();
	}
	@Answer
	public int[] smallestRange(List<List<Integer>> nums) {
		PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparing(a -> nums.get(a[0]).get(a[1])));
		int[] range = new int[]{-100000, 100000};
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.size(); i++) {
			List<Integer> list = nums.get(i);
			int val = list.get(0);
			q.add(new int[]{i, 0});
			max = Math.max(max, val);
		}
		int min = q.peek()[1];
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			List<Integer> list = nums.get(cur[0]);
			min = list.get(cur[1]);
			int[] newRange = new int[]{min, max};
			if (newRange[1] - newRange[0] < range[1] - range[0]) {
				range = newRange;
			}
			if (list.size() == cur[1] + 1) {
				break;
			}
			int[] next = new int[]{cur[0], cur[1] + 1};
			q.add(next);
			int nextVal = list.get(next[1]);
			max = Math.max(max, nextVal);
		}
		return range;
	}



	@Override
	void init() {
		addInputParsers(
				new TwoDimensionListParser(new IntParser())
		);
		setOutputParser(new ArrayParser(new IntParser()));

		addInput("[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]");
		addOutput("[20,24]");

		addInput("[[1,2,3],[1,2,3],[1,2,3]]");
		addOutput("[1,1]");
	}
}

