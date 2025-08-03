package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/count-different-palindromic-subsequences/
 *
 * 1 <= s.length <= 1000
 * s[i] is either 'a', 'b', 'c', or 'd'.
 */
public class CountDifferentPalindromicSubsequences extends AbstractProblem{
	public static void main(String[] args) {
		new CountDifferentPalindromicSubsequences().runTest();
	}

	@Answer
	public int countPalindromicSubsequences(String s) {
		int l = s.length();
		int dp[][] = new int[l][l];
		for (int i = 0; i < l; i++) {
			dp[i][i] = 1;
		}
		int mod = 1000000007;

		for (int e = 1; e < l; e++) {
			for (int b = e-1; b >= 0; b--) {
				char rc = s.charAt(e);
				long cnt = dp[b][e-1];
				int lci = -1;
				int ai = -1;
				for (int i = b; i < e; i++) {
					char c = s.charAt(i);
					if (c != rc) {
						continue;
					}
					if (lci == -1) {
						lci = i;
					} else {
						ai = i;
					}
				}
				if (lci == -1) {
					cnt += 1;
				} else if (ai == -1) {
					cnt += dp[lci+1][e-1] + 1;
				} else {
					cnt += dp[lci+1][e-1] - dp[lci + 1][ai] + 1;
				}
				dp[b][e] = (int) (cnt % mod);
			}
		}
		return dp[0][l-1];
	}

	@Override
	void init() {
		addInputParsers(new StringParser());
		setOutputParser(new IntParser());
		addInput("aacaa");
		addOutput("7");

		addInput("acaca");
		addOutput("9");

		addInput("bcacb");
		addOutput("10");

		addInput("bccb");
		addOutput("6");

		addInput("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba");
		addOutput("104860361");

		addInput("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba");
		addOutput("104860361");
	}
}

