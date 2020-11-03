package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Stack;

// https://leetcode-cn.com/problems/basic-calculator/
public class NumberOfDigitOne extends AbstractProblem{
	public static void main(String[] args) {
		new NumberOfDigitOne().runTest();
	}

	@Answer
	public int countDigitOne(int n) {
		if (n<10) {
			return n >0?1:0;
		}
		String str = String.valueOf(n);
		int h = str.charAt(0) - '0';

		int unit = 1;
		int scale = 1;
		for (int i = 1; i < str.length() - 1; i++) {
			scale*=10;
		}
		if (str.length()>1) {
			unit = scale * (str.length()-1);
		}
		int hval = unit * h;
		if (h == 1) {
			hval += 1 + n - h*scale*10;
		} else {
			hval += scale * 10;
		}
		int rval = countDigitOne(n - h*scale*10);
		return hval+rval;
	}

	@Override
	void init() {
		addInputParsers(new IntParser());
		setOutputParser(new IntParser());

		addInput("13");
		addOutput("6");
	}
}

