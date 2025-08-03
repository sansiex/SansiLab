package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.DoubleParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/smallest-good-base/
public class SmallestGoodBase extends AbstractProblem{
	public static void main(String[] args) {
		new SmallestGoodBase().runTest();
	}
	@Answer
	public String smallestGoodBase(String n) {
		long val = Long.parseLong(n);
		int maxLen = Long.toBinaryString(val).length();

		for (int i = maxLen; i >= 1; i--) {
			long res = tryLen(val, i);
			if (res > 0) {
				return String.valueOf(res);
			}
		}
		return "";
	}

	public long tryLen(long val, int len) {
		long e = val-1;
		if (len > 1) {
			e = (long) Math.floor(Math.sqrt(val));
		}
		long nroot = nroot(val, 2, e, len);
		for (int i = len; i >= 1 ; i--) {
			long cur = pow(nroot, i);
			val -= cur;
			if (val  >= cur || val <= 0) {
				return -1;
			}
		}
		if (val != 1L)  {
			return -1;
		}
		return nroot;
	}

	public long nroot(long val, long s, long e, int n) {
		if (n == 2) {
			return (long) Math.floor(Math.sqrt(val));
		}
		if (s > e) {
			return -1;
		}
		long guess = (s + e)/2;

		long powVal = pow(guess, n);
		if (powVal > val) {
			return nroot(val, s, guess-1, n);
		}
		if (pow(guess+1, n) >= val) {
			return guess;
		}
		return nroot(val, guess+1, e, n);
	}

	public long pow(long base, long exp) {
		long h = Long.MAX_VALUE/base;
		long ret = base;
		for (int i = 1; i < exp; i++) {
			if (ret > h) {
				return Long.MAX_VALUE;
			}
			ret*=base;
		}
		return ret;
	}


	@Override
	void init() {
		addInputParsers(
				new StringParser()
		);
		setOutputParser(new StringParser());

		addInput("2251799813685247");
		addOutput("2");

		addInput("1000000000000000000");
		addOutput("999999999999999999");

		addInput("100");
		addOutput("99");

		addInput("4681");
		addOutput("8");

		addInput("13");
		addOutput("3");
	}
}

