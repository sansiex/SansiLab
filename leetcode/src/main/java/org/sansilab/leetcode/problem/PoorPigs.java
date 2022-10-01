package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/poor-pigs/
public class PoorPigs extends AbstractProblem{
	public static void main(String[] args) {
		new PoorPigs().runTest();
	}
	@Answer
	public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
		int states = minutesToTest / minutesToDie + 1;
		int pigs = (int) Math.ceil(Math.log(buckets) / Math.log(states) - 1e-5);
		return pigs;
	}

	@Override
	void init() {
		addInputParsers(
				new IntParser()
				,new IntParser()
				,new IntParser()
		);
		setOutputParser(new IntParser());


		addInput("8",  "15", "40");
		addOutput("2");


		addInput("8",  "15", "40");
		addOutput("2");


		addInput("9",  "15", "30");
		addOutput("2");


		addInput("4",  "15", "15");
		addOutput("2");


		addInput("4",  "15", "30");
		addOutput("2");
	}
}

