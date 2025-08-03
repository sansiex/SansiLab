package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

// https://leetcode.cn/problems/decode-ways-ii/
public class DecodeWays2 extends AbstractProblem{
	public static void main(String[] args) {
		new DecodeWays2().runTest();
	}
	@Answer
	public int numDecodings(String s) {
		long mod = 1000000007L;
		int l = s.length();
		long[] dp = new long[l];
		dp[0] = one(s.charAt(0));
		if (l == 1) {
			return (int) dp[0];
		}
		char c1 = s.charAt(1);
		if (c1 == '0') {
			dp[1] = two(s.charAt(0), s.charAt(1));
		} else {
			dp[1] = dp[0] * one(s.charAt(1)) + two(s.charAt(0), s.charAt(1));
		}
		for (int i = 2; i < l; i++) {
			char c = s.charAt(i);
			if (c != '0') {
				dp[i] = (dp[i - 1] * one(s.charAt(i)) + dp[i - 2] * two(s.charAt(i - 1), s.charAt(i))) % mod;
			} else {
				dp[i] = (dp[i - 2] * two(s.charAt(i - 1), s.charAt(i))) % mod;
			}
		}
		return (int) dp[l-1];
	}

	int one(char c) {
		if ('*' == c) {
			return 9;
		} else if ('0' == c) {
			return 0;
		} else {
			return 1;
		}
	}

	int two(char a, char b) {
		if (a == '*') {
			if (b == '*') {
				return 15;
			} else if (b > '6') {
				return 1;
			} else {
				return 2;
			}
		} else {
			if (b == '*') {
				if (a > '2' || a == '0') {
					return 0;
				} else if (a == '1') {
					return 9;
				} else {
					return 6;
				}
			} else {
				int val = (a - '0') * 10 + (b  - '0');
				if (val < 10 || val > 26) {
					return 0;
				} else {
					return 1;
				}
			}
		}
	}

	@Override
	void init() {
		addInputParsers(
				new StringParser()
		);
		setOutputParser(new IntParser());

		addInput("0");
		addOutput("0");

		addInput("*0**0");
		addOutput("36");

		addInput("*1*1*0");
		addOutput("404");

		addInput("*1*1");
		addOutput("202");

		addInput("*1*");
		addOutput("180");

		addInput("*1");
		addOutput("11");

		addInput("*");
		addOutput("9");

		addInput("1*");
		addOutput("18");

		addInput("2*");
		addOutput("15");
	}
}

