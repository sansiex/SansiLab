package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Arrays;

// https://leetcode.cn/problems/strange-printer/
public class StrangePrinter extends AbstractProblem{
	public static void main(String[] args) {
		new StrangePrinter().runTest();
	}
	@Answer
	public int strangePrinter(String s) {
		char[] chars = new char[s.length()];
		int end = 1;
		char last = s.charAt(0);
		chars[0] = last;
		for (int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == last) {
				continue;
			}
			last = c;
			chars[end] = c;
			end ++;
		}
		char[] paper = new char[end];
		Arrays.fill(paper, '0');
		int ret = 0;
		for (int i = 0; i < end; i++) {
			int inc = 1;
			int maxInc = 1;
			int maxIncPos = i;
			char c = chars[i];
			if (paper[i] == c) {
				continue;
			}
			ret ++;
			paper[i] = c;
			for (int j = i + 1; j < end; j++) {
				char c2 = chars[j];
				char pc = paper[j];

				if (pc == c) {
					continue;
				}
				if (pc == c2) {
					inc --;
				} else if (c2 == c) {
					inc ++;
				}
				if (inc >= maxInc) {
					maxInc = inc;
					maxIncPos = j;
				}
			}
			for (int j = i+1; j <= maxIncPos; j++) {
				paper[j] = c;
			}
			if (chars[end-1] == paper[end-1]) {
				end--;
			}
		}
		return ret;
	}

	@Override
	void init() {
		addInputParsers(
				new StringParser()
		);
		setOutputParser(new IntParser());

		addInput("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa");
		addOutput("19");

		addInput("tbgtgb");
		addOutput("4");

		addInput("acbcaba");
		addOutput("4");

		addInput("abcac");
		addOutput("4");

		addInput("aaabbb");
		addOutput("2");

		addInput("aba");
		addOutput("2");
	}
}

