package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Arrays;

// https://leetcode-cn.com/problems/basic-calculator/
public class CountAndSay extends AbstractProblem{
	public static void main(String[] args) {
		new CountAndSay().runTest();
	}
	@Answer
	public String countAndSay(int n) {
		String ret = "1";
		for (int i = 1; i < n; i++) {
			ret = next(ret);
		}
		return ret;
	}

	public String next(String cur) {
		StringBuilder sb = new StringBuilder();
		char c = cur.charAt(0);
		int cnt = 1;
		for (int i = 1; i < cur.length(); i++) {
			char a = cur.charAt(i);
			if (c == a) {
				cnt ++;
				continue;
			} else {
				sb.append(cnt).append(c);
				c = a;
				cnt = 1;
			}
		}
		sb.append(cnt).append(c);
		return sb.toString();
	}


	@Override
	void init() {
		addInputParsers(
				new IntParser()
		);
		setOutputParser(new StringParser());

		addInput("1");
		addOutput("1");

		addInput("4");
		addOutput("1211");
	}
}

