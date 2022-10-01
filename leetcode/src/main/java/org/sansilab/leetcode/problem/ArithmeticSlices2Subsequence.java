package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/arithmetic-slices-ii-subsequence/
public class ArithmeticSlices2Subsequence extends AbstractProblem{
	public static void main(String[] args) {
		new ArithmeticSlices2Subsequence().runTest();
	}
	@Answer
	public int numberOfArithmeticSlices(int[] nums) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
		}
		int cnt = 0;
		for (Map.Entry<Integer, List<Integer>> ent : map.entrySet()) {
			List<Integer> list = ent.getValue();
			if (list.size()<3) {
				continue;
			}
			cnt += Math.pow(2, list.size()) - 1 - list.size() - c(list.size(), 2);
		}
		for (int i = 0; i < nums.length-2; i++) {
			for (int j = i+1; j < nums.length-1; j++) {
				if (nums[j] == nums[i]) {
					continue;
				}
				long diff = new Long(nums[j]) - new Long(nums[i]);
				long t = new Long(nums[j]) + diff;
				if (t > Integer.MAX_VALUE || t < Integer.MIN_VALUE) {
					continue;
				}
				cnt += search(map, j, t, diff);
			}
		}
		return cnt;
	}

	static int c(int a, int b) {
		int t = Math.min(a-b, b);
		int z = 1;
		int m = 1;
		for (int i = 1; i <= t; i++) {
			z *= (a+1-i);
			m *= i;
		}
		return z/m;
	}

	int search(Map<Integer, List<Integer>> map, int lastIndex, Long targetVal, long diff) {
		if (targetVal > Integer.MAX_VALUE || targetVal <  Integer.MIN_VALUE) {
			return 0;
		}
		List<Integer> list = map.get(targetVal.intValue());
		if (list == null) {
			return 0;
		}
		int ret = 0;
		long nextTarget =  targetVal + diff;
		for (Integer index : list) {
			if (index <= lastIndex) {
				continue;
			}
			ret += 1 + search(map, index, nextTarget, diff);
		}
		return ret;
	}

	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
		);
		setOutputParser(new IntParser());

		addInput("[2147483638,2147483639,2147483640,2147483641,2147483642,2147483643,2147483644,2147483645,2147483646,2147483647,-2147483648,-2147483647,-2147483646,-2147483645,-2147483644,-2147483643,-2147483642,-2147483641,-2147483640,-2147483639]");
		addOutput("110");

		addInput("[-2147483602,-2147483050,-2147482899,-2147483259,-2147483111,-2147483468" +
				",-2147483080,-2147482907,-2147483037,-2147483187,-2147482982,-2147482702" +
				",-2147483168,-2147483385,-2147483207,-2147483130,-2147483316,-2147483241" +
				",-2147482960,-2147483383,-2147482987,-2147483480,-2147483011,-2147483013" +
				",-2147482837,-2147483234,-2147482795,-2147483247,-2147482790,-2147483357" +
				",-2147482866,-2147482995,-2147483472,-2147483577,-2147483218,-2147483167" +
				",-2147482755,-2147483349,-2147483190,-2147483236,-2147482902,-2147482986" +
				",-2147483336,-2147483372,-2147483407,-2147482726,-2147483400,-2147483235" +
				",-2147483479,-2147483072,-2147483215,-2147483428,-2147483171,-2147482741" +
				",-2147482796,-2147483418,-2147483632,-2147483446,-2147483268,-2147483059" +
				",-2147483194,-2147482772,-2147483347,-2147483502,-2147483242,-2147482722" +
				",-2147483123,-2147483454,-2147482889,-2147482995,-2147483571,-2147483278" +
				",-2147483136,-2147482709,-2147482785,-2147483544,-2147483579,-2147483648" +
				",-2147483189,-2147483128,-2147483461,-2147483110,-2147483037,-2147483288" +
				",-2147483623,-2147483480,-2147482715,-2147483644,-2147483458,-2147483320" +
				",-2147482777,-2147483018,-2147483305,-2147482717,-2147482779,-2147483040" +
				",-2147483179,-2147483406,-2147482856,-2147483179,-2147483388,-2147483065" +
				",-2147483229,-2147483446,-2147483058,-2147483331,-2147483209,-2147483597" +
				",-2147482667,-2147483553,-2147483261,-2147483538,-2147483271,-2147483474" +
				",-2147482792,-2147482665,-2147482961,-2147483006,-2147483585,-2147483404" +
				",-2147483179,-2147483532,-2147483597,-2147483185,-2147483339,-2147482716" +
				",-2147483584,-2147483078,-2147483549,-2147483206,-2147483619,-2147483166" +
				",-2147482730,-2147482913,-2147483550,-2147482783,-2147483483,-2147483270" +
				",-2147483415,-2147483505,-2147483337,-2147482777,-2147482775,-2147482672" +
				",-2147483141,-2147482967,-2147482838,-2147482654,-2147482850,-2147482792]");
		addOutput("306");

		addInput("[2147483638,2147483639,2147483640,2147483641,2147483642,2147483643,2147483644,2147483645,2147483646,2147483647,-2147483648,-2147483647,-2147483646,-2147483645,-2147483644,-2147483643,-2147483642,-2147483641,-2147483640,-2147483639]");
		addOutput("110");

		addInput("[-2147483648,0,-2147483648]");
		addOutput("0");

		addInput("[0,2000000000,-294967296]");
		addOutput("0");

		addInput("[7,7,7,7,7]");
		addOutput("16");

		addInput("[2,4,6,8,10]");
		addOutput("7");
	}
}

