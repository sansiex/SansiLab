package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Arrays;

// https://leetcode.cn/problems/super-washing-machines/
public class SuperWashingMachines extends AbstractProblem{
	public static void main(String[] args) {
		new SuperWashingMachines().runTest();
	}
	@Answer
	public int findMinMoves(int[] machines) {
		int l = machines.length;
		int sum = Arrays.stream(machines).sum();
		if (sum % l > 0) {
			return -1;
		}
		int avg = sum/l;
		int m = 0;
		int cs = 0;
		for (int i = 0; i < l; i++) {
			int c = machines[i] - avg;
			cs += c;
			m = Math.max(c, m);
			m = Math.max(Math.abs(cs), m);
		}
		return m;
	}

	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
		);
		setOutputParser(new IntParser());

		addInput("[0,0,11,5]");
		addOutput("8");

		addInput("[1,0,5]");
		addOutput("3");

		addInput("[0,3,0]");
		addOutput("2");

		addInput("[0,2,0]");
		addOutput("-1");

		addInput("[3,0,3]");
		addOutput("1");
	}
}

