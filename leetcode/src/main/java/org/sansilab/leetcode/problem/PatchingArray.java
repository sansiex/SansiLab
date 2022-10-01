package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

// https://leetcode-cn.com/problems/basic-calculator/
public class PatchingArray extends AbstractProblem{
	public static void main(String[] args) {
		new PatchingArray().runTest();
	}
	@Answer
	public int minPatches(int[] nums, int n) {
		long curMax = 0;
		int ret = 0;
		int i = 0;
		while(curMax < n) {
			if (i < nums.length) {
				int v = nums[i];
				if (v > curMax + 1) {
					ret++;
					curMax = curMax * 2 + 1;
				} else {
					curMax = curMax + v;
					i++;
				}
			} else {
				ret++;
				curMax = curMax * 2 + 1;
			}
		}
		return ret;
	}


	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
				,new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("[1,3]", "6");
		addOutput("1");

		addInput("[1,5,10]", "20");
		addOutput("2");

		addInput("[1,2,2]", "5");
		addOutput("0");

		addInput("[1,2,31,33]", "2147483647");
		addOutput("28");
	}
}

