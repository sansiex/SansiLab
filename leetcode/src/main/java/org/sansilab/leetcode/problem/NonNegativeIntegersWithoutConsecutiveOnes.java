package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;

// https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/
public class NonNegativeIntegersWithoutConsecutiveOnes extends AbstractProblem{
	public static void main(String[] args) {
		new NonNegativeIntegersWithoutConsecutiveOnes().runTest();
	}
	@Answer
	public int findIntegers(int n) {
		int l = (int) Math.ceil(Math.log(n + 1)/Math.log(2));
		if (l <= 1) {
			return (n & 1) == 1 ? 2 : 1;
		}
		int[] dp = new int[l];
		dp[0] = 2;
		dp[1] = 3;
		for (int i = 2; i < l; i++) {
			int offset = l - i - 1;
			int val = n >> offset;
			int val2 = n >> (offset + 2);
			if (val % 4 > 0) {
				dp[i] = dp[i - 1] + dp[i - 2];
			} else {
				if (valid(val2)) {
					dp[i] = dp[i - 1] + dp[i - 2] - 1;
				} else {
					dp[i] = dp[i - 1] + dp[i - 2];
				}
			}
		}
		return dp[l - 1];
	}

	public boolean valid(int n) {
		int b = 3;
		while (b <= n) {
			int a = n&b;
			if (a == 3) {
				return false;
			} else if (a <= 1) {
				n >>= 2;
			} else {
				n <<= 1;
			}
		}
		return true;
	}

	@Override
	void init() {
		addInputParsers(
				new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("16");
		addOutput("9");

		addInput("4");
		addOutput("4");

		addInput("5");
		addOutput("5");

		addInput("1");
		addOutput("2");

		addInput("2");
		addOutput("3");
	}
}

