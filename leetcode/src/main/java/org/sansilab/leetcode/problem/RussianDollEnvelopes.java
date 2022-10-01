package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.*;

import java.util.*;

// https://leetcode.cn/problems/russian-doll-envelopes/
public class RussianDollEnvelopes extends AbstractProblem{
	public static void main(String[] args) {
		new RussianDollEnvelopes().runTest();
	}
	@Answer
	public int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, (e1, e2) -> {
			if (e1[0] == e2[0]) {
				return e2[1] - e1[1];
			} else {
				return e1[0] - e2[0];
			}
		});

		TreeSet<Integer> list = new TreeSet<>();
		list.add(envelopes[0][1]);
		for (int i = 1; i < envelopes.length; i++) {
			if (envelopes[i][1] > list.last()) {
				list.add(envelopes[i][1]);
			} else {
				int v = list.ceiling(envelopes[i][1]);
				list.remove(v);
				list.add(envelopes[i][1]);
			}
		}
		return list.size();
	}


	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
		);
		setOutputParser(new IntParser());

		addInput("[[5,4],[6,4],[6,7],[2,3]]");
		addOutput("3");

		addInput("[[1,1],[1,1],[1,1]]");
		addOutput("1");

		addInput("[[4,5],[4,6],[6,7],[2,3],[1,1]]");
		addOutput("4");


	}
}

