package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.*;
import java.util.function.Function;

// https://leetcode.cn/problems/ipo/
public class Ipo extends AbstractProblem{
	public static void main(String[] args) {
		new Ipo().runTest();
	}
	@Answer
	public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		int len = profits.length;
		int[][] arr = new int[len][2];
		for (int i = 0; i < len; i++) {
			arr[i][0] = profits[i];
			arr[i][1] = capital[i];
		}
		Arrays.sort(arr, (a, b) -> {
			return a[1] - b[1];
		});
		PriorityQueue<Integer> pile = new PriorityQueue<>((a, b) -> b - a);
		int c = w;
		int j = 0;
		for (int i = 0; i < k; i++) {
			for (; j < len && arr[j][1] <= c; j++) {
				pile.add(arr[j][0]);
			}
			if (pile.isEmpty()) {
				break;
			}
			int p = pile.poll();
			c += p;
		}
		return c;
	}


	@Override
	void init() {
		addInputParsers(
				new IntParser(),
				new IntParser(),
				new ArrayParser(new IntParser()),
				new ArrayParser(new IntParser())
		);
		setOutputParser(new IntParser());

		addInput("2", "0", "[1,2,3]", "[0,1,1]");
		addOutput("4");
	}
}

