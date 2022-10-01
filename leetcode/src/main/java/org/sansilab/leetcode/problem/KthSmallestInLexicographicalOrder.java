package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.BooleanParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/
public class KthSmallestInLexicographicalOrder extends AbstractProblem{
	public static void main(String[] args) {
		new KthSmallestInLexicographicalOrder().runTest();
	}
	@Answer
	public int findKthNumber(int n, int k) {
		int s = 1;
		int cur = 1;
		while (cur < k) {
			long cnt = count(n, s);
			if (cur + cnt - 1 < k) {
				s ++;
				cur += cnt;
			} else {
				s = s*10;
				cur ++;
			}
		}
		return s;
	}

	public int count(int n, int s) {
		int cnt = 0;
		long l = s;
		long r = s;
		while (l <= n) {
			cnt += Math.min(r, n) - l + 1;
			l = l*10;
			r = r*10 + 9;
		}
		return cnt;
	}


	@Override
	void init() {
		addInputParsers(
				new IntParser(),
				new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("681692778","351251360");
		addOutput("416126219");

		addInput("681692778","351251360");
		addOutput("416126219");

		addInput("13", "2");
		addOutput("10");

		addInput("1", "1");
		addOutput("1");
	}
}

