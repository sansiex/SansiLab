package org.sansilab.leetcode.problem;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/freedom-trail/
public class FreedomTrail extends AbstractProblem{
	public static void main(String[] args) {
		new FreedomTrail().runTest();
	}
	@Answer
	public int findRotateSteps(String ring, String key) {
		int[][] dp = new int[ring.length()][key.length()];
		char k = key.charAt(key.length() - 1);
		for (int i = 0; i < ring.length(); i++) {
			int s = Integer.MAX_VALUE;
			for (int j = 0; j < ring.length(); j++) {
				char ck = ring.charAt(j);
				if (ck != k) {
					continue;
				}
				s = Math.min(s, 1 + dist(ring.length(), i, j));
			}
			dp[i][key.length() - 1] = s;
		}

		for (int i = key.length() - 2; i >= 0; i--) {
			char ck = key.charAt(i);
			for (int j = 0; j < ring.length(); j++) {
				int s = Integer.MAX_VALUE;
				for (int l = 0; l < ring.length(); l++) {
					char r = ring.charAt(l);
					if (r != ck) {
						continue;
					}
					int cs = 1 + dist(ring.length(), j, l) + dp[l][i+1];
					s = Math.min(s, cs);
				}
				dp[j][i] = s;
			}
		}
		return dp[0][0];
	}

	int dist(int len, int i, int j) {
		if (i > j) {
			int tmp = i;
			i = j;
			j = tmp;
		}
		return Math.min(j - i, i + len - j);
	}

	@Override
	void init() {
		addInputParsers(
				new StringParser(),
				new StringParser()
		);
		setOutputParser(new IntParser());

		addInput("abcde", "ade");
		addOutput("6");

		addInput("godding", "godding");
		addOutput("13");

		addInput("godding", "gd");
		addOutput("4");
	}
}

