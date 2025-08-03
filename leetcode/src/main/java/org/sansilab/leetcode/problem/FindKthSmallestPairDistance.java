package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
public class FindKthSmallestPairDistance extends AbstractProblem{
	public static void main(String[] args) {
		new FindKthSmallestPairDistance().runTest();
	}

	@Answer
	public int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);
		int max = nums[nums.length - 1] - nums[0];
		return bs(nums, k, 0, max);
	}

	int bs(int[] nums, int k, int s, int e) {
		int m = (s+e)/2;
		int cnt = 0;
		int maxCnt = 1;
		int maxVal = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				int val = nums[j] - nums[i];
				if (val <= m) {
					cnt ++;
					if (val > maxVal) {
						maxVal = val;
						maxCnt = 1;
					} else if (val == maxVal) {
						maxCnt ++;
					}
				} else {
					break;
				}
			}
		}
		if (s == e) {
			return maxVal;
		}
		if (cnt < k) {
			return bs(nums, k, Math.min(m + 1, e), e);
		} else if (cnt > k) {
			if (cnt - maxCnt < k) {
				return maxVal;
			}
			return bs(nums, k, s, Math.max(s, m-1));
		} else {
			return maxVal;
		}
	}

	@Override
	void init() {
		addInputParsers(new ArrayParser(new IntParser()), new IntParser());
		setOutputParser(new IntParser());

		addInput("[10,6,2,10,5,4,0,1,5,2,5,5,5,0,4,9,8,6,7,9,1,10,4,8,6,3,6,2,1,7,5,0,2,6,10,10,0,3,9,0,8,3,5,9,4,4,5,2,2,7]", "444");
		addOutput("2");

		addInput("[10,6,2,10,5,4,0,1,5,2,5,5,5,0,4,9,8,6,7,9,1,10,4,8,6,3,6,2,1,7,5,0,2,6,10,10,0,3,9,0,8,3,5,9,4,4,5,2,2,7]", "444");
		addOutput("2");

		addInput("[9,10,7,10,6,1,5,4,9,8]", "18");
		addOutput("2");

		addInput("[1,3,4]", "3");
		addOutput("3");

		addInput("[1,3,1]", "1");
		addOutput("0");

		addInput("[1,1,1]", "2");
		addOutput("0");

		addInput("[1,6,1]", "3");
		addOutput("5");
	}
}

