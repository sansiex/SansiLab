package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/split-array-largest-sum/
public class SplitArrayLargestSum extends AbstractProblem{
	public static void main(String[] args) {
		new SplitArrayLargestSum().runTest();
	}
	@Answer
	public int splitArray(int[] nums, int m) {
		int[][] dp = new int[m][nums.length];
		dp[0][0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			dp[0][i] = dp[0][i-1] + nums[i];
		}

		for (int i = 1; i < m; i++) {
			dp[i][i] = Math.max(dp[i-1][i-1], nums[i]);
			for (int j = i+1; j < nums.length; j++) {
				int sum = nums[j];
				int k = j-1;
				int cur = Math.max(sum, dp[i-1][k]);
				while(true) {
					sum += nums[k];
					k--;
					cur = Math.min(Math.max(sum, dp[i-1][k]), cur);
					if (sum>=dp[i-1][k] || k<i) {
						break;
					}
				}
				dp[i][j]=cur;
			}
		}
		return dp[m-1][nums.length-1];
	}

	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
				, new IntParser()
		);
		setOutputParser(new IntParser());

		addInput(
				"[10,2,3]","2");
		addOutput("10");

		addInput(
				"[10,2,3]","2");
		addOutput("10");

		addInput(
				"[7,2,5,10,8]","2");
		addOutput("18");

		addInput(
				"[1,2,3,4,5]","2");
		addOutput("9");

		addInput("[1,4,4]", "3");
		addOutput("4");
	}
}

