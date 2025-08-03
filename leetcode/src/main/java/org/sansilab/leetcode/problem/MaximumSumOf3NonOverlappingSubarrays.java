package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/
public class MaximumSumOf3NonOverlappingSubarrays extends AbstractProblem{
	public static void main(String[] args) {
		new MaximumSumOf3NonOverlappingSubarrays().runTest();
	}

	@Answer
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int[] sums = new int[nums.length - k + 1];
		for (int i = 0; i < nums.length; i++) {
			for (int j = Math.min(i, sums.length-1);j >= 0 && j > i - k; j--) {
				sums[j] += nums[i];
			}
		}
		int[] dp1 = new int[sums.length];
		dp1[0] = 0;
		for (int i = 1; i < sums.length; i++) {
			if (sums[dp1[i-1]] >= sums[i]) {
				dp1[i] = dp1[i - 1];
			} else {
				dp1[i] = i;
			}
		}
		int[][] dp2 = new int[sums.length][2];
		dp2[k] = new int[]{0, k};
		for (int i = k + 1; i < sums.length; i++) {
			int lastSum = sums[dp2[i - 1][0]] + sums[dp2[i-1][1]];
			int newSum = sums[dp1[i-k]] + sums[i];
			if (lastSum >= newSum) {
				dp2[i] = dp2[i-1];
			} else {
				dp2[i] = new int[]{dp1[i-k], i};
			}
		}
		int[][] dp3 = new int[sums.length][3];
		dp3[k*2] = new int[]{0, k, k*2};
		for (int i = k*2 + 1; i < sums.length; i++) {
			int lastSum = sums[dp3[i - 1][0]] + sums[dp3[i-1][1]] + sums[dp3[i-1][2]];
			int newSum = sums[dp2[i-k][0]] + sums[dp2[i-k][1]] + sums[i];
			if (lastSum >= newSum) {
				dp3[i] = dp3[i-1];
			} else {
				dp3[i] = new int[]{dp2[i-k][0], dp2[i-k][1], i};
			}
		}
		return dp3[sums.length-1];
	}

	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
				,new IntParser()
		);
		setOutputParser(new ArrayParser(new IntParser()));

		addInput("[1,2,1,2,1,2,1,2,1]", "2");
		addOutput("[0,2,4]");

		addInput("[1,2,1,2,1,2,1,2,1]", "2");
		addOutput("[0,2,4]");

		addInput("[1,2,1,2,6,7,5,1]", "2");
		addOutput("[0,3,5]");
	}
}

